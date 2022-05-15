package com.design.mvp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.design.mvp.R;
import com.design.mvp.view.adapter.base.BaseAdapter;
import com.design.mvp.view.adapter.base.BaseViewHolder;

public class CartoonListAdapter extends BaseAdapter<BaseViewHolder<ICartoonItem>, ICartoonItem> {

    public CartoonListAdapter(Context mContext) {
        super(mContext);
    }

    @NonNull
    @Override
    public BaseViewHolder<ICartoonItem> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BaseViewHolder<ICartoonItem> viewHolder;
        if (viewType == ICartoonItem.typeText) {
            View textView = LayoutInflater.from(mContext).inflate(R.layout.adapter_cartoon_list_item_text, parent, false);
            viewHolder = new TextViewHolder(textView);
        } else {
            View imageView = LayoutInflater.from(mContext).inflate(R.layout.adapter_cartoon_list_item_image, parent, false);
            viewHolder = new ImageViewHolder(imageView);
        }
        return viewHolder;
    }

    public static class TextViewHolder extends BaseViewHolder<ICartoonItem> {

        TextView tv;

        public TextViewHolder(View view) {
            super(view);
            tv = view.findViewById(R.id.btn_name);
        }

        @Override
        public void bindData(ICartoonItem data) {
            tv.setText(data.getITitleName());
        }


    }

    public static class ImageViewHolder extends BaseViewHolder<ICartoonItem> {

        ImageView imageView;

        public ImageViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.iv_image);
        }

        @Override
        public void bindData(ICartoonItem data) {
            Glide.with(imageView.getContext()).load(data.getPictureUrl()).into(imageView);
        }

    }


}
