package com.cakes.musicplayer.sample.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.cakes.musicplayer.R;
import com.cakes.musicplayer.music.MusicFileHelper;
import com.cakes.musicplayer.music.MusicInfoBean;

import java.util.List;

public class LocalMusicActivity extends AppCompatActivity {

    private final String TAG = "LocalMusicActivity";

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
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL); //设置为垂直布局，这也是默认的
        recyclerView.setLayoutManager(linearLayoutManager); //设置布局管理器
        recyclerView.addItemDecoration(new DividerItemDecoration(this, OrientationHelper.VERTICAL)); //设置分隔线
        recyclerView.setItemAnimator(new DefaultItemAnimator()); //设置增加或删除条目的动画

        List<MusicInfoBean> sdcardMusicList = MusicFileHelper.sdcardMusicList;
//        MainRecyclerViewAdapter adapter = new MainRecyclerViewAdapter(this, sdcardMusicList);
//        recyclerView.setAdapter(adapter);
    }
}
