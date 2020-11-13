package com.promise09th.mvvmproject.presentation.common;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.promise09th.mvvmproject.R;
import com.promise09th.mvvmproject.presentation.model.Thumbnail;
import com.promise09th.mvvmproject.utils.Constants;
import com.squareup.picasso.Picasso;

public class CustomBindingAdapter {

    @BindingAdapter("thumbnailUrl")
    public static void loadImage(ImageView imageView, String thumbnailUrl) {
        Picasso.get().load(thumbnailUrl).into(imageView);
    }

    @BindingAdapter("bind_type")
    public static void bindType(TextView textView, Thumbnail thumbnail) {
        switch (thumbnail.getType()) {
            case Constants.VIDEO_TYPE:
                textView.setText(R.string.video);
                textView.setTextColor(textView.getContext().getColor(R.color.color_009ae1));
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
