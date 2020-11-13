package com.promise09th.mvvmproject.data.mapper;

import com.promise09th.mvvmproject.data.model.ThumbnailData;
import com.promise09th.mvvmproject.db.model.ThumbnailEntity;

public class EntityMapper {

    public static ThumbnailEntity mapToThumbanilEntity(ThumbnailData thumbnailData) {
        ThumbnailEntity entity = new ThumbnailEntity();
        entity.name = thumbnailData.getName();
        entity.dateTime = thumbnailData.getDateTime();
        entity.thumbnailUrl = thumbnailData.getThumbnailUrl();
        entity.type = thumbnailData.getType();
        return entity;
    }

    public static ThumbnailData mapToThumbanil(ThumbnailEntity thumbnailEntity) {
        return new ThumbnailData.Builder()
                .setName(thumbnailEntity.name)
                .setDateTIme(thumbnailEntity.dateTime)
                .setThumbnailUrl(thumbnailEntity.thumbnailUrl)
                .setType(thumbnailEntity.type)
                .build();
    }
}
