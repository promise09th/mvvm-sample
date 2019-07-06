package com.promise09th.mvvmproject.view.main;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.promise09th.mvvmproject.R;
import com.promise09th.mvvmproject.common.OnClickItemListener;
import com.promise09th.mvvmproject.common.PresetPosition;
import com.promise09th.mvvmproject.databinding.FragmentSearchResultBinding;
import com.promise09th.mvvmproject.model.Thumbnail;
import com.promise09th.mvvmproject.view.recyclerview.ThumbnailAdapter;
import com.promise09th.mvvmproject.viewmodel.ThumbnailViewModel;

/**
 * A placeholder fragment containing a simple view.
 */
public class SearchResultFragment extends Fragment implements OnClickItemListener, PresetPosition {

    private ThumbnailViewModel mThumbnailViewModel;
    private ThumbnailAdapter mAdapter;
    private FragmentSearchResultBinding mBinding;

    public static SearchResultFragment newInstance() {
        SearchResultFragment fragment = new SearchResultFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mThumbnailViewModel = ViewModelProviders.of(getActivity()).get(ThumbnailViewModel.class);
        mAdapter = new ThumbnailAdapter(this);

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_result, container, false);
        mBinding.setViewModel(mThumbnailViewModel);
        mBinding.searchResultRecyclerview.setAdapter(mAdapter);
        mBinding.setLifecycleOwner(getActivity());
        return mBinding.getRoot();
    }

    @Override
    public void onClickItem(Thumbnail thumbnail) {
        if (mThumbnailViewModel.containsSavedThumbnail(thumbnail)) {
            AlertDialog dialog = new AlertDialog.Builder(getActivity())
                    .setTitle(R.string.note)
                    .setMessage(R.string.mylocker_thumbnail_already_added)
                    .setPositiveButton(R.string.btn_confirm, null)
                    .create();
            dialog.show();
            return;
        }

        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.note)
                .setMessage(R.string.mylocker_thumbnail_add)
                .setNegativeButton(R.string.btn_cancel, null)
                .setPositiveButton(R.string.btn_confirm, (dialogInterface, i) ->
                        mThumbnailViewModel.setSavedThumbnail(thumbnail))
                .create();
        dialog.show();
    }

    @Override
    public void clearPosition() {
        mBinding.searchResultRecyclerview.scrollToPosition(0);
    }
}