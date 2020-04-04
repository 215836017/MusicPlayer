package com.cakes.musicplayer.ui.activities;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.cakes.musicplayer.R;
import com.cakes.musicplayer.music.MusicInfoBean;
import com.cakes.musicplayer.music.MusicTime;
import com.cakes.musicplayer.play.OnMusicPlayListener;
import com.cakes.musicplayer.play.playing.PlayingHelper;
import com.cakes.musicplayer.service.MusicService;

/**
 * 播放详情页
 */
public class PlayDetailsActivity extends BaseActivity {

    private final String TAG = "PlayDetailsActivity";
    private MusicService.MusicPlayBinder musicPlayBinder;

    private ImageView imageBack;
    private TextView textMusicName;
    private TextView textMusicAuthor;
    private ImageView imagePlay, imagePrevious, imageNext;
    private ImageView imagePlayMode, imagePlayList;
    private SeekBar seekBar;
    private TextView textLike, textVideo, textDownload, textComment, textMore;
    private TextView textTimeStart, textTimeEnd;

    private final int MSG_PLAY_START = 0 + MSG_LEVEL_PLAY;
    private final int MSG_PLAY_STOP = 1 + MSG_LEVEL_PLAY;
    private final int MSG_PLAY_COMPLETE = 2 + MSG_LEVEL_PLAY;
    private final int MSG_UPDATE_PLAY_PROGRESS = 3 + MSG_LEVEL_PLAY;
    private final int MSG_PLAY_ERROR = 4 + MSG_LEVEL_PLAY;

    @Override
    public void handleMsgInHandler(Message msg) {
        switch (msg.what) {
            case MSG_PLAY_START:
                setData();
                break;

            case MSG_PLAY_STOP:
                playStop();
                break;

            case MSG_PLAY_COMPLETE:
                playComplete();
                break;

            case MSG_UPDATE_PLAY_PROGRESS:
                textTimeStart.setText(MusicTime.getTimeStr(msg.arg1));
                seekBar.setProgress(msg.arg1);
                break;

            case MSG_PLAY_ERROR:
                playError();
                break;

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_details);

        initViews();
        setData();

        Intent intent = new Intent(this, MusicService.class);
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);

        // todo get last play position
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

    private void initViews() {
        imageBack = findViewById(R.id.activity_playing_image_back);
        textMusicName = findViewById(R.id.activity_playing_text_name);
        textMusicAuthor = findViewById(R.id.activity_playing_text_author);
        imagePlay = findViewById(R.id.activity_playing_image_play);
        imagePrevious = findViewById(R.id.activity_playing_image_previous);
        imageNext = findViewById(R.id.activity_playing_image_next);
        imagePlayMode = findViewById(R.id.activity_playing_image_play_mode);
        imagePlayList = findViewById(R.id.activity_playing_image_play_list);
        seekBar = findViewById(R.id.activity_playing_seekbar);
        textLike = findViewById(R.id.activity_playing_text_like);
        textVideo = findViewById(R.id.activity_playing_text_videos);
        textDownload = findViewById(R.id.activity_playing_text_download);
        textComment = findViewById(R.id.activity_playing_text_comments);
        textMore = findViewById(R.id.activity_playing_text_more);
        textTimeStart = findViewById(R.id.activity_playing_text_time_start);
        textTimeEnd = findViewById(R.id.activity_playing_text_time_end);

        imageBack.setOnClickListener(onClickListener);
        imagePlay.setOnClickListener(onClickListener);
        imagePrevious.setOnClickListener(onClickListener);
        imageNext.setOnClickListener(onClickListener);
        imagePlayMode.setOnClickListener(onClickListener);
        imagePlayList.setOnClickListener(onClickListener);
    }

    private void setData() {
        MusicInfoBean playingInfoBean = PlayingHelper.getInstance().getPlayingInfoBean();
        if (null != playingInfoBean) {
            textMusicName.setText(playingInfoBean.getDisplayName());
            textMusicAuthor.setText(playingInfoBean.getArtist());
            textTimeEnd.setText(MusicTime.getTimeStr(playingInfoBean.getDuration()));
            seekBar.setMax((int) playingInfoBean.getDuration() / 1000);
        }
        if (null != musicPlayBinder && musicPlayBinder.isPlaying()) {
            imagePlay.setImageResource(R.drawable.ic_media_pause);
        }
    }

    private void playStop() {
        imagePlay.setImageResource(R.drawable.ic_media_play);
    }

    private void playComplete() {
        textMusicName.setText("");
        textMusicAuthor.setText("");
        textTimeStart.setText("");
        textTimeEnd.setText("");
        imagePlay.setImageResource(R.drawable.ic_media_play);
    }

    private void playError() {

    }


    private void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.activity_playing_image_back:
                finish();
                break;

            case R.id.activity_playing_image_play:
                if (null != musicPlayBinder) {
                    if (musicPlayBinder.isPlaying()) {
                        musicPlayBinder.pause();
                        imagePlay.setImageResource(R.drawable.ic_media_play);
                    } else {
                        musicPlayBinder.resume();
                        imagePlay.setImageResource(R.drawable.ic_media_pause);
                    }
                }
                break;

            case R.id.activity_playing_image_previous:
                break;
            case R.id.activity_playing_image_next:
                break;
            case R.id.activity_playing_image_play_mode:
                break;
            case R.id.activity_playing_image_play_list:
                break;
        }
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onViewClick(v);
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
        public void onStart(MusicInfoBean currentMusicInfo, int duration) {
            handler.sendEmptyMessage(MSG_PLAY_START);
        }

        @Override
        public void onStop(MusicInfoBean currentMusicInfo) {
            handler.sendEmptyMessage(MSG_PLAY_STOP);
        }

        @Override
        public void onComplete(MusicInfoBean currentMusicInfo) {
            handler.sendEmptyMessage(MSG_PLAY_COMPLETE);
        }

        @Override
        public void onProgress(int duration) {
            Message message = handler.obtainMessage(MSG_UPDATE_PLAY_PROGRESS, duration, -1);
            handler.sendMessage(message);
        }

        @Override
        public void onError(MusicInfoBean currentMusicInfo, int errorCode) {
            handler.sendEmptyMessage(MSG_PLAY_ERROR);
        }
    };
}
