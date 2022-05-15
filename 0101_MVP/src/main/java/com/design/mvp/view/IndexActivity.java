package com.design.mvp.view;

import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import com.design.mvp.R;
import com.design.mvp.base.BaseActivity;
import com.design.mvp.base.BaseFragment;
import com.design.mvp.view.cartoon.CartoonFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class IndexActivity extends BaseActivity<OnePushContract.Presenter> implements OnePushContract.View {

    TabLayout mTabLayout;
    List<BaseFragment> list = new ArrayList<BaseFragment>();
    IndexFragmentAdapter mAdapter;
    ViewPager mViewPager;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_index;
    }

    @Override
    protected OnePushContract.Presenter initPresenter() {
        return new OnePushPresenter(this, this);
    }

    @Override
    protected void initEventAndData(Bundle bundle) {
        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_pager);
        list.add(new CartoonFragment());
        mAdapter = new IndexFragmentAdapter(getSupportFragmentManager(), 0);
        mAdapter.setNewData(list);
        mViewPager.setAdapter(mAdapter);
        mPresenter.init();
        mTabLayout.setupWithViewPager(mViewPager);
    }


    @Override
    public void showFragmentList(List<BaseFragment> list) {
        mAdapter.setNewData(list);
    }

}
