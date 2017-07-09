package com.fzq.musicplayer.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.fzq.musicplayer.Constant;

/**
 * Created by fzq on 2017/5/13.
 */

public class MusicService extends Service {

    private MediaPlayer mediaPlayer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * onstart()方法是在android2.0一下的版本中使用
     *
     * @param intent
     * @param startId
     */
    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    /**
     * 在android2.0以上则使用onstartCommand()方法。它们两个方法放在一起使用时，不会产生冲突。
     *
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mediaPlayer = new MediaPlayer();
        initMediaPlayer();

        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        playOrPause();
    }

    /**
     * 开始/暂停播放
     */
    private void playOrPause() {

        Constant.isPlaying = mediaPlayer.isPlaying();

        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            Constant.currentPosition = mediaPlayer.getCurrentPosition();
        } else {

            startPlay();
        }
    }
    /**
     * 开始播放
     */
    private void startPlay() {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(Constant.currentPlayingPath);
            mediaPlayer.prepare();
            mediaPlayer.start();

            updatePlayProgress();
        } catch (Exception e) {

        }
    }

    /**
     * 每隔一秒更新播放的进度条
     */
    private void updatePlayProgress() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                while (mediaPlayer.isPlaying()) {
                    try {

                        Constant.currentPosition = mediaPlayer.getCurrentPosition();
                        Thread.sleep(1000);

                    } catch (Exception e) {
                    }
                }
            }
        }.start();
    }

    /**
     * mediaPlayer的各种状态监听器
     */
    private void initMediaPlayer() {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                System.out.println("mediaPlayer --- setOnCompletionListener");
            }
        });


        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                System.out.println("mediaPlayer --- setOnPreparedListener");
            }
        });


        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                System.out.println("mediaPlayer --- setOnErrorListener");
                return false;
            }
        });


    }
}
