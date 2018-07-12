package com.dongdian.jj.gorgeous.category;


import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.dongdian.jj.gorgeous.R;
import com.tools.jj.tools.adapter.BaseRecyclerViewHolder;
import com.tools.jj.tools.adapter.CommonDelegateAdapter;
import com.tools.jj.tools.basemvp.p.BasePresenter;
import com.tools.jj.tools.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Jiajun he on 2018/4/8.
 * Email:1021661582@qq.com
 * des:
 * version: 1.0.0
 */

public class CategoryFragment extends BaseFragment {


    @Bind(R.id.fragment_category_rv)
    RecyclerView liveRecyclerView;

    private DelegateAdapter mDelegateAdapter;
    private CommonDelegateAdapter gridDelegateAdapter;
    private List<String> dataList = new ArrayList<>();
    @Override
    public void showRequestFail() {

    }

    @Override
    public void showRequestSuccess() {

    }

    @Override
    protected void initView() {
        initRecyclerView();
    }

    private void initRecyclerView() {


            VirtualLayoutManager manager = new VirtualLayoutManager(getActivity());
            liveRecyclerView.setLayoutManager(manager);
            RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
            liveRecyclerView.setRecycledViewPool(viewPool);
            viewPool.setMaxRecycledViews(0, 40);

            mDelegateAdapter = new DelegateAdapter(manager, false);
            GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(2);
            //自动填充，这个最好设置为false，不然有的时候布局会乱
            gridLayoutHelper.setAutoExpand(false);
            //间隔
            gridLayoutHelper.setHGap(4);
            gridLayoutHelper.setVGap(40);
        LoadData();
            gridDelegateAdapter = new CommonDelegateAdapter<String>(getActivity(), R.layout.fragment_home_vp_fragment_live, dataList, gridLayoutHelper, 0) {
                @Override
                public void convert(BaseRecyclerViewHolder holder, String s, int position) {

                }

                @Override
                public void convert(BaseRecyclerViewHolder holder, int position) {

                }

            };
            //SingleLayoutHelper，上啦footer布局
            SingleLayoutHelper singleLayoutHelper = new SingleLayoutHelper();
            singleLayoutHelper.setItemCount(1);
           mDelegateAdapter.addAdapter(gridDelegateAdapter);

            liveRecyclerView.setAdapter(mDelegateAdapter);

            Log.d("sadzcxzcsaf", "data:" + dataList.size());

        }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_category;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initData() {
        LoadData();
    }
    public void LoadData() {
        for (int i = 0; i < 6; i++) {
            dataList.add(i + "");
        }
    }

    @Override
    protected void registerListener() {

    }
}
