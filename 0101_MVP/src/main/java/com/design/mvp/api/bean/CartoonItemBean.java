package com.design.mvp.api.bean;

import com.design.mvp.view.adapter.ICartoonItem;

public class CartoonItemBean implements ICartoonItem {

    private String name;
    private String image_url;
    private Integer code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "CartoonItemBean{" +
                "name='" + name + '\'' +
                ", image_url='" + image_url + '\'' +
                ", code=" + code +
                '}';
    }

    @Override
    public String getITitleName() {
        return name;
    }

    @Override
    public String getPictureUrl() {
        return image_url;
    }

    @Override
    public int getType() {
        return code % 2 == 0 ? typeText : typeImage;
    }
}