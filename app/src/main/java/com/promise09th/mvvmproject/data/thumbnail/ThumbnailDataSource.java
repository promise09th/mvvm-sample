package com.promise09th.mvvmproject.data.thumbnail;

import com.promise09th.mvvmproject.model.Thumbnail;

import java.util.ArrayList;

import io.reactivex.Single;

public interface ThumbnailDataSource {
    Single<ArrayList<Thumbnail>> getVideoThumbnail(String query);
    Single<ArrayList<Thumbnail>> getImageThumbnail(String query);
}
