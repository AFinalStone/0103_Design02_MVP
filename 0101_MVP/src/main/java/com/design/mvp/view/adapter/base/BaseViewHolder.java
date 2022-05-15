package com.design.mvp.view.adapter.base;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseViewHolder<T extends BaseViewHolderItem> extends RecyclerView.ViewHolder {

    public BaseViewHolder(@NonNull ViewGroup parent, @NonNull @LayoutRes int resource) {
        super(LayoutInflater.from(parent.getContext()).inflate(resource, parent, false));
    }

    public abstract void bindData(T data);

}
