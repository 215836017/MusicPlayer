package com.cakes.musicplayer.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import com.cakes.musicplayer.music.Constant;
import com.cakes.musicplayer.utils.LogUtil;

public class MusicService extends Service {

    private final String tag = "MusicService.java";

    private final String CHANNEL_NOTIFICATION_ID = "musicService_Notification_id";

    private MediaPlayer mediaPlayer;

    public static Handler serviceHandler;
    public static final int msg_play = 101;
    public static final int msg_stop = 102;

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
        //   initMediaPlayer();

        return super.onStartCommand(intent, flags, startId);
    }

    // TODO
//	https://www.jianshu.com/p/46c63dfd5c89/
//	https://www.jianshu.com/p/c566903e44d0
//	https://blog.csdn.net/u012604299/article/details/78326371
// https://www.cnblogs.com/Jason-Jan/p/8459687.html#_label3
    @Override
    public void onCreate() {
        super.onCreate();

        //下面这样写会报错： Bad notification for startForeground: java.lang.RuntimeException: invalid channel for service notification
//        startForeground(1, new Notification());

        //android O 以后每个Notification都需要依附一个channel，要不然就报错。那就加一个简单的channel
        // error_1. https://blog.csdn.net/kdsde/article/details/82143866
        //          https://blog.csdn.net/o279642707/article/details/82352431
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_NOTIFICATION_ID, "诺秒贷", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(mChannel);
            Notification notification = new Notification.Builder(getApplicationContext(), CHANNEL_NOTIFICATION_ID).build();
            startForeground(1, notification);
        }


        serviceHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                handleMsg(msg);
				
			//	需要考虑使用观察者模式进行组件间的通信。
                // 参考： https://blog.csdn.net/AndroidFlying007/article/details/52761393
                //        https://blog.csdn.net/Gods_magic/article/details/84558169
            }
        };

        initMediaPlayer();

        //  playOrPause();
    }

    private void handleMsg(Message msg) {
        switch (msg.what) {
            case msg_play:
                startPlay();
                break;

            case msg_stop:
                stop();
        }
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
     * 开始/暂停播放
     */
    private void stop() {

        Constant.isPlaying = mediaPlayer.isPlaying();

        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            Constant.currentPosition = mediaPlayer.getCurrentPosition();
        }

        mediaPlayer.stop();
    }

    /**
     * 开始播放
     */
    private void startPlay() {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(Constant.currentPlayingPath);
//            mediaPlayer.seekTo(Constant.currentPosition);
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
                        LogUtil.d(tag, "updatePlayProgress() -- Constant.currentPosition = " +
                                Constant.currentPosition);
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
        mediaPlayer.setOnErrorListener(onErrorListener);
        mediaPlayer.setOnPreparedListener(onPreparedListener);
        mediaPlayer.setOnSeekCompleteListener(onSeekCompleteListener);
        mediaPlayer.setOnCompletionListener(onCompletionListener);

    }

    MediaPlayer.OnErrorListener onErrorListener = new MediaPlayer.OnErrorListener() {

        @Override
        public boolean onError(MediaPlayer mp, int what, int extra) {
            LogUtil.e(tag, "onErrorListener -- what = " + what + ", extra = " + extra);
            return false;
        }
    };

    MediaPlayer.OnPreparedListener onPreparedListener = new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mp) {
//            if (!isGetDuration) {
//                musicDuration = mp.getDuration();
//                isGetDuration = true;
//            }
//            handler.sendEmptyMessage(MediaplayerCode.EventCode.MSG_EVENT_PERPARE_OK);
        }
    };

    MediaPlayer.OnSeekCompleteListener onSeekCompleteListener = new MediaPlayer.OnSeekCompleteListener() {
        @Override
        public void onSeekComplete(MediaPlayer mp) {

        }
    };

    MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
//            handler.sendEmptyMessage(MediaplayerCode.EventCode.MSG_EVENT_PLAY_FINISH);
        }
    };
}
