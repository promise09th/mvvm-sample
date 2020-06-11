package com.promise09th.mvvmproject.model.thumbnail;

public class Thumbnail {

    private String name;
    private String dateTime;
    private String thumbnailUrl;
    private String type;

    private Thumbnail(Thumbnail.Builder builder) {
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

        public Thumbnail build() {
            return new Thumbnail(this);
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
}
