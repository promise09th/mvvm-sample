package com.promise09th.mvvmproject.presentation.injection.module;

import com.promise09th.mvvmproject.remote.kakao.KakaoRetrofitService;
import com.promise09th.mvvmproject.remote.RetrofitClient;

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
