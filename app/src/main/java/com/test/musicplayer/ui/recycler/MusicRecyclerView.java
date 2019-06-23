package com.test.musicplayer.ui.recycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class MusicRecyclerView extends RecyclerView {
    public MusicRecyclerView(@NonNull Context context) {
        this(context, null);
    }

    public MusicRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MusicRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        init();
    }

    private void init() {
        //创建线性布局
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        //设置为垂直方向 跟进源码可知：1是vertical， 0是horizontal
//        layoutManager.setOrientation(OrientationHelper.VERTICAL);
//
//        recyclerView.setLayoutManager(layoutManager);
//        SecondAdapter adapter = new SecondAdapter(this, nameList);
//        recyclerView.addItemDecoration(new FirstDecoration(this, 1));
//
//        recyclerView.setAdapter(adapter);
    }

}
