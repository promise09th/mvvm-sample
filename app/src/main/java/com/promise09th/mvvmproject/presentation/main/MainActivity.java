package com.promise09th.mvvmproject.presentation.main;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.promise09th.mvvmproject.R;
import com.promise09th.mvvmproject.common.PresetPosition;
import com.promise09th.mvvmproject.databinding.ActivityMainBinding;
import com.promise09th.mvvmproject.presentation.ViewModelFactory;
import com.promise09th.mvvmproject.presentation.main.viewpager.SectionsPagerAdapter;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends DaggerAppCompatActivity {

    @Inject
    ViewModelFactory viewModelFactory;

    private ThumbnailViewModel thumbnailViewModel;
    private ActivityMainBinding binding;
    private Handler handler = new Handler(Looper.getMainLooper());
    private CompositeDisposable disposables = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        thumbnailViewModel = new ViewModelProvider(this, viewModelFactory).get(ThumbnailViewModel.class);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);
        setSupportActionBar(binding.toolbar);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        binding.tabs.setupWithViewPager(viewPager);
    }

    @Override
    protected void onDestroy() {
        disposables.clear();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchView searchView = (SearchView)menu.findItem(R.id.action_search).getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint(getString(R.string.search_view_hint));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String input) {
                getThumbnail(input);
                binding.viewPager.setCurrentItem(0);
                clearFragmentRecyclerviewPosition();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String input) {
                return false;
            }
        });
        return true;
    }

    private void getThumbnail(String input) {
        disposables.add(thumbnailViewModel.getThumbnail(input)
                .subscribe(
                        receivedThumbnail -> thumbnailViewModel.setSearchResultThumbnail(receivedThumbnail),
                        e -> showErrorToast()));
    }

    private void showErrorToast() {
        handler.post(() ->
                Toast.makeText(MainActivity.this, R.string.thumbnail_error, Toast.LENGTH_SHORT).show());
    }

    private void clearFragmentRecyclerviewPosition() {
        List<Fragment> fragments = MainActivity.this.getSupportFragmentManager().getFragments();
        for (Fragment f : fragments) {
            if (f instanceof PresetPosition) {
                ((PresetPosition)f).clearPosition();
            }
        }
    }
}