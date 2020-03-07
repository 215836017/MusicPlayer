package com.cakes.musicplayer.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

import com.cakes.musicplayer.R;
import com.cakes.musicplayer.music.MusicInfoBean;
import com.cakes.musicplayer.play.OnMusicPlayListener;
import com.cakes.musicplayer.service.MusicService;

/**
 * 播放详情页
 */
public class PlayDetailsActivity extends AppCompatActivity {

    private final String TAG = "PlayDetailsActivity";
    private MusicService.MusicPlayBinder musicPlayBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_details);

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
        public void onStart(MusicInfoBean currentMusicInfo) {

        }

        @Override
        public void onStop(MusicInfoBean currentMusicInfo) {

        }

        @Override
        public void onComplete(MusicInfoBean currentMusicInfo) {

        }

        @Override
        public void onError(MusicInfoBean currentMusicInfo, int errorCode) {

        }
    };
}
