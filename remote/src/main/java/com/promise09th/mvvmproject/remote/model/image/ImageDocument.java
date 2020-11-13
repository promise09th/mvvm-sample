package com.promise09th.mvvmproject.remote.model.image;

import com.google.gson.annotations.SerializedName;

public class ImageDocument {
    @SerializedName("collection")
    private String collection;

    @SerializedName("thumbnail_url")
    private String thumbnailUrl;

    @SerializedName("image_url")
    private String imageUrl;

    @SerializedName("mWidth")
    private int width;

    @SerializedName("height")
    private int height;

    @SerializedName("display_sitename")
    private String displaySiteName;

    @SerializedName("doc_url")
    private String docUrl;

    @SerializedName("datetime")
    private String dateTime;

    public ImageDocument() {

    }

    public String getCollection() {
        return this.collection;
    }

    public String getThumbnailUrl() {
        return this.thumbnailUrl;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public String getDisplaySiteName() {
        return this.displaySiteName;
    }

    public String getDocUrl() {
        return this.docUrl;
    }

    public String getDateTime() {
        return this.dateTime;
    }
}
