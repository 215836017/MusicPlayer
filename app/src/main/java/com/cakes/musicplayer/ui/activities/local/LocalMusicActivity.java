package com.cakes.musicplayer.ui.activities.local;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

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
import com.cakes.musicplayer.play.last.LastPlayConstant;
import com.cakes.musicplayer.play.playing.PlayingHelper;
import com.cakes.musicplayer.play.PlayMode;
import com.cakes.musicplayer.ui.activities.BaseActivity;
import com.cakes.musicplayer.ui.activities.PlayDetailsActivity;
import com.cakes.musicplayer.ui.adapters.OnItemEventListener;
import com.cakes.musicplayer.service.MusicService;
import com.cakes.musicplayer.utils.LogUtil;
import com.cakes.musicplayer.utils.ToastUtil;
import com.cakes.musicplayer.utils.sp.SPUtil;

import java.util.List;

/**
 * 亿图图示-跨平台综合办公绘图软件
 */
public class LocalMusicActivity extends BaseActivity implements View.OnClickListener {

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

    private MusicInfoBean currentPlayMusic;
    private final int MSG_QUERY_FINISH = 10 + MSG_LEVEL_LOCAL;
    private final int MSG_PLAY_START = 11 + MSG_LEVEL_LOCAL;
    private final int MSG_PLAY_STOP = 12 + MSG_LEVEL_LOCAL;
    private final int MSG_PLAY_COMPLETE = 13 + MSG_LEVEL_LOCAL;
    private final int MSG_PLAY_ERROR = 14 + MSG_LEVEL_LOCAL;
    private final int MSG_PLAY_UPDATE = 15 + MSG_LEVEL_LOCAL;

