package com.cakes.musicplayer.ui.activities;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.cakes.musicplayer.R;
import com.cakes.musicplayer.music.MusicInfoBean;
import com.cakes.musicplayer.music.MusicList;
import com.cakes.musicplayer.music.MusicSearcher;
import com.cakes.musicplayer.music.QueryLocalMusicListener;
import com.cakes.musicplayer.play.OnMusicPlayListener;
import com.cakes.musicplayer.ui.adapters.LocalMusicAdapter;
import com.cakes.musicplayer.ui.adapters.OnItemEventListener;
import com.cakes.musicplayer.service.MusicService;
import com.cakes.musicplayer.utils.LogUtil;
import com.cakes.musicplayer.utils.sp.SPConstant;
import com.cakes.musicplayer.utils.sp.SPUtil;
import com.cakes.musicplayer.utils.sp.SPUtilTest;

import java.util.List;

public class LocalMusicActivity extends AppCompatActivity {

    private final String TAG = "LocalMusicActivity";

    private RecyclerView recyclerView;
    private LinearLayout layoutLoading;
    private TextView textEmpty;

    private ConstraintLayout layoutControlRoot;
    private ImageView imageControlAlbum;
    private SeekBar seekBarControl;
    private TextView textControlName;
    private TextView textControlAuthor;
    private ImageView imageControlList;
    private ImageView imageControlPlay;
    private ImageView imageControlNext;

    private List<MusicInfoBean> musicDataList;
    private LocalMusicAdapter localMusicAdapter;

    private MusicService.MusicPlayBinder musicPlayBinder;

    private final int MSG_QUERY_FINISH = 0x10;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            handleMsg(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_music);

        initView();
        queryData();
        initBottomControlViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        musicPlayBinder.getService().removeMusicPlayListener(musicPlayerListener);
        try {
            unbindService(serviceConnection);
        } catch (Exception e) {
        }
    }

    private void handleMsg(Message msg) {
        switch (msg.what) {
            case MSG_QUERY_FINISH:
                LogUtil.d(TAG, "case MSG_QUERY_FINISH -- 11111111");
                bindMusciService();
                adaptData();
                hideLoadingView();
                break;
        }
    }

    private void initView() {
        recyclerView = findViewById(R.id.activity_local_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL); //设置为垂直布局，这也是默认的
        recyclerView.setLayoutManager(linearLayoutManager); //设置布局管理器
        recyclerView.addItemDecoration(new DividerItemDecoration(this, OrientationHelper.VERTICAL)); //设置分隔线
        recyclerView.setItemAnimator(new DefaultItemAnimator()); //设置增加或删除条目的动画

        layoutLoading = findViewById(R.id.activity_local_layout_loading);
        textEmpty = findViewById(R.id.activity_local_text_empty);

        layoutControlRoot = findViewById(R.id.layout_bottom_control_layout_root);
        imageControlAlbum = findViewById(R.id.layout_bottom_control_image_album);
        seekBarControl = findViewById(R.id.layout_bottom_control_seekbar);
        textControlName = findViewById(R.id.layout_bottom_control_text_name);
        textControlAuthor = findViewById(R.id.layout_bottom_control_text_author);
        imageControlList = findViewById(R.id.layout_bottom_control_image_list);
        imageControlPlay = findViewById(R.id.layout_bottom_control_image_play);
        imageControlNext = findViewById(R.id.layout_bottom_control_image_next);

        setDatasForBottomControlViews();

        layoutControlRoot.setOnClickListener(controlLayoutClickListener);
        imageControlAlbum.setOnClickListener(controlLayoutClickListener);
        imageControlList.setOnClickListener(controlLayoutClickListener);
        imageControlPlay.setOnClickListener(controlLayoutClickListener);
        imageControlNext.setOnClickListener(controlLayoutClickListener);

        seekBarControl.setOnSeekBarChangeListener(seekBarChangeListener);
    }

    private void setDatasForBottomControlViews() {
        getLastPlayInfo();

    }

    private void getLastPlayInfo() {
    }

    private void queryData() {
        LogUtil.i(TAG, "queryData() -- 1111111");
        layoutLoading.setVisibility(View.VISIBLE);
        MusicSearcher musicFileHelper = new MusicSearcher(this, queryLocalMusicListener);
        musicFileHelper.querySdcardMusicFiles();
    }

    private void initBottomControlViews() {
        String lastPlayInfo = SPUtil.getString(this, SPConstant.SP_LAST_PLAY_INFO, "");
    }

    private void bindMusciService() {
        Intent serviceIntent = new Intent(this, MusicService.class);
        bindService(serviceIntent, serviceConnection, BIND_AUTO_CREATE);
    }

    private void hideLoadingView() {
        layoutLoading.setVisibility(View.GONE);
    }

    private void adaptData() {
        if (!MusicList.getInstance().getSdcardMusicList().isEmpty()) {
            LogUtil.i(TAG, "adaptData() --- musicDataList.size = " + musicDataList.size());
            localMusicAdapter = new LocalMusicAdapter(this, onItemEventListener);
            recyclerView.setAdapter(localMusicAdapter);
        } else {
            textEmpty.setVisibility(View.VISIBLE);
        }
    }

    private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private View.OnClickListener controlLayoutClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.layout_bottom_control_layout_root:
                    startActivity(new Intent(LocalMusicActivity.this, PlayDetailsActivity.class));
                    break;
            }
        }
    };

    private QueryLocalMusicListener queryLocalMusicListener = new QueryLocalMusicListener() {
        @Override
        public void onQueryMusicFinish() {
            musicDataList = MusicList.getInstance().getSdcardMusicList();
            handler.sendEmptyMessage(MSG_QUERY_FINISH);
        }

        @Override
        public void onQueryMusicFailed() {

        }
    };

    private OnItemEventListener onItemEventListener = new OnItemEventListener() {

        @Override
        public void playMusic(int position, MusicInfoBean musicInfoBean) {
            musicPlayBinder.playMusic(musicInfoBean);
        }

        @Override
        public void onItemClick(int position, MusicInfoBean musicInfoBean) {

        }
    };

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            musicPlayBinder = (MusicService.MusicPlayBinder) service;
            musicPlayBinder.getService().addMusicPlayListener(musicPlayerListener);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicPlayBinder = null;
        }
    };

    private OnMusicPlayListener musicPlayerListener = new OnMusicPlayListener() {
        @Override
        public void onStart() {

        }

        @Override
        public void onStop() {

        }

        @Override
        public void onComplete() {

        }

        @Override
        public void onError(int errorCode) {

        }
    };
}
