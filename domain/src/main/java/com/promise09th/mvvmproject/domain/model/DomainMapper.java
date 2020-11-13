package com.promise09th.mvvmproject.domain.model;

import android.text.TextUtils;

import com.promise09th.mvvmproject.data.model.ThumbnailData;

import java.util.Optional;

public class DomainMapper {

    public static ThumbnailDomain mapToThumbnailDomain(ThumbnailData thumbnailData) {
        return new ThumbnailDomain.Builder()
                .setName(thumbnailData.getName())
                .setDateTIme(thumbnailData.getDateTime())
                .setThumbnailUrl(thumbnailData.getThumbnailUrl())
                .setType(thumbnailData.getType())
                .build();
    }

    public static ThumbnailData mapToThumbnailData(ThumbnailDomain thumbnailDomain) {
        return new ThumbnailData.Builder()
                .setName(thumbnailDomain.getName())
                .setDateTIme(thumbnailDomain.getDateTime())
                .setThumbnailUrl(thumbnailDomain.getThumbnailUrl())
                .setType(thumbnailDomain.getType())
                .build();
    }
}
