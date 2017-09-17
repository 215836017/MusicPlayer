package com.fzq.musicplayer.ui;

import android.app.Application;

import com.fzq.musicplayer.utils.MusicUtil;
import com.fzq.musicplayer.utils.SharedPreferensUtil;

/**
 * Created by fzq on 2017/6/14.
 */

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        //程序启动时遍历手机中的music文件
        MusicUtil.getIntance().getGlobalMusicList(this);

        //初始化SharedPreferences
        SharedPreferensUtil.getInstance(this);
    }
}
