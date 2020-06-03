package com.promise09th.mvvmproject.data.thumbnail;

import androidx.annotation.NonNull;

import com.promise09th.mvvmproject.model.thumbnail.Thumbnail;

import java.util.ArrayList;

import io.reactivex.Single;

public class ThumbnailRepository {

    private static ThumbnailRepository sInstance;

    private ThumbnailDataSource mLocalDataSource;
    private ThumbnailDataSource mRemoteDataSource;

    public static synchronized ThumbnailRepository getInstance(@NonNull ThumbnailDataSource local,
                                                          @NonNull ThumbnailDataSource remote) {
        if (sInstance == null) {
            sInstance = new ThumbnailRepository(local, remote);
        }
        return sInstance;
    }

    private ThumbnailRepository(@NonNull ThumbnailDataSource local,
                                @NonNull ThumbnailDataSource remote) {
        mLocalDataSource = local;
        mRemoteDataSource = remote;
    }

    public Single<ArrayList<Thumbnail>> getThumbnail(String query) {
        return Single.zip(getVideoThumbnail(query), getImageThumbnail(query), (image, video) -> {
            image.addAll(video);
            return image;
        });
    }

    private Single<ArrayList<Thumbnail>> getVideoThumbnail(String query) {
        return mRemoteDataSource.getVideoThumbnail(query);
    }

    private Single<ArrayList<Thumbnail>> getImageThumbnail(String query) {
        return mRemoteDataSource.getImageThumbnail(query);
    }
}
