package com.design.mvp.api.bean;

import java.util.List;

public class CartoonBean {

    /**
     * cartoonName : 一拳超人
     * list : [{"name":"第1拳","zipName":"001.zip"},{"name":"第2拳","zipName":"002.zip"}]
     */

    private String cartoonName;
    private String unZipFileParentName;
    private List<CartoonItemBean> list;

    public String getCartoonName() {
        return cartoonName;
    }

    public void setCartoonName(String cartoonName) {
        this.cartoonName = cartoonName;
    }

    public String getUnZipFileParentName() {
        return unZipFileParentName;
    }

    public void setUnZipFileParentName(String onePushMan) {
        this.unZipFileParentName = onePushMan;
    }

    public List<CartoonItemBean> getList() {
        return list;
    }

    public void setList(List<CartoonItemBean> list) {
        this.list = list;
    }


}
