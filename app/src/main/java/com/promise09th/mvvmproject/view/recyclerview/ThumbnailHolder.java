package com.promise09th.mvvmproject.view.recyclerview;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.promise09th.mvvmproject.common.OnClickItemListener;
import com.promise09th.mvvmproject.databinding.ItemImageViewBinding;
import com.promise09th.mvvmproject.model.Thumbnail;
import com.squareup.picasso.Picasso;

public class ThumbnailHolder extends RecyclerView.ViewHolder {
    private ItemImageViewBinding mBinding;
    private OnClickItemListener mClickListener;

    ThumbnailHolder(ItemImageViewBinding binding, OnClickItemListener clickListener) {
        super(binding.getRoot());
        mBinding = binding;
        mClickListener = clickListener;
    }

    public void bind(Thumbnail thumbnail) {
        mBinding.setHolder(this);
        mBinding.setThumbnail(thumbnail);
    }

    public void onClickItem(Thumbnail thumbnail) {
        mClickListener.onClickItem(thumbnail);
    }

    @BindingAdapter("thumbnailUrl")
    public static void loadImage(ImageView imageView, String thumbnailUrl) {
        Picasso.get().load(thumbnailUrl).into(imageView);
    }
}