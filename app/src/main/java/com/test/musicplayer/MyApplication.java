package com.test.musicplayer;

import android.app.Application;
import android.content.Intent;
import android.os.Build;

import com.test.musicplayer.music.MusicFileHelper;
import com.test.musicplayer.service.MusicService;
import com.test.musicplayer.utils.CrashHandler;
import com.test.musicplayer.utils.LogUtil;

public class MyApplication extends Application {

    private final String tag = "MyApplication.java";

    @Override
    public void onCreate() {
        super.onCreate();

//          CrashHandler.getInstance().init(getApplicationContext());
        LogUtil.i(tag, "modify on 2019.05.20");
        MusicFileHelper musicFileHelper = new MusicFileHelper(this);
        musicFileHelper.getOutMusicList();

        Intent intent = new Intent(this, MusicService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent);  // error_1. https://blog.csdn.net/kdsde/article/details/82143866
        } else {
            startService(intent);
        }
    }
}
