package com.design.mvp.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


/**
 * @author syl
 * @time 2019/6/22 5:06 PM
 */

public abstract class BaseFragment<T extends BaseMVPContract.BasePresenter> extends Fragment implements BaseMVPContract.BaseView {

    protected View mContentView;
    protected T mPresenter;
    protected Activity mActivity;

    /**
     * 获取当前页面的layout id
     *
     * @return
     */
    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * 创建Presenter
     *
     * @return
     */
    protected abstract T initPresenter();

    /**
     * 初始化事件和数据
     *
     * @param savedInstanceState
     */
    protected abstract void initEventAndData(Bundle savedInstanceState);

    /**
     * 这个方法主要是为了ViewPager的适配器
     *
     * @return
     */
    public CharSequence getPageTitle() {
        return "";
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mActivity = getActivity();
        //如果是在ViewPager里，或者Fragment恢复现场，不需要重新创建View
        if (mContentView == null) {
            mContentView = inflater.inflate(getLayoutId(), container, false);
            mContentView.setClickable(true);
        }
        ViewGroup parent = (ViewGroup) mContentView.getParent();
        if (parent != null) {
            parent.removeView(mContentView);
        }
        return mContentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //如果已经创建过Presenter，则不重新创建了
        if (mPresenter == null) {
            mPresenter = initPresenter();
        }
        initEventAndData(savedInstanceState);
        if (mPresenter != null)
            mPresenter.onViewCreated();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.onDestroyView();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
    }

    @Override
    public void showLoadingView() {
        Activity activity = getActivity();
        if (activity == null)
            return;
        if (activity instanceof BaseActivity) {
            ((BaseActivity) activity).showLoadingView();
            return;
        }
    }

    @Override
    public void showLoadingView(CharSequence msg) {
        Activity activity = getActivity();
        if (activity == null)
            return;
        if (activity instanceof BaseActivity) {
            ((BaseActivity) activity).showLoadingView(msg);
            return;
        }
    }

    @Override
    public void hideLoadingView() {
        Activity activity = getActivity();
        if (activity == null)
            return;
        if (activity instanceof BaseActivity) {
            ((BaseActivity) activity).hideLoadingView();
            return;
        }
    }

    @Override
    public void toastMessage(CharSequence msg) {
        Activity activity = getActivity();
        if (activity == null)
            return;
        if (activity instanceof BaseActivity) {
            ((BaseActivity) activity).toastMessage(msg);
            return;
        }
    }


    @Override
    public void toastMessage(int resId) {
        Activity activity = getActivity();
        if (activity == null)
            return;
        if (activity instanceof BaseActivity) {
            ((BaseActivity) activity).toastMessage(resId);
            return;
        }
    }


    @Override
    public void closeCurrPage() {
        Activity activity = getActivity();
        if (activity != null) {
            activity.finish();
        }
    }

    @Override
    public void hideSoftKeyboard() {
        Activity activity = getActivity();
        if (activity == null)
            return;
        if (activity instanceof BaseActivity) {
            ((BaseActivity) activity).hideSoftKeyboard();
            return;
        }
    }

    @Override
    public void showSoftKeyboard() {
        Activity activity = getActivity();
        if (activity == null)
            return;
        if (activity instanceof BaseActivity) {
            ((BaseActivity) activity).showSoftKeyboard();
            return;
        }
    }

    @Override
    public void showSoftKeyboard(View view) {
        Activity activity = getActivity();
        if (activity == null)
            return;
        if (activity instanceof BaseActivity) {
            ((BaseActivity) activity).showSoftKeyboard(view);
            return;
        }
    }

}
