package com.fzq.musicplayer.ui.fragments.localmusic;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fzq.musicplayer.R;
import com.fzq.musicplayer.ui.recyclerview.MyDecoration;
import com.fzq.musicplayer.utils.MusicUtil;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by fzq on 2017/8/19.
 */

public class DanQuFrag extends Fragment {

    private View rootView;
    private Unbinder unBinder;
    private Context context;

    @BindView(R.id.danquFrag_recyclerView)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.layout_fragment_localmusic_danqu, container, false);
        unBinder = ButterKnife.bind(this, rootView);
        context = getContext();

        //创建默认的线性LayoutManager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
//        recyclerView.setHasFixedSize(true);
        MyDecoration decoration = new MyDecoration(context, OrientationHelper.VERTICAL);
        recyclerView.addItemDecoration(decoration);
        DanQuAdapter adapter = new DanQuAdapter(context, MusicUtil.musicList);
        recyclerView.setAdapter(adapter);


        return rootView;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unBinder.unbind();
    }
}
