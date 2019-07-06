package com.promise09th.mvvmproject.data;

import com.promise09th.mvvmproject.model.Thumbnail;

import java.util.ArrayList;

import io.reactivex.Single;

public class ThumbnailRepository {
    public static Single<ArrayList<Thumbnail>> getThumbnail(String query) {
        return Single.zip(getVideoThumbnail(query), getImageThumbnail(query), (image, video) -> {
            image.addAll(video);
            return image;
        });
    }

    private static Single<ArrayList<Thumbnail>> getVideoThumbnail(String query) {
        return RestApiHelper.getVideoThumbnail(query);
    }

    private static Single<ArrayList<Thumbnail>> getImageThumbnail(String query) {
        return RestApiHelper.getImageThumbnail(query);
    }
}
