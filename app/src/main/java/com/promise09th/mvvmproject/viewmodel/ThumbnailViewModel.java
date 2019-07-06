package com.promise09th.mvvmproject.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.promise09th.mvvmproject.data.ThumbnailRepository;
import com.promise09th.mvvmproject.model.Thumbnail;

import java.util.ArrayList;

import io.reactivex.Single;

public class ThumbnailViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Thumbnail>> mSearchResultThumbnail = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Thumbnail>> mSavedThumbnail = new MutableLiveData<>();

    public void setSearchResultThumbnail(Thumbnail receiveThumbnail) {
        ArrayList<Thumbnail> list = getSearchResultThumbnail().getValue();
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(receiveThumbnail);
        list.sort((t1, t2) -> t2.getDateTime().compareToIgnoreCase(t1.getDateTime()));
        mSearchResultThumbnail.setValue(list);
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

    public void setSavedThumbnail(ArrayList<Thumbnail> savedThumbnail) {
        savedThumbnail.sort((t1, t2) -> t2.getDateTime().compareToIgnoreCase(t1.getDateTime()));
        mSavedThumbnail.setValue(savedThumbnail);
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
        return ThumbnailRepository.getThumbnail(query);
    }
}