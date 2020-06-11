package com.promise09th.mvvmproject.presentation.injection.module;

import com.promise09th.mvvmproject.data.retrofit.KakaoRetrofitService;
import com.promise09th.mvvmproject.data.thumbnail.ThumbnailLocalDataSource;
import com.promise09th.mvvmproject.data.thumbnail.ThumbnailRemoteDataSource;
import com.promise09th.mvvmproject.db.database.AppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataSourceModule {

    @Singleton
    @Provides
    public ThumbnailLocalDataSource provideThumbnailLocalDataSource(AppDatabase database) {
        return new ThumbnailLocalDataSource(database);
    }

    @Singleton
    @Provides
    public ThumbnailRemoteDataSource provideThumbnailRemoteDataSource(KakaoRetrofitService kakaoService) {
        return new ThumbnailRemoteDataSource(kakaoService);
    }
}
