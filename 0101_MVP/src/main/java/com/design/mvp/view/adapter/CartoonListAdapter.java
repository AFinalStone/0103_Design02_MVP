package com.design.mvp.view.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.design.mvp.view.adapter.base.BaseAdapter;
import com.design.mvp.view.adapter.base.BaseViewHolder;
import com.design.mvp.view.adapter.view.ImageViewHolder;
import com.design.mvp.view.adapter.view.TextViewHolder;

public class CartoonListAdapter extends BaseAdapter<BaseViewHolder<ICartoonItem>, ICartoonItem> {

    public CartoonListAdapter(Context mContext) {
        super(mContext);
    }

    @NonNull
    @Override
    public BaseViewHolder<ICartoonItem> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BaseViewHolder<ICartoonItem> viewHolder;
        if (viewType == ICartoonItem.typeText) {
            viewHolder = new TextViewHolder(parent);
        } else {
            viewHolder = new ImageViewHolder(parent);
        }
        return viewHolder;
    }

}
