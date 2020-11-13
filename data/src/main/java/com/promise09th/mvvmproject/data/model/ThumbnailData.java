package com.promise09th.mvvmproject.data.model;

import java.util.Objects;

public class ThumbnailData {

    private String name;
    private String dateTime;
    private String thumbnailUrl;
    private String type;

    private ThumbnailData(Builder builder) {
        this.name = builder.name;
        this.dateTime = builder.dateTime;
        this.thumbnailUrl = builder.thumbnailUrl;
        this.type = builder.type;
    }

    public static class Builder {
        private String name;
        private String dateTime;
        private String thumbnailUrl;
        private String type;

        public Builder() {

        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDateTIme(String dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public Builder setThumbnailUrl(String thumbnailUrl) {
            this.thumbnailUrl = thumbnailUrl;
            return this;
        }

        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public ThumbnailData build() {
            return new ThumbnailData(this);
        }
    }

    public String getName() {
        return name;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThumbnailData thumbnail = (ThumbnailData) o;
        return Objects.equals(name, thumbnail.name) &&
                Objects.equals(dateTime, thumbnail.dateTime) &&
                Objects.equals(thumbnailUrl, thumbnail.thumbnailUrl) &&
                Objects.equals(type, thumbnail.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dateTime, thumbnailUrl, type);
    }
}
