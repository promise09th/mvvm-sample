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
import com.promise09th.mvvmproject.databinding.FragmentSearchResultBinding;
import com.promise09th.mvvmproject.presentation.ViewModelFactory;
import com.promise09th.mvvmproject.presentation.main.recyclerview.ThumbnailAdapter;
import com.promise09th.mvvmproject.presentation.main.viewmodel.ThumbnailViewModel;
import com.promise09th.mvvmproject.presentation.model.Thumbnail;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class SearchResultFragment extends DaggerFragment implements PresetPosition {

    @Inject
    ViewModelFactory viewModelFactory;

    private ThumbnailViewModel thumbnailViewModel;
    private ThumbnailAdapter adapter;
    private FragmentSearchResultBinding binding;

    public static SearchResultFragment newInstance() {
        SearchResultFragment fragment = new SearchResultFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        thumbnailViewModel = new ViewModelProvider(getActivity(), viewModelFactory).get(ThumbnailViewModel.class);

        adapter = new ThumbnailAdapter(thumbnailViewModel, ViewType.SEARCH);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_result, container, false);
        binding.setViewModel(thumbnailViewModel);
        binding.searchResultRecyclerview.setAdapter(adapter);
        binding.setLifecycleOwner(getActivity());

        bindClicked();

        return binding.getRoot();
    }

    private void bindClicked() {
        thumbnailViewModel.getSearchResultItemClicked().observe(getViewLifecycleOwner(), thumbnailEvent -> {
            Thumbnail thumbnail = thumbnailEvent.getContentIfNotHandled();
            if (thumbnail == null) {
                return;
            }

            if (thumbnailViewModel.containsSavedThumbnail(thumbnail)) {
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
                            thumbnailViewModel.setSavedThumbnail(thumbnail))
                    .create();
            dialog.show();
        });
    }

    @Override
    public void clearPosition() {
        binding.searchResultRecyclerview.scrollToPosition(0);
    }
}