package com.promise09th.mvvmproject.data.source;

import com.promise09th.mvvmproject.data.mapper.DocumentMapper;
import com.promise09th.mvvmproject.data.model.ThumbnailData;
import com.promise09th.mvvmproject.remote.kakao.KakaoRetrofitService;
import com.promise09th.mvvmproject.remote.model.ThumbnailRemote;

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

    public Single<ArrayList<ThumbnailData>> getAllThumbnail(String query) {
        return Single.zip(
                getVideoThumbnail(query),
                getImageThumbnail(query),
                (videos, images) -> {
                    ArrayList<ThumbnailData> thumbnails = new ArrayList<>();
                    thumbnails.addAll(videos);
                    thumbnails.addAll(images);
                    return thumbnails;
                });
    }

    private Single<ArrayList<ThumbnailData>> getVideoThumbnail(String query) {
        return kakaoService.requestVideoThumbnail(query)
                .map(videoResponse -> videoResponse.getDocuments().stream()
                        .map(DocumentMapper::mapToThumbanilData)
                        .collect(Collectors.toCollection(ArrayList::new)));
    }

    private Single<ArrayList<ThumbnailData>> getImageThumbnail(String query) {
        return kakaoService.requestImageThumbnail(query)
                .map(imageResponse -> imageResponse.getDocuments().stream()
                        .map(DocumentMapper::mapToThumbanilData)
                        .collect(Collectors.toCollection(ArrayList::new)));
    }
}
