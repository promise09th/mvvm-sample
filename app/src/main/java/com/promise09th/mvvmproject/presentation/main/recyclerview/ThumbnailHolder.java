package com.promise09th.mvvmproject.presentation.main.recyclerview;

import androidx.recyclerview.widget.RecyclerView;

import com.promise09th.mvvmproject.common.ViewType;
import com.promise09th.mvvmproject.databinding.ItemImageViewBinding;
import com.promise09th.mvvmproject.presentation.model.Thumbnail;
import com.promise09th.mvvmproject.presentation.main.ThumbnailViewModel;

public class ThumbnailHolder extends RecyclerView.ViewHolder {
    private ItemImageViewBinding mBinding;
    private ThumbnailViewModel mThumbnailViewModel;
    private ViewType mType;

    ThumbnailHolder(ItemImageViewBinding binding, ThumbnailViewModel thumbnailViewModel, ViewType type) {
        super(binding.getRoot());
        mBinding = binding;

        mThumbnailViewModel = thumbnailViewModel;
        mType = type;
    }

    public void bind(Thumbnail thumbnail) {
        mBinding.setViewModel(mThumbnailViewModel);
        mBinding.setType(mType);
        mBinding.setThumbnail(thumbnail);
    }
}