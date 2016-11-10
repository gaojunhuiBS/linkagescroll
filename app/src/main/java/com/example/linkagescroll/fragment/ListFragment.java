package com.example.linkagescroll.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.linkagescroll.R;
import com.example.linkagescroll.adapter.RecyclerBaseAdapter;
import com.example.linkagescroll.itemDecoration.AlphaDividerItemDecoration;
import com.example.linkagescroll.model.RecyclerDataModel;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shuyu on 2016/11/10.
 */

public class ListFragment extends Fragment {


    @BindView(R.id.list_item_recycler)
    XRecyclerView listItemRecycler;

    LinearLayoutManager linearLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_list, null);
        ButterKnife.bind(this, view);

        initView();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        refresh();

    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    protected int devider;
    protected boolean isRefreshing;
    protected long timestamp;
    protected int page = 2;

    protected RecyclerBaseAdapter recyclerBaseAdapter;
    protected List<RecyclerDataModel> dataList = new ArrayList<>();
    protected SwipeRefreshLayout swipeRefreshLayout;


    public int getDevider() {
        return devider;
    }

    public void setDevider(int devider) {
        this.devider = devider;
    }

    public void setListData(List list) {
        this.dataList = list;
        if (recyclerBaseAdapter != null) {
            recyclerBaseAdapter.setListData(list);
        }
    }


    public boolean isRefreshing() {
        return isRefreshing;
    }

    public void setRefreshing(boolean refreshing) {
        isRefreshing = refreshing;
    }

    public void setSwipeRefreshLayout(SwipeRefreshLayout swipeRefreshLayout) {
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    private void initView() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        listItemRecycler.setLayoutManager(linearLayoutManager);
        listItemRecycler.addItemDecoration(new AlphaDividerItemDecoration(getDevider(), AlphaDividerItemDecoration.LIST));
        listItemRecycler.setPullRefreshEnabled(false);
        listItemRecycler.setLoadingMoreEnabled(false);
        listItemRecycler.setLoadingMoreProgressStyle(ProgressStyle.SysProgress);

        for (int i = 0; i < 19; i++) {
            RecyclerDataModel recyclerDataModel = new RecyclerDataModel();
            dataList.add(recyclerDataModel);
        }

        recyclerBaseAdapter = new RecyclerBaseAdapter(getActivity(), dataList);
        listItemRecycler.setAdapter(recyclerBaseAdapter);

    }

    /**
     * 刷新
     */
    public void refresh() {
        if (isRefreshing())
            return;
        setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setRefreshing(false);
                if (swipeRefreshLayout != null)
                    swipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }

    public void changeToTop() {
        listItemRecycler.stopNestedScroll();
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        linearLayoutManager.scrollToPosition(0);
    }

    public RecyclerView getScrollableView() {
        return listItemRecycler;
    }

}
