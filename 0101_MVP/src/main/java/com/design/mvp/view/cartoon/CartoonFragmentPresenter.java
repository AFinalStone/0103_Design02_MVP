package com.design.mvp.view.cartoon;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.design.mvp.api.StoneCartoonAPIService;
import com.design.mvp.api.bean.BaseRespBean;
import com.design.mvp.api.bean.CartoonBean;
import com.design.mvp.api.bean.CartoonItemBean;
import com.design.mvp.base.BasePresenter;
import com.design.mvp.view.adapter.ICartoonItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CartoonFragmentPresenter extends BasePresenter<CartoonFragmentContract.View> implements CartoonFragmentContract.Presenter {

    private final static String TAG = "CartoonFragment";

    public CartoonFragmentPresenter(@NonNull Context context, @NonNull CartoonFragmentContract.View view) {
        super(context, view);
    }

    @Override
    public void init() {
        mView.showLoadingView();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.fastmock.site/mock/a02358e8f30496cbae7473fdb75a9ff8/stone_cartoon/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        StoneCartoonAPIService service = retrofit.create(StoneCartoonAPIService.class);
        Call<BaseRespBean<CartoonBean>> repos = service.get_cartoon_list("afinalstone");
        repos.enqueue(new Callback<BaseRespBean<CartoonBean>>() {
            @Override
            public void onResponse(Call<BaseRespBean<CartoonBean>> call, Response<BaseRespBean<CartoonBean>> response) {
                BaseRespBean<CartoonBean> baseRespBean = response.body();
                CartoonBean cartoonBean = baseRespBean.getData();
                List<CartoonItemBean> list = cartoonBean.getList();
                for (CartoonItemBean itemBean : list) {
                    Log.w(TAG, itemBean.toString());
                }
                mView.showList((ArrayList) list);
                mView.hideLoadingView();
            }

            @Override
            public void onFailure(Call<BaseRespBean<CartoonBean>> call, Throwable t) {
                mView.hideLoadingView();
                mView.toastMessage("数据请求失败");
            }
        });
    }

    @Override
    public void goDetail(final ICartoonItem item) {


    }


}
