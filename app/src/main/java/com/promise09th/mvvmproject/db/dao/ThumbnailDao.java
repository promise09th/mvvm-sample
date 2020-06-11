package com.promise09th.mvvmproject.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.promise09th.mvvmproject.db.model.ThumbnailEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface ThumbnailDao {

    @Query("SELECT * FROM ThumbnailEntity")
    public Flowable<List<ThumbnailEntity>> getAll();

    @Insert
    public Completable insertThumbnail(ThumbnailEntity thumbnailEntity);

    @Update
    public Completable updateThumbnail(ThumbnailEntity thumbnailEntity);

    @Delete
    public Completable deleteThumbnail(ThumbnailEntity... thumbnailEntities);
}
