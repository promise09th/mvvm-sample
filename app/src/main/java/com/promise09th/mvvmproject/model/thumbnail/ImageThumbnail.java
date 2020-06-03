package com.promise09th.mvvmproject.model.thumbnail;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;
import com.promise09th.mvvmproject.R;

import java.util.Optional;

public class ImageThumbnail implements Thumbnail {
    @SerializedName("collection")
    private String mCollection;

    @SerializedName("thumbnail_url")
    private String mThumbnailUrl;

    @SerializedName("image_url")
    private String mImageUrl;

    @SerializedName("mWidth")
    private int mWidth;

    @SerializedName("height")
    private int mHeight;

    @SerializedName("display_sitename")
    private String mDisplaySiteName;

    @SerializedName("doc_url")
    private String mDocUrl;

    @SerializedName("datetime")
    private String mDateTime;

    public ImageThumbnail() {

    }

    @Override
    public String getName() {
        return Optional.ofNullable(mDisplaySiteName)
                .filter(t -> !TextUtils.isEmpty(t))
                .orElse("N/A");
    }

    @Override
    public String getDateTime() {
        return mDateTime;
    }

    @Override
    public String getThumbnailUrl() {
        return mThumbnailUrl;
    }

    @Override
    public String getType() {
        return "이미지";
    }

}
