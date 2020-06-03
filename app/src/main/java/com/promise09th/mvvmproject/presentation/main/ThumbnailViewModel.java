package com.promise09th.mvvmproject.presentation.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.promise09th.mvvmproject.data.thumbnail.ThumbnailRepository;
import com.promise09th.mvvmproject.model.thumbnail.Thumbnail;
import com.promise09th.mvvmproject.presentation.Event;

import java.util.ArrayList;

import io.reactivex.Single;

public class ThumbnailViewModel extends ViewModel {

    public enum ClickView {
        SEARCH,
        LOCKER
    }

    private ThumbnailRepository mThumbnailRepository;

    private MutableLiveData<ArrayList<Thumbnail>> mSearchResultThumbnail = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Thumbnail>> mSavedThumbnail = new MutableLiveData<>();

    private MutableLiveData<Event<Thumbnail>> mSearchResultItemClicked = new MutableLiveData<>();
    private MutableLiveData<Event<Thumbnail>> mMyLockerItemClicked = new MutableLiveData<>();

    public ThumbnailViewModel(@NonNull ThumbnailRepository thumbnailRepository) {
        mThumbnailRepository = thumbnailRepository;
    }

    public void setSearchResultThumbnail(ArrayList<Thumbnail> receiveThumbnail) {
        receiveThumbnail.sort((t1, t2) -> t2.getDateTime().compareToIgnoreCase(t1.getDateTime()));
        mSearchResultThumbnail.setValue(receiveThumbnail);
    }

    public LiveData<ArrayList<Thumbnail>> getSearchResultThumbnail() {
        return mSearchResultThumbnail;
    }

    public boolean containsSavedThumbnail(Thumbnail savedThumbnail) {
        ArrayList<Thumbnail> list = getSavedThumbnail().getValue();
        if (list != null && list.contains(savedThumbnail)) {
            return true;
        }
        return false;
    }

    public void setSavedThumbnail(Thumbnail savedThumbnail) {
        ArrayList<Thumbnail> list = getSavedThumbnail().getValue();
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(savedThumbnail);
        list.sort((t1, t2) -> t2.getDateTime().compareToIgnoreCase(t1.getDateTime()));
        mSavedThumbnail.setValue(list);
    }

    public void removeSavedThumbnail(Thumbnail savedThumbnail) {
        ArrayList<Thumbnail> list = getSavedThumbnail().getValue();
        if (list == null) {
            list = new ArrayList<>();
        }

        if (list.contains(savedThumbnail)) {
            list.remove(savedThumbnail);
            list.sort((t1, t2) -> t2.getDateTime().compareToIgnoreCase(t1.getDateTime()));
            mSavedThumbnail.setValue(list);
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

    public void onClickItem(ClickView type, Thumbnail thumbnail) {
        if (type == ClickView.SEARCH) {
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