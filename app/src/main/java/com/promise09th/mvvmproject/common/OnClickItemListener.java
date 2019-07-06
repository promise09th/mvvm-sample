package com.promise09th.mvvmproject.common;

import com.promise09th.mvvmproject.model.Thumbnail;

@FunctionalInterface
public interface OnClickItemListener {
    void onClickItem(Thumbnail thumbnail);
}
