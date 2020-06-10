package com.promise09th.mvvmproject.data.thumbnail;

import androidx.annotation.NonNull;

import com.promise09th.mvvmproject.data.retrofit.KakaoRetrofitService;
import com.promise09th.mvvmproject.model.response.ImageThumbnailResponse;
import com.promise09th.mvvmproject.model.response.VideoThumbnailResponse;
import com.promise09th.mvvmproject.model.thumbnail.Thumbnail;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class ThumbnailRemoteDataSource implements ThumbnailDataSource {

    private KakaoRetrofitService mKakaoService;

    @Inject
    public ThumbnailRemoteDataSource(KakaoRetrofitService kakaoService) {
        mKakaoService = kakaoService;
    }

    @Override
    public Single<ArrayList<Thumbnail>> getVideoThumbnail(String query) {
        return Single.create(e -> {
            Call<VideoThumbnailResponse> imageThumbnailCall = mKakaoService.requestVideoThumbnail(query);
            imageThumbnailCall.enqueue(new Callback<VideoThumbnailResponse>() {
                @Override
                public void onResponse(@NonNull Call<VideoThumbnailResponse> call, @NonNull Response<VideoThumbnailResponse> response) {
                    VideoThumbnailResponse imageThumbnailResponse = response.body();
                    if (imageThumbnailResponse != null) {
                        e.onSuccess(new ArrayList<>(imageThumbnailResponse.getDocuments()));
                    } else {
                        e.onError(new Throwable());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<VideoThumbnailResponse> call, @NonNull Throwable t) {
                    call.cancel();
                    e.onError(t);
                }
            });
        });
    }

    @Override
    public Single<ArrayList<Thumbnail>> getImageThumbnail(String query) {
        return Single.create(e -> {
            Call<ImageThumbnailResponse> imageThumbnailCall = mKakaoService.requestImageThumbnail(query);
            imageThumbnailCall.enqueue(new Callback<ImageThumbnailResponse>() {
                @Override
                public void onResponse(@NonNull Call<ImageThumbnailResponse> call, @NonNull Response<ImageThumbnailResponse> response) {
                    ImageThumbnailResponse imageThumbnailResponse = response.body();
                    if (imageThumbnailResponse != null) {
                        e.onSuccess(new ArrayList<>(imageThumbnailResponse.getDocuments()));
                    } else {
                        e.onError(new Throwable());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ImageThumbnailResponse> call, @NonNull Throwable t) {
                    call.cancel();
                    e.onError(t);
                }
            });
        });
    }
}
