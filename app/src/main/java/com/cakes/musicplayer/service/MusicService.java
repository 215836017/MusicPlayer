package com.cakes.musicplayer.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;

import com.cakes.musicplayer.music.Constant;
import com.cakes.musicplayer.utils.LogUtil;

// TODO
//	https://www.jianshu.com/p/46c63dfd5c89/
//	https://www.jianshu.com/p/c566903e44d0
//	https://blog.csdn.net/u012604299/article/details/78326371
// https://www.cnblogs.com/Jason-Jan/p/8459687.html#_label3
//	需要考虑使用观察者模式进行组件间的通信。
// 参考： https://blog.csdn.net/AndroidFlying007/article/details/52761393
//        https://blog.csdn.net/Gods_magic/article/details/84558169

//  多个Activity bindService的使用: https://blog.csdn.net/wangsf789/article/details/85694193
public class MusicService extends Service {

    private final String tag = "MusicService";

    private final String CHANNEL_NOTIFICATION_ID = "musicService_Notification_id";

    private MediaPlayer mediaPlayer;

    private MusicPlayBinder musicPlayBinder = new MusicPlayBinder();
    private OnMusicServiceListener musicServiceListener;

    private MusicService musicService;

    public class MusicPlayBinder extends Binder {
        public MusicService getService() {
            return MusicService.this;
        }

        public void startPlayMusic(String musicFilePath) {
            musicService.play(musicFilePath);
        }

        public void stopPlayMusic() {
            musicService.stop();
        }

        public int getPlayProgress() {
            return 0;
        }
    }

    public interface OnMusicServiceListener {
        void onPlayStart();

        void onPlayFinsh();

        void onPlayStoped();

        void onPlayError();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //   initMediaPlayer();

        return super.onStartCommand(intent, flags, startId);
    }

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

        initMediaPlayer();

        //  playOrPause();
    }

    private void play(String path) {

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
