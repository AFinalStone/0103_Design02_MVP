package com.design.mvp.view.empty;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.design.mvp.R;
import com.design.mvp.base.BaseFragment;
import com.design.mvp.base.BaseMVPContract;

public class EmptyFragment extends BaseFragment {

    public static EmptyFragment getFragment(String name) {
        EmptyFragment frag = new EmptyFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment_empty;
    }

    @Override
    protected BaseMVPContract.BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initEventAndData(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        String name = bundle.getString("name");
        if (!TextUtils.isEmpty(name)) {
            TextView tvContent = mContentView.findViewById(R.id.tv_content);
            tvContent.setText(name);
        }
    }
}
