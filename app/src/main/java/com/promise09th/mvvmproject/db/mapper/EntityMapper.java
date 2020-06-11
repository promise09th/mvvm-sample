package com.promise09th.mvvmproject.db.mapper;

import com.promise09th.mvvmproject.db.model.ThumbnailEntity;
import com.promise09th.mvvmproject.presentation.model.Thumbnail;

public class EntityMapper {

    public static ThumbnailEntity mapToThumbanilEntity(Thumbnail thumbnail) {
        ThumbnailEntity entity = new ThumbnailEntity();
        entity.name = thumbnail.getName();
        entity.dateTime = thumbnail.getDateTime();
        entity.thumbnailUrl = thumbnail.getThumbnailUrl();
        entity.type = thumbnail.getType();
        return entity;
    }

    public static Thumbnail mapToThumbanil(ThumbnailEntity entity) {
        return new Thumbnail.Builder()
                .setName(entity.name)
                .setDateTIme(entity.dateTime)
                .setThumbnailUrl(entity.thumbnailUrl)
                .setType(entity.type)
                .build();
    }
}
