package com.design.mvp.view.adapter;

import com.design.mvp.view.adapter.base.BaseViewHolderItem;

public interface ICartoonItem extends BaseViewHolderItem {

    int typeText = 0;
    int typeImage = 1;

    /**
     * 获取标题名称
     *
     * @return
     */
    String getITitleName();

    /**
     * 获取图片lian
     *
     * @return
     */
    String getPictureUrl();

}
