package com.promise09th.mvvmproject.presentation.main.recyclerview;

import androidx.recyclerview.widget.RecyclerView;

import com.promise09th.mvvmproject.common.ViewType;
import com.promise09th.mvvmproject.databinding.ItemImageViewBinding;
import com.promise09th.mvvmproject.presentation.model.Thumbnail;
import com.promise09th.mvvmproject.presentation.main.ThumbnailViewModel;

public class ThumbnailHolder extends RecyclerView.ViewHolder {
    private ItemImageViewBinding binding;
    private ThumbnailViewModel thumbnailViewModel;
    private ViewType viewType;

    ThumbnailHolder(ItemImageViewBinding binding, ThumbnailViewModel thumbnailViewModel, ViewType viewType) {
        super(binding.getRoot());
        this.binding = binding;

        this.thumbnailViewModel = thumbnailViewModel;
        this.viewType = viewType;
    }

    public void bind(Thumbnail thumbnail) {
        binding.setViewModel(thumbnailViewModel);
        binding.setType(viewType);
        binding.setThumbnail(thumbnail);
    }
}