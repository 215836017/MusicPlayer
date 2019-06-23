package com.test.musicplayer.music;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

import com.test.musicplayer.utils.LogUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * MediaStore的学习： （1）http://blog.csdn.net/wuqingyidongren/article/details/53640399
 * （2）https://www.oschina.net/question/16_7603
 * （3）http://blog.csdn.net/chengkaizone/article/details/51858777
 */

public class MusicFileHelper implements GetMusicProcessListener {

    private final String tag = "MusicFileHelper.java";

    public static List<MusicInfoBean> sdcardMusicList = new ArrayList<>();
    public static List<MusicInfoBean> innerMusicList = new ArrayList<>();

    /*** 在新的线程中查找music文件的工作是否完成了 */
    public static boolean isGetMusicFinish = false;

    private Context context;
    private GetMusicsThread thread;

    public MusicFileHelper(Context context) {
        this.context = context;
        thread = new GetMusicsThread(context.getApplicationContext());
        thread.setGetMusicProcessListener(this);
    }

    /**
     * 获取内存中存储的音乐文件
     */
    public void getInnerMusicList() {
        thread.setSdcardMusic(false);
        thread.start();
    }

    /**
     * 获取sdcard上存储的音乐文件
     */
    public void getOutMusicList() {

        // TODO: 需要申请权限 -- 动态申请权限
        thread.start();
    }

    @Override
    public void onGetMusicFinish() {
        isGetMusicFinish = true;
    }

    /**
     * 删除music文件
     *
     * @param path music文件的绝对路径
     * @return true:删除成功; false:路径对应的文件不存在或删除失败
     */
    public boolean deleteMusicFile(String path) {
        File file2Del = new File(path);
        if (file2Del.exists()) {
            return file2Del.delete();
        }

        return false;
    }

}
