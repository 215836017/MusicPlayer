package com.cakes.musicplayer.music;

import android.content.Context;

/**
 * MediaStore的学习： （1）http://blog.csdn.net/wuqingyidongren/article/details/53640399
 * （2）https://www.oschina.net/question/16_7603
 * （3）http://blog.csdn.net/chengkaizone/article/details/51858777
 */

public class MusicSearcher {

    private QueryLocalMusicThread thread;

    public MusicSearcher(Context context, QueryLocalMusicListener queryLocalMusicListener) {
        thread = new QueryLocalMusicThread(context.getApplicationContext());
        thread.setQueryListener(queryLocalMusicListener);
    }

    /**
     * 获取内存中存储的音乐文件
     */
    public void queryInnerMusicFiles() {
        thread.setSdcardMusic(false);
        thread.start();
    }

    /**
     * 获取sdcard上存储的音乐文件
     */
    public void querySdcardMusicFiles() {
        thread.setSdcardMusic(true);
        // TODO: 需要申请权限 -- 动态申请权限
        thread.start();
    }
}
