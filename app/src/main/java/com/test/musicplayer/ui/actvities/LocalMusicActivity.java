package com.test.musicplayer.ui.actvities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.test.musicplayer.R;
import com.test.musicplayer.music.MusicFileHelper;
import com.test.musicplayer.music.MusicInfoBean;
import com.test.musicplayer.ui.adapters.MainRecyclerViewAdapter;
import com.test.musicplayer.utils.LogUtil;

import java.util.List;

public class LocalMusicActivity extends AppCompatActivity {

    private final String tag = "LocalMusicActivity.java";

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_music);

        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.localMusicAct_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL); //设置为垂直布局，这也是默认的
        recyclerView.setLayoutManager(linearLayoutManager); //设置布局管理器
        recyclerView.addItemDecoration(new DividerItemDecoration(this, OrientationHelper.VERTICAL)); //设置分隔线
        recyclerView.setItemAnimator(new DefaultItemAnimator()); //设置增加或删除条目的动画

        List<MusicInfoBean> sdcardMusicList = MusicFileHelper.sdcardMusicList;
        MainRecyclerViewAdapter adapter = new MainRecyclerViewAdapter(this, sdcardMusicList);
        recyclerView.setAdapter(adapter);
    }
}
