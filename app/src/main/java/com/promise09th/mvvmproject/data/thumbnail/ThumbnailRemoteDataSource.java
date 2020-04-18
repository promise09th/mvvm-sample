package com.promise09th.mvvmproject.data.thumbnail;

import com.promise09th.mvvmproject.data.RestApiHelper;
import com.promise09th.mvvmproject.model.Thumbnail;

import java.util.ArrayList;

import io.reactivex.Single;

public class ThumbnailRemoteDataSource implements ThumbnailDataSource{
    private static ThumbnailRemoteDataSource sInstance;

    public static synchronized ThumbnailRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new ThumbnailRemoteDataSource();
        }
        return sInstance;
    }

    private ThumbnailRemoteDataSource() {
        // Not Assigned any object
    }

    @Override
    public Single<ArrayList<Thumbnail>> getVideoThumbnail(String query) {
        return RestApiHelper.getVideoThumbnail(query);
    }

    @Override
    public Single<ArrayList<Thumbnail>> getImageThumbnail(String query) {
        return RestApiHelper.getImageThumbnail(query);
    }
}
