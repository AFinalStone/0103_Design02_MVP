package com.design.mvp.view.cartoon;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.design.mvp.R;
import com.design.mvp.base.BaseFragment;
import com.design.mvp.view.adapter.CartoonListAdapter;
import com.design.mvp.view.adapter.ICartoonItem;

import java.util.ArrayList;
import java.util.HashSet;

public class CartoonFragment extends BaseFragment<CartoonFragmentContract.Presenter> implements CartoonFragmentContract.View {

    public static CartoonFragment getInstance() {
        CartoonFragment cartoonFragment = new CartoonFragment();
        return cartoonFragment;
    }

    private RecyclerView mRvList;
    private CartoonListAdapter mAdapter;
    private String mTitle = "动漫列表";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment_cartoon;
    }

    @Override
    protected CartoonFragmentContract.Presenter initPresenter() {
        return new CartoonFragmentPresenter(mActivity, this);
    }

    @Override
    protected void initEventAndData(Bundle savedInstanceState) {
        mRvList = mContentView.findViewById(R.id.rv_list);
        mRvList.setLayoutManager(new LinearLayoutManager(mActivity));
        mAdapter = new CartoonListAdapter(getContext());
        mRvList.setAdapter(mAdapter);
        mPresenter.init();
    }

    @Override
    public CharSequence getPageTitle() {
        return mTitle;
    }


    @Override
    public void showList(ArrayList<ICartoonItem> list) {
        mContentView.findViewById(R.id.tv_content).setVisibility(View.GONE);
        mAdapter.setNewData(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateHaveReadIndexList(HashSet<String> hashSet) {
    }

    @Override
    public void showDataEmpty() {
        mContentView.findViewById(R.id.tv_content).setVisibility(View.VISIBLE);

    }
}
