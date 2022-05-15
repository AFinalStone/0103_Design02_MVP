package com.design.mvp.base;

import android.view.View;

/**
 * MVP基础的会话类对象
 *
 * @author syl
 * @time 2020/3/16 19:40
 */

public interface BaseMVPContract {

    interface BaseView {

        /**
         * 显示浮动加载进度条
         */
        void showLoadingView();


        /**
         * 显示浮动加载进度条
         *
         * @param msg
         */
        void showLoadingView(CharSequence msg);


        /**
         * 停止浮动加载进度条
         */
        void hideLoadingView();


        /**
         * 显示Toast信息
         *
         * @param msg
         */
        void toastMessage(CharSequence msg);


        /**
         * 显示Toast信息
         *
         * @param resId 资源id
         */
        void toastMessage(int resId);


        /**
         * 关闭当前页面
         */
        void closeCurrPage();


        /**
         * 隐藏软键盘
         */
        void hideSoftKeyboard();


        /**
         * 切换软键盘的状态
         */
        void showSoftKeyboard();

        /**
         * 显示软键盘
         *
         * @param view
         */
        void showSoftKeyboard(View view);

    }

    interface BasePresenter {

        void onViewCreated();

        void onDestroyView();

        void onDestroy();

    }

}