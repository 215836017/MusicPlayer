package com.fzq.musicplayer.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import com.fzq.musicplayer.Constant;
import com.fzq.musicplayer.ui.MainActivity;
import com.fzq.musicplayer.utils.MusicUtil;

/**
 * Created by fzq on 2017/5/13.
 */

public class MusicService extends Service {

    private final String TAG = "MusicService.java";
    public static Handler handler = null;
    public static final int PLAY_START = 0x10;
    public static final int PLAY_PAUSE = 0x11;
    public static final int PLAY_FINISH = 0x12;
    public static final int CHANGE_PROGRESS = 0x13;  //因为手动滑动了进度条，所以要更新

    private static MediaPlayer mediaPlayer;


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
        //添加mediaPlayer的各种状态监听器
        initMediaPlayer();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case PLAY_START:
                        play();
                        break;
                    case PLAY_PAUSE:
                        pause();
                        break;
                    case PLAY_FINISH:
                        break;
                    case CHANGE_PROGRESS:
                        mediaPlayer.seekTo(Constant.currentProgress);
                        break;
                    default:
                        break;
                }
            }
        };
    }

    /**
     * 暂停播放
     */
    private void pause() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            Constant.currentProgress = mediaPlayer.getCurrentPosition();
            Constant.playState = Constant.PLAY_PAUSE;
        }
    }

    /**
     * 开始播放
     */
    private void play() {

        try {
            if (mediaPlayer == null) {
                mediaPlayer = new MediaPlayer();
            }

            mediaPlayer.reset();
            mediaPlayer.setDataSource(Constant.currentPlayingMusic.getPath());
            mediaPlayer.prepare();
        } catch (Exception e) {
            System.out.println("准备工作阶段出错。。。。");
        }
    }

    /**
     * 添加mediaPlayer的各种状态监听器
     */
    private void initMediaPlayer() {
        mediaPlayer = new MediaPlayer();

        //准备工作完成
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.i(TAG, "mediaPlayer --- setOnPreparedListener()");
                               if (Constant.playState == Constant.PLAY_PAUSE) {
                    //如果是从暂停状态开始播放，那么还要设置进度
                    mediaPlayer.seekTo(Constant.currentProgress);
                }

                mp.start();
                System.out.println("测试播放： 准备工作完成");
                updatePlayProgress();
                Constant.playState = Constant.PLAY_PLAYING;

            }
        });

        //播放工作完成
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.i(TAG, "mediaPlayer --- setOnCompletionListener()");
                Constant.playState = Constant.PLAY_STOP;

                if (Constant.playMode == Constant.PLAYMODE_SINGLE_PLAY) {
                    //目前来说，不用管
                } else if (Constant.playMode == Constant.PLAYMODE_SINGLE_REPEAT) {
                    play();
                } else if (Constant.playMode == Constant.PLAYMODE_LIST_PLAY) {
                    if (Constant.currentPlayingPosition < MusicUtil.musicList.size() - 1) {
                        Constant.currentPlayingPosition++;
                        Constant.currentPlayingMusic = MusicUtil.musicList.get(Constant.currentPlayingPosition);
                        play();
                    }
                } else if (Constant.playMode == Constant.PLAYMODE_LIST_REPEAT) {
                    if (Constant.currentPlayingPosition < MusicUtil.musicList.size() - 1) {
                        Constant.currentPlayingPosition++;
                    } else {
                        Constant.currentPlayingPosition = 0;
                    }

                    Constant.currentPlayingMusic = MusicUtil.musicList.get(Constant.currentPlayingPosition);
                    play();
                }

            }
        });


        //出错了。。。
        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                if (null == Constant.currentPlayingMusic) {
                    Log.i(TAG, "音乐文件不存在，加载时出错");
                } else {
                    Log.i(TAG, "mediaPlayer --- setOnErrorListener");
                }

                return true;
            }
        });
    }


    /**
     * 每隔一秒更新播放的进度条
     */
    private void updatePlayProgress() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    while (Constant.playState == Constant.PLAY_PLAYING) {

                        Thread.sleep(1000);
                        MainActivity.handler.sendEmptyMessage(MainActivity.DO_PLAY_UPDATE_PROGRESS);
                    }
                } catch (Exception e) {
                }

            }
        }.start();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            mediaPlayer = null;
        }
    }
}
