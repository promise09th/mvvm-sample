package com.promise09th.mvvmproject.data.source;

import com.promise09th.mvvmproject.data.mapper.EntityMapper;
import com.promise09th.mvvmproject.data.model.ThumbnailData;
import com.promise09th.mvvmproject.db.database.AppDatabase;

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

    private AppDatabase appDatabase;

    @Inject
    public ThumbnailLocalDataSource(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    public Flowable<ArrayList<ThumbnailData>> getAllThumbnail() {
        return appDatabase.thumbnailDao().getAll()
                .map(list -> list.stream()
                        .map(EntityMapper::mapToThumbanil)
                        .collect(Collectors.toCollection(ArrayList::new)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable insertThumbnail(ThumbnailData thumbnail) {
        return appDatabase.thumbnailDao().insertThumbnail(EntityMapper.mapToThumbanilEntity(thumbnail))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable deleteThumbnail(ThumbnailData thumbnail) {
        return appDatabase.thumbnailDao().deleteThumbnail(EntityMapper.mapToThumbanilEntity(thumbnail))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
