package com.cakes.musicplayer;

import android.app.Application;

import com.cakes.musicplayer.utils.LogUtil;

public class MusicApplication extends Application {

    private final String tag = "MusicApplication";

    @Override
    public void onCreate() {
        super.onCreate();

//          CrashHandler.getInstance().init(getApplicationContext());
        LogUtil.i(tag, "modify on 2019.05.20");
//        MusicFileHelper musicFileHelper = new MusicFileHelper(this);
//        musicFileHelper.getOutMusicList();

//        Intent intent = new Intent(this, MusicService.class);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            startForegroundService(intent);  // error_1. https://blog.csdn.net/kdsde/article/details/82143866
//        } else {
//            startService(intent);
//        }
    }
}
