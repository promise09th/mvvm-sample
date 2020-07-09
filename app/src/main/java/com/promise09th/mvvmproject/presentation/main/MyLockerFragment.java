package com.promise09th.mvvmproject.presentation.main;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.promise09th.mvvmproject.R;
import com.promise09th.mvvmproject.presentation.common.PresetPosition;
import com.promise09th.mvvmproject.presentation.common.ViewType;
import com.promise09th.mvvmproject.databinding.FragmentMyLockerBinding;
import com.promise09th.mvvmproject.presentation.ViewModelFactory;
import com.promise09th.mvvmproject.presentation.main.recyclerview.ThumbnailAdapter;
import com.promise09th.mvvmproject.presentation.main.viewmodel.ThumbnailViewModel;
import com.promise09th.mvvmproject.presentation.model.Thumbnail;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class MyLockerFragment extends DaggerFragment implements PresetPosition {

    @Inject
    ViewModelFactory viewModelFactory;

    private ThumbnailViewModel thumbnailViewModel;
    private ThumbnailAdapter adapter;
    private FragmentMyLockerBinding binding;

    public static MyLockerFragment newInstance() {
        MyLockerFragment fragment = new MyLockerFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        thumbnailViewModel = new ViewModelProvider(getActivity(), viewModelFactory).get(ThumbnailViewModel.class);

        adapter = new ThumbnailAdapter(thumbnailViewModel, ViewType.LOCKER);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_locker, container, false);
        binding.mylockerRecyclerview.setAdapter(adapter);
        binding.setLifecycleOwner(getActivity());

        bindClicked();

        thumbnailViewModel.fetchMyLockerThumbnails();

        return binding.getRoot();
    }

    private void bindClicked() {
        thumbnailViewModel.getMyLockerItemClicked().observe(getViewLifecycleOwner(), thumbnailEvent -> {
            Thumbnail thumbnail = thumbnailEvent.getContentIfNotHandled();
            if (thumbnail == null) {
                return;
            }
            showDeleteItemDialog(thumbnail);
        });

        thumbnailViewModel.getSavedThumbnail().observe(getViewLifecycleOwner(),
                list -> adapter.submitList(list));
    }

    private void showDeleteItemDialog(Thumbnail thumbnail) {
        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.note)
                .setMessage(R.string.mylocker_thumbnail_delete)
                .setNegativeButton(R.string.btn_cancel, null)
                .setPositiveButton(R.string.btn_confirm, (dialogInterface, i) ->
                        thumbnailViewModel.removeSavedThumbnail(thumbnail))
                .create();
        dialog.show();
    }

    @Override
    public void clearPosition() {
        binding.mylockerRecyclerview.scrollToPosition(0);
    }
}