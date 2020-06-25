package com.promise09th.mvvmproject.presentation.main;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.promise09th.mvvmproject.common.ViewType;
import com.promise09th.mvvmproject.data.repository.ThumbnailRepository;
import com.promise09th.mvvmproject.presentation.Event;
import com.promise09th.mvvmproject.presentation.model.Thumbnail;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class ThumbnailViewModel extends ViewModel {

    private static final String TAG = ThumbnailViewModel.class.getSimpleName();

    private ThumbnailRepository thumbnailRepository;

    private MutableLiveData<ArrayList<Thumbnail>> searchResultThumbnail = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Thumbnail>> savedThumbnail = new MutableLiveData<>();

    private MutableLiveData<Event<Thumbnail>> searchResultItemClicked = new MutableLiveData<>();
    private MutableLiveData<Event<Thumbnail>> myLockerItemClicked = new MutableLiveData<>();
    private MutableLiveData<Event<Boolean>> errorToastShown = new MutableLiveData<>();

    private CompositeDisposable disposables = new CompositeDisposable();

    @Inject
    public ThumbnailViewModel(@NonNull ThumbnailRepository thumbnailRepository) {
        this.thumbnailRepository = thumbnailRepository;
    }

    @Override
    protected void onCleared() {
        disposables.clear();
        super.onCleared();
    }

    public void setSearchResultThumbnail(ArrayList<Thumbnail> receiveThumbnail) {
        receiveThumbnail.sort((t1, t2) -> t2.getDateTime().compareToIgnoreCase(t1.getDateTime()));
        searchResultThumbnail.setValue(receiveThumbnail);
    }

    public LiveData<ArrayList<Thumbnail>> getSearchResultThumbnail() {
        return searchResultThumbnail;
    }

    public boolean containsSavedThumbnail(Thumbnail savedThumbnail) {
        ArrayList<Thumbnail> list = getSavedThumbnail().getValue();
        return list != null && list.contains(savedThumbnail);
    }

    public LiveData<ArrayList<Thumbnail>> getSavedThumbnail() {
        return savedThumbnail;
    }

    public MutableLiveData<Event<Thumbnail>> getSearchResultItemClicked() {
        return searchResultItemClicked;
    }

    public MutableLiveData<Event<Thumbnail>> getMyLockerItemClicked() {
        return myLockerItemClicked;
    }

    public LiveData<Event<Boolean>> getErrorToastShown() {
        return errorToastShown;
    }

    private void setSearchResultItemClicked(Thumbnail thumbnail) {
        searchResultItemClicked.setValue(new Event<>(thumbnail));
    }

    private void setMyLockerItemClicked(Thumbnail thumbnail) {
        myLockerItemClicked.setValue(new Event<>(thumbnail));
    }

    public void onClickItem(ViewType type, Thumbnail thumbnail) {
        if (type == ViewType.SEARCH) {
            setSearchResultItemClicked(thumbnail);
        } else { // ClickView.LOCKER
            setMyLockerItemClicked(thumbnail);
        }
    }

    public void fetchMyLockerThumbnails() {
        // Room DB에서 Flowable 사용 시, complete가 불리지 않고 계속 Observing 함
        disposables.add(thumbnailRepository.getAllThumbnail().subscribe(
                thumbnails -> {
                    Log.d(TAG, "fetchMyLocker : onNext : " + thumbnails.size());
                    thumbnails.sort((t1, t2) -> t2.getDateTime().compareToIgnoreCase(t1.getDateTime()));
                    savedThumbnail.setValue(thumbnails);
                },
                e -> Log.d(TAG, "fetchMyLocker() : fail"),
                () -> Log.d(TAG, "fetchMyLocker() : complete")
        ));
    }

    public void setSavedThumbnail(Thumbnail savedThumbnail) {
        disposables.add(thumbnailRepository.saveThumbnail(savedThumbnail).subscribe(
                () -> Log.d(TAG, "save : success"),
                e -> Log.d(TAG, "save : fail")
        ));
    }

    public void removeSavedThumbnail(Thumbnail savedThumbnail) {
        ArrayList<Thumbnail> list = getSavedThumbnail().getValue();
        if (list != null && list.contains(savedThumbnail)) {
            disposables.add(thumbnailRepository.deleteThumbnail(savedThumbnail).subscribe(
                    () -> Log.d(TAG, "delete : success"),
                    e -> Log.d(TAG, "delete : fail")
            ));
        }
    }

    public void fetchThumbnail(String query) {
        disposables.add(thumbnailRepository.getThumbnail(query)
                .subscribe(
                        this::setSearchResultThumbnail,
                        e -> errorToastShown.setValue(new Event<>(Boolean.TRUE))
                ));
    }
}