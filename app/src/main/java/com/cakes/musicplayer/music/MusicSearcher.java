package com.cakes.musicplayer.music;

import android.content.Context;

import com.cakes.musicplayer.threads.ThreadPoolHelper;

/**
 * MediaStore的学习： （1）http://blog.csdn.net/wuqingyidongren/article/details/53640399
 * （2）https://www.oschina.net/question/16_7603
 * （3）http://blog.csdn.net/chengkaizone/article/details/51858777
 */

public class MusicSearcher {

    private QueryLocalMusicRunnable queryLocalMusicRunnable;

    public MusicSearcher(Context context, QueryLocalMusicListener queryLocalMusicListener) {
        queryLocalMusicRunnable = new QueryLocalMusicRunnable(context.getApplicationContext(), queryLocalMusicListener);
    }

    /**
     * 获取内存中存储的音乐文件
     */
    public void queryInnerMusicFiles() {
        queryLocalMusicRunnable.setSdcardMusic(false);
        ThreadPoolHelper.getInstance().addTask(queryLocalMusicRunnable);
    }

    /**
     * 获取sdcard上存储的音乐文件
     */
    public void querySdcardMusicFiles() {
        queryLocalMusicRunnable.setSdcardMusic(true);
        // TODO: 需要申请权限 -- 动态申请权限
        ThreadPoolHelper.getInstance().addTask(queryLocalMusicRunnable);
    }
}
