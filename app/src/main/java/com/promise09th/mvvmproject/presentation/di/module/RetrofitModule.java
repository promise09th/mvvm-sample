package com.promise09th.mvvmproject.presentation.di.module;

import com.promise09th.mvvmproject.data.retrofit.KakaoRetrofitService;
import com.promise09th.mvvmproject.data.retrofit.RetrofitClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RetrofitModule {

    @Singleton
    @Provides
    public KakaoRetrofitService provideKakaoRetrofitService() {
        return RetrofitClient.getKakaoClient().create(KakaoRetrofitService.class);
    }
}
