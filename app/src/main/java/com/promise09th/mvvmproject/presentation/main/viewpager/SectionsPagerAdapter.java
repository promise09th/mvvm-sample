package com.promise09th.mvvmproject.presentation.main.viewpager;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.promise09th.mvvmproject.R;
import com.promise09th.mvvmproject.presentation.main.MyLockerFragment;
import com.promise09th.mvvmproject.presentation.main.SearchResultFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private static final int MAX_PAGE = 2;

    @StringRes
    private static final int[] TAB_TITLES = new int[] {
            R.string.tab_text_1,
            R.string.tab_text_2
    };
    private final Context context;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return SearchResultFragment.newInstance();
        } else { // position == 1
            return MyLockerFragment.newInstance();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return context.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return MAX_PAGE;
    }
}