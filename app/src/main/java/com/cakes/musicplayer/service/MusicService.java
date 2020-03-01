package com.cakes.musicplayer.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import com.cakes.musicplayer.music.MusicInfoBean;
import com.cakes.musicplayer.play.MediaPlayerManager;
import com.cakes.musicplayer.play.OnMusicPlayListener;

import java.util.ArrayList;
import java.util.List;

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

    private final String TAG = "MusicService";

    private final String CHANNEL_NOTIFICATION_ID = "musicService_Notification_id";

    private MusicPlayBinder musicPlayBinder = new MusicPlayBinder();
    private List<OnMusicPlayListener> playerListenerList = new ArrayList<>();
    private MediaPlayerManager mediaPlayerManager;

    private final int MSG_PLAY_COMPLETE = 0x10;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            handleMsg(msg);
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return musicPlayBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //下面这样写会报错： Bad notification for startForeground: java.lang.RuntimeException: invalid channel for service notification
//        startForeground(1, new Notification());

        //android O 以后每个Notification都需要依附一个channel，要不然就报错。那就加一个简单的channel
        // error_1. https://blog.csdn.net/kdsde/article/details/82143866
        //          https://blog.csdn.net/o279642707/article/details/82352431
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//            NotificationChannel mChannel = new NotificationChannel(CHANNEL_NOTIFICATION_ID, "诺秒贷", NotificationManager.IMPORTANCE_HIGH);
//            notificationManager.createNotificationChannel(mChannel);
//            Notification notification = new Notification.Builder(getApplicationContext(), CHANNEL_NOTIFICATION_ID).build();
//            startForeground(1, notification);
//        }

        initManagers();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayerManager.release();
        mediaPlayerManager = null;
    }

    private void initManagers() {
        mediaPlayerManager = new MediaPlayerManager(musicPlayerListener);
    }

    private void handleMsg(Message msg) {
        switch (msg.what) {
            case MSG_PLAY_COMPLETE:
                doWorkForPlayComplete();
                break;

        }
    }

    private void doWorkForPlayComplete() {

    }

    public void addMusicPlayListener(OnMusicPlayListener onMusicPlayListener) {
        if (null != onMusicPlayListener && !playerListenerList.contains(onMusicPlayListener)) {
            playerListenerList.add(onMusicPlayListener);
        }
    }

    public void removeMusicPlayListener(OnMusicPlayListener onMusicPlayListener) {
        if (null != onMusicPlayListener && playerListenerList.contains(onMusicPlayListener)) {
            playerListenerList.remove(onMusicPlayListener);
        }
    }

    public class MusicPlayBinder extends Binder {
        public MusicService getService() {
            return MusicService.this;
        }

        public void playMusic(MusicInfoBean musicInfoBean) {
            mediaPlayerManager.play(musicInfoBean);
        }

        public void pause() {
            mediaPlayerManager.pause();
        }

        public void resume() {
            mediaPlayerManager.resume();
        }

        public void stop() {
            mediaPlayerManager.stop();
        }

        public int getCurrentPosition() {
            return mediaPlayerManager.getCurrentPosition();
        }
    }

    private OnMusicPlayListener musicPlayerListener = new OnMusicPlayListener() {
        @Override
        public void onStart() {
            for (OnMusicPlayListener listener : playerListenerList) {
                listener.onStart();
            }
        }

        @Override
        public void onStop() {
            for (OnMusicPlayListener listener : playerListenerList) {
                listener.onStop();
            }
        }

        @Override
        public void onComplete() {
            for (OnMusicPlayListener listener : playerListenerList) {
                listener.onComplete();
            }
        }

        @Override
        public void onError(int errorCode) {
            for (OnMusicPlayListener listener : playerListenerList) {
                listener.onError(errorCode);
            }
        }
    };
}
