package com.promise09th.mvvmproject.data.thumbnail;

import androidx.annotation.NonNull;

import com.promise09th.mvvmproject.presentation.model.Thumbnail;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Singleton
public class ThumbnailRepository {

    private ThumbnailLocalDataSource mLocalDataSource;
    private ThumbnailRemoteDataSource mRemoteDataSource;

    @Inject
    public ThumbnailRepository(@NonNull ThumbnailLocalDataSource local,
                                @NonNull ThumbnailRemoteDataSource remote) {
        mLocalDataSource = local;
        mRemoteDataSource = remote;
    }

    public Single<ArrayList<Thumbnail>> getThumbnail(String query) {
        return mRemoteDataSource.getAllThumbnail(query);
    }

    public Flowable<ArrayList<Thumbnail>> getAllThumbnail() {
        return mLocalDataSource.getAllThumbnail();
    }

    public Completable saveThumbnail(Thumbnail thumbnail) {
        return mLocalDataSource.insertThumbnail(thumbnail);
    }

    public Completable deleteThumbnail(Thumbnail thumbnail) {
        return mLocalDataSource.deleteThumbnail(thumbnail);
    }
}
