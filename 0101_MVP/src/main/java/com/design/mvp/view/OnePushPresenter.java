package com.design.mvp.view;

import android.content.Context;

import androidx.annotation.NonNull;

import com.design.mvp.base.BaseFragment;
import com.design.mvp.base.BasePresenter;
import com.design.mvp.view.empty.EmptyFragment;
import com.design.mvp.view.cartoon.CartoonFragment;

import java.util.ArrayList;
import java.util.List;

public class OnePushPresenter extends BasePresenter<OnePushContract.View> implements OnePushContract.Presenter {

    public OnePushPresenter(@NonNull Context context, @NonNull OnePushContract.View view) {
        super(context, view);
    }

    @Override
    public void init() {
        List<BaseFragment> list = new ArrayList<BaseFragment>();
        list.add(CartoonFragment.getInstance());
        list.add(EmptyFragment.getFragment("搜索"));
        list.add(EmptyFragment.getFragment("资讯"));
        list.add(EmptyFragment.getFragment("我的"));
        mView.showFragmentList(list);
    }
}
