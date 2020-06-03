package com.promise09th.mvvmproject.data.retrofit;

import com.promise09th.mvvmproject.common.KakaoApiKey;
import com.promise09th.mvvmproject.model.response.ImageThumbnailResponse;
import com.promise09th.mvvmproject.model.response.VideoThumbnailResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface KakaoRetrofitService {

    @Headers("Authorization: KakaoAK " + KakaoApiKey.REST_API_KEY)
    @GET("/v2/search/vclip")
    Call<VideoThumbnailResponse> requestVideoThumbnail(@Query("query") String query);

    @Headers("Authorization: KakaoAK " + KakaoApiKey.REST_API_KEY)
    @GET("/v2/search/image")
    Call<ImageThumbnailResponse> requestImageThumbnail(@Query("query") String query);
}
