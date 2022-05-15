package com.design.mvp.view.adapter.view;

import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.design.mvp.R;
import com.design.mvp.view.adapter.ICartoonItem;
import com.design.mvp.view.adapter.base.BaseViewHolder;

public class TextViewHolder extends BaseViewHolder<ICartoonItem> {

    TextView tvName;

    public TextViewHolder(@NonNull ViewGroup parent) {
        super(parent, R.layout.adapter_cartoon_list_item_text);
        tvName = itemView.findViewById(R.id.tv_name);
    }

    @Override
    public void bindData(ICartoonItem data) {
        tvName.setText(data.getITitleName());
    }


}