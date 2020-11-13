package com.promise09th.mvvmproject.presentation.main.recyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.promise09th.mvvmproject.databinding.ItemImageViewBinding;
import com.promise09th.mvvmproject.presentation.common.ViewType;
import com.promise09th.mvvmproject.presentation.main.viewmodel.ThumbnailViewModel;
import com.promise09th.mvvmproject.presentation.model.Thumbnail;

public class ThumbnailAdapter extends ListAdapter<Thumbnail, ThumbnailHolder> {

    private ThumbnailViewModel thumbnailViewModel;
    private ViewType viewType;

    public ThumbnailAdapter(ThumbnailViewModel thumbnailViewModel, ViewType viewType) {
        super(new AsyncDifferConfig.Builder<>(new DiffUtil.ItemCallback<Thumbnail>() {
            @Override
            public boolean areItemsTheSame(@NonNull Thumbnail old,
                                           @NonNull Thumbnail newItem) {
                return old.equals(newItem);
            }

            @Override
            public boolean areContentsTheSame(@NonNull Thumbnail old,
                                              @NonNull Thumbnail newItem) {
                return old.equals(newItem);
            }
        }).build());

        this.thumbnailViewModel = thumbnailViewModel;
        this.viewType = viewType;
    }

    @Override
    @NonNull
    public ThumbnailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemImageViewBinding binding = ItemImageViewBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ThumbnailHolder(binding, thumbnailViewModel, this.viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull ThumbnailHolder holder, int position) {
        holder.bind(getItem(position));
    }
}