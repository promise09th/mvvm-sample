package com.promise09th.mvvmproject.db.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.promise09th.mvvmproject.db.dao.ThumbnailDao;
import com.promise09th.mvvmproject.db.model.ThumbnailEntity;

@Database(entities = {ThumbnailEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ThumbnailDao thumbnailDao();
}
