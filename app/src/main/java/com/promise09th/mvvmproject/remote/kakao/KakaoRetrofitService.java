package com.promise09th.mvvmproject.remote.kakao;

import com.promise09th.mvvmproject.remote.model.image.ImageResponse;
import com.promise09th.mvvmproject.remote.model.video.VideoResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface KakaoRetrofitService {

    @Headers("Authorization: KakaoAK " + KakaoApiKey.REST_API_KEY)
    @GET("/v2/search/vclip")
    Single<VideoResponse> requestVideoThumbnail(@Query("query") String query);

    @Headers("Authorization: KakaoAK " + KakaoApiKey.REST_API_KEY)
    @GET("/v2/search/image")
    Single<ImageResponse> requestImageThumbnail(@Query("query") String query);
}
