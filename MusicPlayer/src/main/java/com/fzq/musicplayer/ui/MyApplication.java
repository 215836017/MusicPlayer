package com.fzq.musicplayer.ui;

import android.app.Application;

import com.fzq.musicplayer.utils.MusicUtil;

/**
 * Created by fzq on 2017/6/14.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        MusicUtil.getIntance().getGlobalMusicList(this);
    }
}
