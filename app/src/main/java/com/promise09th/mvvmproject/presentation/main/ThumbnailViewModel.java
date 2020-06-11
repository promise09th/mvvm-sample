package com.promise09th.mvvmproject.presentation.main;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.promise09th.mvvmproject.common.ViewType;
import com.promise09th.mvvmproject.data.thumbnail.ThumbnailRepository;
import com.promise09th.mvvmproject.presentation.Event;
import com.promise09th.mvvmproject.presentation.model.Thumbnail;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;

public class ThumbnailViewModel extends ViewModel {

    private static final String TAG = ThumbnailViewModel.class.getSimpleName();

    private ThumbnailRepository mThumbnailRepository;

    private MutableLiveData<ArrayList<Thumbnail>> mSearchResultThumbnail = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Thumbnail>> mSavedThumbnail = new MutableLiveData<>();

    private MutableLiveData<Event<Thumbnail>> mSearchResultItemClicked = new MutableLiveData<>();
    private MutableLiveData<Event<Thumbnail>> mMyLockerItemClicked = new MutableLiveData<>();

    private CompositeDisposable mDisposables = new CompositeDisposable();

    @Inject
    public ThumbnailViewModel(@NonNull ThumbnailRepository thumbnailRepository) {
        mThumbnailRepository = thumbnailRepository;
    }

    @Override
    protected void onCleared() {
        mDisposables.clear();
        super.onCleared();
    }

    public void setSearchResultThumbnail(ArrayList<Thumbnail> receiveThumbnail) {
        receiveThumbnail.sort((t1, t2) -> t2.getDateTime().compareToIgnoreCase(t1.getDateTime()));
        mSearchResultThumbnail.setValue(receiveThumbnail);
    }

    public LiveData<ArrayList<Thumbnail>> getSearchResultThumbnail() {
        return mSearchResultThumbnail;
    }

    public void fetchMyLockerThumbnails() {
        // Room DB에서 Flowable 사용 시, complete가 불리지 않고 계속 Observing 함
        mDisposables.add(mThumbnailRepository.getAllThumbnail().subscribe(
                thumbnails -> {
                    Log.d(TAG, "fetchMyLocker : onNext : " + thumbnails.size());
                    thumbnails.sort((t1, t2) -> t2.getDateTime().compareToIgnoreCase(t1.getDateTime()));
                    mSavedThumbnail.setValue(thumbnails);
                },
                e -> Log.d(TAG, "fetchMyLocker() : fail"),
                () -> Log.d(TAG, "fetchMyLocker() : complete")
        ));
    }

    public boolean containsSavedThumbnail(Thumbnail savedThumbnail) {
        ArrayList<Thumbnail> list = getSavedThumbnail().getValue();
        return list != null && list.contains(savedThumbnail);
    }

    public void setSavedThumbnail(Thumbnail savedThumbnail) {
        mDisposables.add(mThumbnailRepository.saveThumbnail(savedThumbnail).subscribe(
                () -> Log.d(TAG, "save : success"),
                e -> Log.d(TAG, "save : fail")
        ));
    }

    public void removeSavedThumbnail(Thumbnail savedThumbnail) {
        ArrayList<Thumbnail> list = getSavedThumbnail().getValue();
        if (list != null && list.contains(savedThumbnail)) {
            mDisposables.add(mThumbnailRepository.deleteThumbnail(savedThumbnail).subscribe(
                    () -> Log.d(TAG, "delete : success"),
                    e -> Log.d(TAG, "delete : fail")
            ));
        }
    }

    public LiveData<ArrayList<Thumbnail>> getSavedThumbnail() {
        return mSavedThumbnail;
    }

    public Single<ArrayList<Thumbnail>> getThumbnail(String query) {
        return mThumbnailRepository.getThumbnail(query);
    }

    public MutableLiveData<Event<Thumbnail>> getSearchResultItemClicked() {
        return mSearchResultItemClicked;
    }

    public MutableLiveData<Event<Thumbnail>> getMyLockerItemClicked() {
        return mMyLockerItemClicked;
    }

    public void onClickItem(ViewType type, Thumbnail thumbnail) {
        if (type == ViewType.SEARCH) {
            setSearchResultItemClicked(thumbnail);
        } else { // ClickView.LOCKER
            setMyLockerItemClicked(thumbnail);
        }
    }

    private void setSearchResultItemClicked(Thumbnail thumbnail) {
        mSearchResultItemClicked.setValue(new Event<>(thumbnail));
    }

    private void setMyLockerItemClicked(Thumbnail thumbnail) {
        mMyLockerItemClicked.setValue(new Event<>(thumbnail));
    }
}