package com.cakes.musicplayer.ui.activities;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cakes.musicplayer.music.MusicInfoBean;
import com.cakes.musicplayer.play.OnMusicPlayListener;
import com.cakes.musicplayer.service.MusicService;

public class BasePlayActivity extends AppCompatActivity {

    public MusicService.MusicPlayBinder musicPlayBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindMusciService();
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

    private void bindMusciService() {
        Intent serviceIntent = new Intent(this, MusicService.class);
        bindService(serviceIntent, serviceConnection, BIND_AUTO_CREATE);
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
        public void onStart(MusicInfoBean currentMusicInfo, int duration) {

        }

        @Override
        public void onStop(MusicInfoBean currentMusicInfo) {

        }

        @Override
        public void onComplete(MusicInfoBean currentMusicInfo) {

        }

        @Override
        public void onProgress(int duration) {

        }

        @Override
        public void onError(MusicInfoBean currentMusicInfo, int errorCode) {

        }
    };
}
