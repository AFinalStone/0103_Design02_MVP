package com.design.mvp.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.design.mvp.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class IndexFragmentAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> mListFragment = new ArrayList<>();

    public IndexFragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }


    public void setNewData(List<BaseFragment> list) {
        if (mListFragment != null) {
            mListFragment.clear();
        }
        if (list != null) {
            mListFragment.addAll(list);
        }
        notifyDataSetChanged();
    }


    @Override
    public Fragment getItem(int position) {
        return mListFragment.get(position);
    }


    @Override
    public int getCount() {
        if (mListFragment == null) {
            return 0;
        }
        return mListFragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mListFragment.get(position).getPageTitle();
    }
}