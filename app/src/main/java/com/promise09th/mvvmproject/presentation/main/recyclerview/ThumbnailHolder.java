package com.promise09th.mvvmproject.presentation.main.recyclerview;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.promise09th.mvvmproject.common.OnThumbnailClickListener;
import com.promise09th.mvvmproject.databinding.ItemImageViewBinding;
import com.promise09th.mvvmproject.model.Thumbnail;
import com.squareup.picasso.Picasso;

public class ThumbnailHolder extends RecyclerView.ViewHolder {
    private ItemImageViewBinding mBinding;
    private OnThumbnailClickListener mItemClickListener;


    ThumbnailHolder(ItemImageViewBinding binding, OnThumbnailClickListener itemClickListener) {
        super(binding.getRoot());
        mBinding = binding;
        mItemClickListener = itemClickListener;
    }

    public void bind(Thumbnail thumbnail) {
        mBinding.setClickListener(mItemClickListener);
        mBinding.setThumbnail(thumbnail);
    }

    @BindingAdapter("thumbnailUrl")
    public static void loadImage(ImageView imageView, String thumbnailUrl) {
        Picasso.get().load(thumbnailUrl).into(imageView);
    }
}