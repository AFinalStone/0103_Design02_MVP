package com.design.mvp.view;


import com.design.mvp.base.BaseFragment;
import com.design.mvp.base.BaseMVPContract;

import java.util.List;

public interface OnePushContract {

    interface View extends BaseMVPContract.BaseView {

        void showFragmentList(List<BaseFragment> list);


    }

    interface Presenter extends BaseMVPContract.BasePresenter {

        /**
         * 初始化
         */
        void init();

    }

}
