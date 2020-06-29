package com.promise09th.mvvmproject.remote.mapper;

import android.text.TextUtils;

import com.promise09th.mvvmproject.presentation.common.Constants;
import com.promise09th.mvvmproject.remote.model.image.ImageDocument;
import com.promise09th.mvvmproject.presentation.model.Thumbnail;
import com.promise09th.mvvmproject.remote.model.video.VideoDocument;

import java.util.Optional;

public class DocumentMapper {

    public static Thumbnail mapToThumbanil(ImageDocument imageDocument) {
        return new Thumbnail.Builder()
                .setName(Optional.ofNullable(imageDocument.getDisplaySiteName())
                        .filter(t -> !TextUtils.isEmpty(t))
                        .orElse("N/A"))
                .setDateTIme(imageDocument.getDateTime())
                .setThumbnailUrl(imageDocument.getThumbnailUrl())
                .setType(Constants.IMAGE_TYPE)
                .build();
    }

    public static Thumbnail mapToThumbanil(VideoDocument videoDocument) {
        return new Thumbnail.Builder()
                .setName(Optional.ofNullable(videoDocument.getTitle())
                        .filter(t -> !TextUtils.isEmpty(t))
                        .orElse("N/A"))
                .setDateTIme(videoDocument.getDateTime())
                .setThumbnailUrl(videoDocument.getThumbnailUrl())
                .setType(Constants.VIDEO_TYPE)
                .build();
    }


}
