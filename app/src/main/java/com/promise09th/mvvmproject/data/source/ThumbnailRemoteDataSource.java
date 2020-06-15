package com.promise09th.mvvmproject.data.source;

import androidx.annotation.NonNull;

import com.promise09th.mvvmproject.remote.mapper.DocumentMapper;
import com.promise09th.mvvmproject.remote.kakao.KakaoRetrofitService;
import com.promise09th.mvvmproject.remote.model.image.ImageResponse;
import com.promise09th.mvvmproject.remote.model.video.VideoResponse;
import com.promise09th.mvvmproject.presentation.model.Thumbnail;

import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class ThumbnailRemoteDataSource {

    private KakaoRetrofitService kakaoService;

    @Inject
    public ThumbnailRemoteDataSource(KakaoRetrofitService kakaoService) {
        this.kakaoService = kakaoService;
    }

    public Single<ArrayList<Thumbnail>> getAllThumbnail(String query) {
        return Single.zip(
                getVideoThumbnail(query),
                getImageThumbnail(query),
                (image, video) -> {
                    image.addAll(video);
                    return image;
                });
    }

    private Single<ArrayList<Thumbnail>> getVideoThumbnail(String query) {
        return Single.create(e -> {
            Call<VideoResponse> imageThumbnailCall = kakaoService.requestVideoThumbnail(query);
            imageThumbnailCall.enqueue(new Callback<VideoResponse>() {
                @Override
                public void onResponse(@NonNull Call<VideoResponse> call, @NonNull Response<VideoResponse> response) {
                    VideoResponse videoResponse = response.body();
                    if (videoResponse != null) {
                        e.onSuccess(videoResponse.getDocuments().stream()
                                .map(DocumentMapper::mapToThumbanil)
                                .collect(Collectors.toCollection(ArrayList::new)));
                    } else {
                        e.onError(new Throwable());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<VideoResponse> call, @NonNull Throwable t) {
                    call.cancel();
                    e.onError(t);
                }
            });
        });
    }

    private Single<ArrayList<Thumbnail>> getImageThumbnail(String query) {
        return Single.create(e -> {
            Call<ImageResponse> imageThumbnailCall = kakaoService.requestImageThumbnail(query);
            imageThumbnailCall.enqueue(new Callback<ImageResponse>() {
                @Override
                public void onResponse(@NonNull Call<ImageResponse> call, @NonNull Response<ImageResponse> response) {
                    ImageResponse imageResponse = response.body();
                    if (imageResponse != null) {
                        e.onSuccess(imageResponse.getDocuments().stream()
                                .map(DocumentMapper::mapToThumbanil)
                                .collect(Collectors.toCollection(ArrayList::new)));
                    } else {
                        e.onError(new Throwable());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ImageResponse> call, @NonNull Throwable t) {
                    call.cancel();
                    e.onError(t);
                }
            });
        });
    }
}
