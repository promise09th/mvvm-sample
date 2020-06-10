package com.promise09th.mvvmproject.data.thumbnail;

import com.promise09th.mvvmproject.model.thumbnail.Thumbnail;

import java.util.ArrayList;

import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class ThumbnailLocalDataSource implements ThumbnailDataSource {

    public ThumbnailLocalDataSource() {
        // Not Assigned any object
    }

    @Override
    public Single<ArrayList<Thumbnail>> getVideoThumbnail(String query) {
        // TODO : Use local database
        return Single.create(e -> e.onSuccess(new ArrayList<>()));
    }

    @Override
    public Single<ArrayList<Thumbnail>> getImageThumbnail(String query) {
        // TODO : Use local database
        return Single.create(e -> e.onSuccess(new ArrayList<>()));
    }
}
