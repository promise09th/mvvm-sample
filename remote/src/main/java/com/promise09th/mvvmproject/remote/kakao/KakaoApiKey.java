package com.promise09th.mvvmproject.remote.kakao;

import com.promise09th.mvvmproject.remote.BuildConfig;

public class KakaoApiKey {
    public static final String REST_API_KEY = BuildConfig.KAKAO_REST_API_KEY;
    public static final String HEADER = "Authorization: KakaoAK " + REST_API_KEY;
}
