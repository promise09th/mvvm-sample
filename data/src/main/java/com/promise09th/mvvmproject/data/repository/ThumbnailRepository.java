package com.promise09th.mvvmproject.data.repository;

import androidx.annotation.NonNull;

import com.promise09th.mvvmproject.data.model.ThumbnailData;
import com.promise09th.mvvmproject.data.source.ThumbnailLocalDataSource;
import com.promise09th.mvvmproject.data.source.ThumbnailRemoteDataSource;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Singleton
public class ThumbnailRepository {

    private ThumbnailLocalDataSource localDataSource;
    private ThumbnailRemoteDataSource remoteDataSource;

    @Inject
    public ThumbnailRepository(@NonNull ThumbnailLocalDataSource local,
                                @NonNull ThumbnailRemoteDataSource remote) {
        localDataSource = local;
        remoteDataSource = remote;
    }

    public Single<ArrayList<ThumbnailData>> getThumbnail(String query) {
        return remoteDataSource.getAllThumbnail(query);
    }

    public Flowable<ArrayList<ThumbnailData>> getAllThumbnail() {
        return localDataSource.getAllThumbnail();
    }

    public Completable saveThumbnail(ThumbnailData thumbnail) {
        return localDataSource.insertThumbnail(thumbnail);
    }

    public Completable deleteThumbnail(ThumbnailData thumbnail) {
        return localDataSource.deleteThumbnail(thumbnail);
    }
}
