package com.promise09th.mvvmproject.presentation.main;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
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

import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity {

    private ThumbnailViewModel mThumbnailViewModel;
    private ActivityMainBinding mBinding;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private CompositeDisposable mDisposables = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewModelFactory factory = ViewModelFactory.getInstance(this.getApplication());
        mThumbnailViewModel = new ViewModelProvider(this, factory).get(ThumbnailViewModel.class);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setLifecycleOwner(this);
        setSupportActionBar(mBinding.toolbar);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = mBinding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        mBinding.tabs.setupWithViewPager(viewPager);
    }

    @Override
    protected void onDestroy() {
        mDisposables.clear();
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
                mBinding.viewPager.setCurrentItem(0);
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
        mDisposables.add(mThumbnailViewModel.getThumbnail(input)
                .subscribe(
                        receivedThumbnail -> mThumbnailViewModel.setSearchResultThumbnail(receivedThumbnail),
                        e -> showErrorToast()));
    }

    private void showErrorToast() {
        mHandler.post(() ->
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