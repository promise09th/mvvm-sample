package com.promise09th.mvvmproject.common;

import com.promise09th.mvvmproject.model.thumbnail.Thumbnail;

@FunctionalInterface
public interface OnThumbnailClickListener {
    void onClickItem(Thumbnail thumbnail);
}
