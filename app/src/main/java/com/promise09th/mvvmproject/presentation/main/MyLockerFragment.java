package com.promise09th.mvvmproject.presentation.main;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.promise09th.mvvmproject.R;
import com.promise09th.mvvmproject.common.PresetPosition;
import com.promise09th.mvvmproject.databinding.FragmentMyLockerBinding;
import com.promise09th.mvvmproject.model.thumbnail.Thumbnail;
import com.promise09th.mvvmproject.presentation.ViewModelFactory;
import com.promise09th.mvvmproject.presentation.main.recyclerview.ThumbnailAdapter;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * A placeholder fragment containing a simple view.
 */
public class MyLockerFragment extends Fragment implements PresetPosition {

    @Inject
    ViewModelFactory mViewModelFactory;

    private ThumbnailViewModel mThumbnailViewModel;
    private ThumbnailAdapter mAdapter;
    private FragmentMyLockerBinding mBinding;

    public static MyLockerFragment newInstance() {
        MyLockerFragment fragment = new MyLockerFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        AndroidSupportInjection.inject(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mThumbnailViewModel = new ViewModelProvider(getActivity(), mViewModelFactory).get(ThumbnailViewModel.class);

        mAdapter = new ThumbnailAdapter(mThumbnailViewModel, ThumbnailViewModel.ClickView.LOCKER);

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_locker, container, false);
        mBinding.setViewModel(mThumbnailViewModel);
        mBinding.mylockerRecyclerview.setAdapter(mAdapter);
        mBinding.setLifecycleOwner(getActivity());

        bindClicked();

        return mBinding.getRoot();
    }

    private void bindClicked() {
        mThumbnailViewModel.getMyLockerItemClicked().observe(getViewLifecycleOwner(), thumbnailEvent -> {
            Thumbnail thumbnail = thumbnailEvent.getContentIfNotHandled();
            if (thumbnail == null) {
                return;
            }

            AlertDialog dialog = new AlertDialog.Builder(getActivity())
                    .setTitle(R.string.note)
                    .setMessage(R.string.mylocker_thumbnail_delete)
                    .setNegativeButton(R.string.btn_cancel, null)
                    .setPositiveButton(R.string.btn_confirm, (dialogInterface, i) ->
                            mThumbnailViewModel.removeSavedThumbnail(thumbnail))
                    .create();
            dialog.show();
        });
    }

    @Override
    public void clearPosition() {
        mBinding.mylockerRecyclerview.scrollToPosition(0);
    }
}