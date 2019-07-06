package com.promise09th.mvvmproject.view.recyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.promise09th.mvvmproject.common.OnClickItemListener;
import com.promise09th.mvvmproject.databinding.ItemImageViewBinding;
import com.promise09th.mvvmproject.model.Thumbnail;

import java.util.ArrayList;
import java.util.List;

public class ThumbnailAdapter extends RecyclerView.Adapter<ThumbnailHolder> {
    private List<Thumbnail> mItems;
    private OnClickItemListener mClickListener;

    public ThumbnailAdapter(OnClickItemListener clickListener) {
        mItems = new ArrayList<>();
        mClickListener = clickListener;
    }

    @Override
    @NonNull
    public ThumbnailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemImageViewBinding binding = ItemImageViewBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ThumbnailHolder(binding, mClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ThumbnailHolder holder, int position) {
        holder.bind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public long getItemId(int position) {
        return mItems.get(position).hashCode();
    }

    public void setItem(List<Thumbnail> items) {
        if (items != null) {
            mItems.clear();
            mItems.addAll(items);
            notifyDataSetChanged();
        }
    }

    @BindingAdapter("item")
    public static void bindItems(RecyclerView recyclerView, ArrayList<Thumbnail> items) {
        ThumbnailAdapter adapter = (ThumbnailAdapter)recyclerView.getAdapter();
        if (adapter != null) {
            adapter.setItem(items);
        }
    }
}