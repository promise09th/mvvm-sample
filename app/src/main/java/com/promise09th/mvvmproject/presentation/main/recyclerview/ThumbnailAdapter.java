package com.promise09th.mvvmproject.presentation.main.recyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.promise09th.mvvmproject.R;
import com.promise09th.mvvmproject.common.Constants;
import com.promise09th.mvvmproject.common.ViewType;
import com.promise09th.mvvmproject.databinding.ItemImageViewBinding;
import com.promise09th.mvvmproject.presentation.main.ThumbnailViewModel;
import com.promise09th.mvvmproject.presentation.model.Thumbnail;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ThumbnailAdapter extends RecyclerView.Adapter<ThumbnailHolder> {

    private List<Thumbnail> items = new ArrayList<>();

    private ThumbnailViewModel thumbnailViewModel;
    private ViewType viewType;

    public ThumbnailAdapter(ThumbnailViewModel thumbnailViewModel, ViewType viewType) {
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
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).hashCode();
    }

    public void setItem(List<Thumbnail> items) {
        if (items != null) {
            this.items.clear();
            this.items.addAll(items);
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

    @BindingAdapter("thumbnailUrl")
    public static void loadImage(ImageView imageView, String thumbnailUrl) {
        Picasso.get().load(thumbnailUrl).into(imageView);
    }

    @BindingAdapter("bind_type")
    public static void bindType(TextView textView, Thumbnail thumbnail) {
        switch (thumbnail.getType()) {
            case Constants.VIDEO_TYPE:
                textView.setText(R.string.video);
                textView.setTextColor(textView.getContext().getColor(R.color.color_39b4ee));
                break;
            case Constants.IMAGE_TYPE:
                textView.setText(R.string.image);
                textView.setTextColor(textView.getContext().getColor(R.color.color_f4754e));
                break;
            default:
                break;
        }
    }
}