package com.promise09th.mvvmproject.view.viewpager;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.promise09th.mvvmproject.R;
import com.promise09th.mvvmproject.view.main.MyLockerFragment;
import com.promise09th.mvvmproject.view.main.SearchResultFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private static int MAX_PAGE = 2;

    @StringRes
    private static final int[] TAB_TITLES = new int[] {
            R.string.tab_text_1,
            R.string.tab_text_2
    };
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

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
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return MAX_PAGE;
    }
}