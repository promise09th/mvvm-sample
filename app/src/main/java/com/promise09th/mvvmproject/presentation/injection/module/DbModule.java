package com.promise09th.mvvmproject.presentation.injection.module;

import android.app.Application;

import androidx.room.Room;

import com.promise09th.mvvmproject.db.database.AppDatabase;

import dagger.Module;
import dagger.Provides;

@Module
public class DbModule {

    @Provides
    public AppDatabase provideDatabase(Application application) {
        return Room.databaseBuilder(
                application.getApplicationContext(),
                AppDatabase.class, "Mvvm.db")
                .build();
    }
}
