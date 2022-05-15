package com.design.mvp.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.design.mvp.R;
import com.design.mvp.util.KeyboardUtil;
import com.design.mvp.util.StatusBarUtil;
import com.design.mvp.util.ToastUtil;
import com.design.mvp.util.loading.LoadingDialogUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 * MVP架构的基础BaseActivity
 *
 * @author syl
 * @time 2020/3/16 18:49
 */
public abstract class BaseActivity<T extends BaseMVPContract.BasePresenter> extends AppCompatActivity implements BaseMVPContract.BaseView {

    protected Activity mContext;

    protected T mPresenter;

    private Dialog mLoadingDialog;

    /**
     * 获取当前页面的layout id
     *
     * @return
     */
    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract T initPresenter();

    protected abstract void initEventAndData(Bundle savedInstanceState);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O && isTranslucentOrFloating()) {
            boolean result = fixOrientation();
        }

        super.onCreate(savedInstanceState);
        mContext = this;
        int layoutId = getLayoutId();
        if (layoutId != 0) {
            setContentView(getLayoutId());
        }
        transparentStatusBar();
        initStatusBarDarkFont(true);

        ActivityManager.getInstance().addActivity(this);
        mPresenter = initPresenter();
        initEventAndData(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
        ActivityManager.getInstance().removeActivity(this);
    }

    //点击键盘外侧把当前键盘隐藏
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        KeyboardUtil.hideKeyboard(mContext);
        return super.onTouchEvent(event);
    }


    /**
     * 初始化底部导航栏的颜色
     */
    protected void initNavigationBarColor(int color) {
        if (color == Color.TRANSPARENT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(color);
        }
    }

    /**
     * 使状态栏透明
     */
    protected void transparentStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * 设置状态栏字体为深色
     */
    protected void initStatusBarDarkFont(boolean isDarkFont) {
        //全屏
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (StatusBarUtil.setMeiZuStatusBarDarkFont(isDarkFont, getWindow())) {
                return;
            }
            if (StatusBarUtil.setXiaoMiStatusBarDarkFont(isDarkFont, this)) {

            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (isDarkFont) {
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
                }
            }
        }

    }


    @Override
    public void showLoadingView() {
        if (mLoadingDialog == null) {
            mLoadingDialog = LoadingDialogUtil.showLoading(this);
        }
        mLoadingDialog.show();
    }

    @Override
    public void showLoadingView(CharSequence msg) {
        if (mLoadingDialog == null) {
            mLoadingDialog = LoadingDialogUtil.showLoading(this, msg, false);
        }
        //动态变更msg
        TextView tvMsg = mLoadingDialog.findViewById(R.id.tv_loadingMsg);
        if (tvMsg != null && !TextUtils.isEmpty(msg)) {
            tvMsg.setText(msg);
        }
        mLoadingDialog.show();
    }

    @Override
    public void hideLoadingView() {
        LoadingDialogUtil.dismissLoading(mLoadingDialog);
        mLoadingDialog = null;
    }

    @Override
    public void toastMessage(CharSequence msg) {
        ToastUtil.showMessage(this, msg);
    }

    @Override
    public void toastMessage(int resId) {
        ToastUtil.showMessage(this, resId);
    }

    @Override
    public void closeCurrPage() {
        finish();
    }

    @Override
    public void hideSoftKeyboard() {
        KeyboardUtil.hideKeyboard(this);
    }

    @Override
    public void showSoftKeyboard() {
        KeyboardUtil.toggleKeyboard(this);
    }

    @Override
    public void showSoftKeyboard(View view) {
        KeyboardUtil.showKeyboard(view);
    }

    private boolean isTranslucentOrFloating() {
        boolean isTranslucentOrFloating = false;
        try {
            int[] styleableRes = (int[]) Class.forName("com.android.internal.R$styleable").getField("Window").get(null);
            final TypedArray ta = obtainStyledAttributes(styleableRes);
            Method m = ActivityInfo.class.getMethod("isTranslucentOrFloating", TypedArray.class);
            m.setAccessible(true);
            isTranslucentOrFloating = (boolean) m.invoke(null, ta);
            m.setAccessible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isTranslucentOrFloating;
    }

    /**
     * Android8.0系统，透明Activity不能固定它的方向,否则直接闪退，这里解决这个BUG
     *
     * @return
     */
    @SuppressLint("WrongConstant")
    private boolean fixOrientation() {
        try {
            Field field = Activity.class.getDeclaredField("mActivityInfo");
            field.setAccessible(true);
            ActivityInfo o = (ActivityInfo) field.get(this);
            o.screenOrientation = -1;
            field.setAccessible(false);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Android8.0系统，透明Activity不能固定它的方向,否则直接闪退，这里解决这个BUG
     *
     * @return
     */
    @Override
    public void setRequestedOrientation(int requestedOrientation) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O && isTranslucentOrFloating()) {
            return;
        }
        super.setRequestedOrientation(requestedOrientation);
    }

}