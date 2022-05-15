package com.design.mvp.view.adapter.base;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T extends BaseViewHolder, K extends BaseViewHolderItem> extends RecyclerView.Adapter<T> {

    protected Context mContext;
    protected List<K> mListData;

    public BaseAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setNewData(List<K> listData) {
        if (mListData == null) {
            mListData = new ArrayList<>();
        }
        mListData.clear();
        mListData.addAll(listData);
    }

    @Override
    public int getItemViewType(int position) {
        return mListData.get(position).getType();
    }

    @Override
    public void onBindViewHolder(@NonNull T holder, int position) {
        holder.bindData(mListData.get(position));
    }

    @Override
    public int getItemCount() {
        return mListData == null ? 0 : mListData.size();
    }
}
