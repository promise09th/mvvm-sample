package com.promise09th.mvvmproject.data.thumbnail;

import com.promise09th.mvvmproject.db.mapper.EntityMapper;
import com.promise09th.mvvmproject.db.database.AppDatabase;
import com.promise09th.mvvmproject.presentation.model.Thumbnail;

import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class ThumbnailLocalDataSource {

    private AppDatabase mAppDatabase;

    @Inject
    public ThumbnailLocalDataSource(AppDatabase appDatabase) {
        mAppDatabase = appDatabase;
    }

    public Flowable<ArrayList<Thumbnail>> getAllThumbnail() {
        return mAppDatabase.thumbnailDao().getAll()
                .map(list -> list.stream()
                        .map(EntityMapper::mapToThumbanil)
                        .collect(Collectors.toCollection(ArrayList::new)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable insertThumbnail(Thumbnail thumbnail) {
        return mAppDatabase.thumbnailDao().insertThumbnail(EntityMapper.mapToThumbanilEntity(thumbnail))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable deleteThumbnail(Thumbnail thumbnail) {
        return mAppDatabase.thumbnailDao().deleteThumbnail(EntityMapper.mapToThumbanilEntity(thumbnail))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
