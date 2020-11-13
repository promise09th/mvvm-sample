package com.promise09th.mvvmproject.presentation.model;

import com.promise09th.mvvmproject.data.model.ThumbnailData;
import com.promise09th.mvvmproject.domain.model.ThumbnailDomain;

public class PresentationMapper {

    public static ThumbnailDomain mapToThumbnailDomain(Thumbnail thumbnail) {
        return new ThumbnailDomain.Builder()
                .setName(thumbnail.getName())
                .setDateTIme(thumbnail.getDateTime())
                .setThumbnailUrl(thumbnail.getThumbnailUrl())
                .setType(thumbnail.getType())
                .build();
    }

    public static Thumbnail mapToThumbnail(ThumbnailDomain thumbnailDomain) {
        return new Thumbnail.Builder()
                .setName(thumbnailDomain.getName())
                .setDateTIme(thumbnailDomain.getDateTime())
                .setThumbnailUrl(thumbnailDomain.getThumbnailUrl())
                .setType(thumbnailDomain.getType())
                .build();
    }
}
