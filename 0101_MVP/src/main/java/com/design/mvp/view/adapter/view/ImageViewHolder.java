package com.design.mvp.view.adapter.view;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.design.mvp.R;
import com.design.mvp.view.adapter.ICartoonItem;
import com.design.mvp.view.adapter.base.BaseViewHolder;

public class ImageViewHolder extends BaseViewHolder<ICartoonItem> {

    ImageView imageView;
    TextView tvName;

    public ImageViewHolder(@NonNull ViewGroup parent) {
        super(parent, R.layout.adapter_cartoon_list_item_image);
        tvName = itemView.findViewById(R.id.tv_name);
        imageView = itemView.findViewById(R.id.iv_image);
    }

    @Override
    public void bindData(ICartoonItem data) {
        tvName.setText(data.getITitleName());
        Glide.with(imageView.getContext()).load(data.getPictureUrl()).placeholder(R.mipmap.img).into(imageView);
    }

}