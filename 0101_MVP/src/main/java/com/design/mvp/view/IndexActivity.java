package com.design.mvp.view;

import android.os.Bundle;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.design.mvp.R;
import com.design.mvp.base.BaseActivity;
import com.design.mvp.base.BaseFragment;
import com.design.mvp.view.cartoon.CartoonFragment;

import java.util.ArrayList;
import java.util.List;


public class IndexActivity extends BaseActivity<OnePushContract.Presenter> implements OnePushContract.View, View.OnClickListener {

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
        findViewById(R.id.btn_home).setOnClickListener(this);
        findViewById(R.id.btn_search).setOnClickListener(this);
        findViewById(R.id.btn_info).setOnClickListener(this);
        findViewById(R.id.btn_my).setOnClickListener(this);
        mViewPager = findViewById(R.id.view_pager);
        list.add(new CartoonFragment());
        mAdapter = new IndexFragmentAdapter(getSupportFragmentManager(), 0);
        mAdapter.setNewData(list);
        mViewPager.setAdapter(mAdapter);
        mPresenter.init();
    }


    @Override
    public void showFragmentList(List<BaseFragment> list) {
        mAdapter.setNewData(list);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_home:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.btn_search:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.btn_info:
                mViewPager.setCurrentItem(2);
                break;
            case R.id.btn_my:
                mViewPager.setCurrentItem(3);
                break;
        }
    }
}
