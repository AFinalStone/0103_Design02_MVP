package com.design.mvp.view.cartoon;


import com.design.mvp.base.BaseMVPContract;
import com.design.mvp.view.adapter.ICartoonItem;

import java.util.ArrayList;
import java.util.HashSet;

public interface CartoonFragmentContract {

    interface View extends BaseMVPContract.BaseView {
        /**
         * 显示列表
         *
         * @param list
         */
        void showList(ArrayList<ICartoonItem> list);

        /**
         * 更新已经看过的条目索引列表
         *
         * @param hashSet
         */
        void updateHaveReadIndexList(HashSet<String> hashSet);

        /**
         * 数据为空
         */
        void showDataEmpty();
    }

    interface Presenter extends BaseMVPContract.BasePresenter {

        /**
         * 初始化
         */
        void init();

        /**
         * 进入详情，当前页码
         */
        void goDetail(ICartoonItem item);
    }

}
