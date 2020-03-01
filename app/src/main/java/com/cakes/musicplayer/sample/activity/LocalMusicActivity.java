package com.cakes.musicplayer.sample.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.cakes.musicplayer.R;
import com.cakes.musicplayer.music.MusicFileHelper;
import com.cakes.musicplayer.music.MusicInfoBean;
import com.cakes.musicplayer.music.QueryLocalMusicListener;
import com.cakes.musicplayer.music.QueryLocalMusicThread;
import com.cakes.musicplayer.sample.adapters.LocalMusicAdapter;
import com.cakes.musicplayer.sample.adapters.OnItemEventListener;

import java.util.List;

public class LocalMusicActivity extends AppCompatActivity {

    private final String TAG = "LocalMusicActivity";

    private RecyclerView recyclerView;
    private LinearLayout layoutLoading;
    private ProgressBar progressBar;
    private TextView textEmpty;

    private List<MusicInfoBean> musicDataList;
    private LocalMusicAdapter localMusicAdapter;

    private final int MSG_QUERY_FINISH = 0x10;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_QUERY_FINISH:
                    bindMusciService();
                    adaptData();
                    hideLoadingView();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_music);

        initView();
        queryData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private void initView() {
        recyclerView = findViewById(R.id.activity_local_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL); //设置为垂直布局，这也是默认的
        recyclerView.setLayoutManager(linearLayoutManager); //设置布局管理器
        recyclerView.addItemDecoration(new DividerItemDecoration(this, OrientationHelper.VERTICAL)); //设置分隔线
        recyclerView.setItemAnimator(new DefaultItemAnimator()); //设置增加或删除条目的动画

        layoutLoading = findViewById(R.id.activity_local_layout_loading);
        progressBar = findViewById(R.id.activity_local_progressbar);
        textEmpty = findViewById(R.id.activity_local_text_empty);
    }

    private void queryData() {
        layoutLoading.setVisibility(View.VISIBLE);
        MusicFileHelper musicFileHelper = new MusicFileHelper(this, queryLocalMusicListener);
        musicFileHelper.querySdcardMusicFiles();
    }

    private void hideLoadingView() {
        layoutLoading.setVisibility(View.GONE);
    }

    private void bindMusciService() {

    }

    private void adaptData() {
        if (null != musicDataList && !musicDataList.isEmpty()) {
            localMusicAdapter = new LocalMusicAdapter(this, musicDataList, onItemEventListener);
            recyclerView.setAdapter(localMusicAdapter);
        } else {
            textEmpty.setVisibility(View.VISIBLE);
        }
    }

    private QueryLocalMusicListener queryLocalMusicListener = new QueryLocalMusicListener() {
        @Override
        public void onQueryMusicFinish(List<MusicInfoBean> musicList) {
            handler.sendEmptyMessage(MSG_QUERY_FINISH);
        }
    };
    private OnItemEventListener onItemEventListener = new OnItemEventListener() {

        @Override
        public void play(int position, MusicInfoBean musicInfoBean) {

        }

        @Override
        public void onItemClick(int position, MusicInfoBean musicInfoBean) {

        }
    };

}
