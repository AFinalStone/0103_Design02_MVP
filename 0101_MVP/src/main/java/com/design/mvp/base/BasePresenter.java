package com.design.mvp.base;

import android.content.Context;

import androidx.annotation.NonNull;

/**
 * MVP的基础Presenter对象
 *
 * @author syl
 * @time 2020/3/16 18:48
 */

public class BasePresenter<T extends BaseMVPContract.BaseView> implements BaseMVPContract.BasePresenter {

    protected Context mContext;
    protected T mView;

    public BasePresenter(@NonNull Context context, @NonNull T view) {
        mView = view;
        mContext = context;
    }

    public T getView() {
        return mView;
    }

    @Override
    public void onViewCreated() {

    }

    @Override
    public void onDestroyView() {

    }

    public void onDestroy() {

    }
}