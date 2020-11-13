package com.promise09th.mvvmproject.data.mapper;

import android.text.TextUtils;

import com.promise09th.mvvmproject.data.model.ThumbnailData;
import com.promise09th.mvvmproject.remote.model.ThumbnailRemote;
import com.promise09th.mvvmproject.remote.model.image.ImageDocument;
import com.promise09th.mvvmproject.remote.model.video.VideoDocument;
import com.promise09th.mvvmproject.utils.Constants;

import java.util.Optional;

public class DocumentMapper {

    public static ThumbnailData mapToThumbanilData(ImageDocument imageDocument) {
        return new ThumbnailData.Builder()
                .setName(Optional.ofNullable(imageDocument.getDisplaySiteName())
                        .filter(t -> !TextUtils.isEmpty(t))
                        .orElse("N/A"))
                .setDateTIme(imageDocument.getDateTime())
                .setThumbnailUrl(imageDocument.getThumbnailUrl())
                .setType(Constants.IMAGE_TYPE)
                .build();
    }

    public static ThumbnailData mapToThumbanilData(VideoDocument videoDocument) {
        return new ThumbnailData.Builder()
                .setName(Optional.ofNullable(videoDocument.getTitle())
                        .filter(t -> !TextUtils.isEmpty(t))
                        .orElse("N/A"))
                .setDateTIme(videoDocument.getDateTime())
                .setThumbnailUrl(videoDocument.getThumbnailUrl())
                .setType(Constants.VIDEO_TYPE)
                .build();
    }
}
