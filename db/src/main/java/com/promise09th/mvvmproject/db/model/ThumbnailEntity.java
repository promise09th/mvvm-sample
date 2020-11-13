package com.promise09th.mvvmproject.db.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class ThumbnailEntity {

    @PrimaryKey
    @NonNull
    public String thumbnailUrl;

    public String name;
    public String dateTime;
    public String type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThumbnailEntity that = (ThumbnailEntity) o;
        return thumbnailUrl.equals(that.thumbnailUrl) &&
                Objects.equals(name, that.name) &&
                Objects.equals(dateTime, that.dateTime) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(thumbnailUrl, name, dateTime, type);
    }
}
