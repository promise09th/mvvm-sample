package com.promise09th.mvvmproject.data.source;

import com.promise09th.mvvmproject.presentation.model.Thumbnail;
import com.promise09th.mvvmproject.remote.kakao.KakaoRetrofitService;
import com.promise09th.mvvmproject.remote.mapper.DocumentMapper;

import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

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
                (videos, images) -> {
                    ArrayList<Thumbnail> thumbnails = new ArrayList<>();
                    thumbnails.addAll(videos);
                    thumbnails.addAll(images);
                    return thumbnails;
                });
    }

    private Single<ArrayList<Thumbnail>> getVideoThumbnail(String query) {
        return kakaoService.requestVideoThumbnail(query)
                .map(videoResponse -> videoResponse.getDocuments().stream()
                        .map(DocumentMapper::mapToThumbanil)
                        .collect(Collectors.toCollection(ArrayList::new)));
    }

    private Single<ArrayList<Thumbnail>> getImageThumbnail(String query) {
        return kakaoService.requestImageThumbnail(query)
                .map(imageResponse -> imageResponse.getDocuments().stream()
                        .map(DocumentMapper::mapToThumbanil)
                        .collect(Collectors.toCollection(ArrayList::new)));
    }
}