    @Override
    public void handleMsgInHandler(Message msg) {
        int what = msg.what;
        LogUtil.i(TAG, "handleMsgInHandler() -- msg.what = " + what);
        switch (what) {

            case MSG_QUERY_FINISH:
                LogUtil.d(TAG, "case MSG_QUERY_FINISH -- 11111111");
                adaptData();
                hideLoadingView();
                break;

            case MSG_PLAY_START:
                onPlayStart(msg);
                break;

            case MSG_PLAY_STOP:
                onPlayPause();
                break;

            case MSG_PLAY_COMPLETE:
                onPlayComplete();
                break;

            case MSG_PLAY_ERROR:
                onPlayError();
                break;

            case MSG_PLAY_UPDATE:
                onPlayProgressUpdate(msg);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_music);

        initView();
        queryData();
        bindMusicService();
        initBottomControlViews();

        PlayMode.getInstance().setPlayMode(PlayMode.PLAY_MODE_LIST_ORDER);
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

    private void initView() {
        recyclerView = findViewById(R.id.activity_local_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL); //设置为垂直布局，这也是默认的
        recyclerView.setLayoutManager(linearLayoutManager); //设置布局管理器
        recyclerView.addItemDecoration(new DividerItemDecoration(this, OrientationHelper.VERTICAL)); //设置分隔线
        recyclerView.setItemAnimator(new DefaultItemAnimator()); //设置增加或删除条目的动画

        layoutLoading = findViewById(R.id.activity_local_layout_loading);
        textEmpty = findViewById(R.id.activity_local_text_empty);

        layoutControlRoot = findViewById(R.id.activity_layout_bottom_control);
        imageControlAlbum = findViewById(R.id.layout_bottom_control_image_album);
        seekBarControl = findViewById(R.id.layout_bottom_control_seekbar);
        textControlName = findViewById(R.id.layout_bottom_control_text_name);
        textControlAuthor = findViewById(R.id.layout_bottom_control_text_author);
        imageControlList = findViewById(R.id.layout_bottom_control_image_list);
        imageControlPlay = findViewById(R.id.layout_bottom_control_image_play);
        imageControlNext = findViewById(R.id.layout_bottom_control_image_next);

        setDataForBottomControlViews();

        layoutControlRoot.setOnClickListener(this);
        imageControlAlbum.setOnClickListener(this);
        imageControlList.setOnClickListener(this);
        imageControlPlay.setOnClickListener(this);
        imageControlNext.setOnClickListener(this);

        seekBarControl.setOnSeekBarChangeListener(seekBarChangeListener);
    }

    private void setDataForBottomControlViews() {
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
        String lastPlayInfo = SPUtil.getString(this, LastPlayConstant.SP_LAST_PLAY_INFO, "");
    }

    private void bindMusicService() {
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

    private void onPlayStart(Message msg) {
        imageControlAlbum.setImageResource(R.mipmap.ic_launcher_round);
        textControlName.setText(currentPlayMusic.getDisplayName());
        textControlAuthor.setText(currentPlayMusic.getArtist());
        imageControlPlay.setImageResource(R.drawable.ic_media_pause);
//        seekBarControl.setMax(msg.arg1 / 1000);

        MusicInfoBean playingInfoBean = PlayingHelper.getInstance().getPlayingInfoBean();
        playingInfoBean.setPlaying(true);
        localMusicAdapter.updateItem(PlayingHelper.getInstance().getPlayingPosition(), playingInfoBean);
    }

    private void onPlayPause() {
        imageControlAlbum.setImageResource(R.mipmap.ic_launcher_round);
        textControlName.setText(currentPlayMusic.getDisplayName());
        textControlAuthor.setText(currentPlayMusic.getArtist());
        imageControlPlay.setImageResource(R.drawable.ic_media_play);
    }

    private void onPlayComplete() {
        imageControlAlbum.setImageResource(R.mipmap.ic_launcher_round);
        textControlName.setText("");
        textControlAuthor.setText("");
        imageControlPlay.setImageResource(R.drawable.ic_media_play);

        MusicInfoBean playingInfoBean = PlayingHelper.getInstance().getPlayingInfoBean();
        playingInfoBean.setPlaying(false);
        localMusicAdapter.updateItem(PlayingHelper.getInstance().getPlayingPosition(), playingInfoBean);

        playNext();
    }

    private void onPlayError() {
        imageControlAlbum.setImageResource(R.mipmap.ic_launcher_round);
        textControlName.setText("");
        textControlAuthor.setText("");
        imageControlPlay.setImageResource(R.drawable.ic_media_play);
    }

    private void onPlayProgressUpdate(Message msg) {
//        LogUtil.d(TAG, "seekbar onPlayProgressUpdate() -- 1111");
//        seekBarControl.setProgress(msg.arg1 / 1000);
    }

    private void showToast(String msg) {
        LogUtil.i(TAG, "showToast() -- msg = " + msg);
        ToastUtil.showLong(this, msg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_layout_bottom_control:
            case R.id.layout_bottom_control_image_album:
                startActivity(new Intent(LocalMusicActivity.this, PlayDetailsActivity.class));
                break;

            case R.id.layout_bottom_control_image_play:
                play();
                break;

            case R.id.layout_bottom_control_image_next:
                playNext();
                break;

            case R.id.layout_bottom_control_image_list:
                // todo show the playing list
                break;
        }
    }

    private void play() {
        if (musicPlayBinder.isPlaying()) {
            musicPlayBinder.pause();

        } else {
            if (null != currentPlayMusic) {
                musicPlayBinder.playMusic(currentPlayMusic);
            }
        }
    }

    private void playNext() {
        int nextPosition = PlayMode.getInstance().getNextPosition(
                PlayingHelper.getInstance().getPlayingPosition(),
                PlayingHelper.getInstance().getPlayingList().size());
        LogUtil.d(TAG, "playNext() -- getNextPosition = " + nextPosition);

        PlayingHelper.getInstance().setPlayingPosition(nextPosition);
        currentPlayMusic = localMusicAdapter.getMusicInfoBean(nextPosition);
        if (null != currentPlayMusic) {
            LogUtil.d(TAG, "playNext() -- nextPosition = " + nextPosition + ", music is: " + currentPlayMusic.toString());
            musicPlayBinder.playMusic(currentPlayMusic);
        } else {
            LogUtil.w(TAG, "playNext() -- currentPlayMusic = null");
        }
    }

    private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            LogUtil.i(TAG, "seekBarChangeListener - onProgressChanged(): progress = " + progress
                    + ", fromUser = " + fromUser);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            LogUtil.i(TAG, "seekBarChangeListener - onProgressChanged(): 1111111");
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            LogUtil.i(TAG, "seekBarChangeListener - onProgressChanged(): 1111111");
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
        public void onStart(MusicInfoBean infoBean, int duration) {
            showToast("music: " + infoBean.getDisplayName() + " is playing...");
            if (null != infoBean) {
                currentPlayMusic = infoBean;
                Message msgStart = handler.obtainMessage(MSG_PLAY_START, duration, -1);
                handler.sendMessage(msgStart);
            }
        }

        @Override
        public void onStop(MusicInfoBean infoBean) {
            if (null != infoBean) {
                currentPlayMusic = infoBean;
                handler.sendEmptyMessage(MSG_PLAY_STOP);
            }
        }

        @Override
        public void onComplete(MusicInfoBean infoBean) {
            if (null != infoBean) {
                showToast("music: " + infoBean.getDisplayName() + " play completed!");
                currentPlayMusic = infoBean;
                handler.sendEmptyMessage(MSG_PLAY_COMPLETE);
            }
        }

        @Override
        public void onProgress(int currentDuration) {
//            LogUtil.d(TAG, "seekbar  onProgress() -- currentDuration = " + currentDuration);
            Message msgProgress = handler.obtainMessage(MSG_PLAY_UPDATE, currentDuration, -1);
            handler.sendMessage(msgProgress);
        }

        @Override
        public void onError(MusicInfoBean infoBean, int errorCode) {
            if (null != infoBean) {
                currentPlayMusic = infoBean;
            }
            handler.sendEmptyMessage(MSG_PLAY_ERROR);
        }
    };


}
