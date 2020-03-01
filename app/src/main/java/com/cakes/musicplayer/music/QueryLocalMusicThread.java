package com.cakes.musicplayer.music;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.cakes.musicplayer.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class QueryLocalMusicThread extends Thread {

    private final String TAG = "QueryLocalMusicThread";

    /*** 查询的排序方式 */
    private String sortOrder = MediaStore.Audio.Albums.DEFAULT_SORT_ORDER;  //即默认以album_key排序

    /*** 内部存储的URI */
    private static final Uri uriInternal = MediaStore.Audio.Media.INTERNAL_CONTENT_URI;
    /*** 外部存储的URI，即sdcard上的存储 */
    private static final Uri uriExternal = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

    private Context context;
    private boolean isSdcardMusic;

    private QueryLocalMusicListener queryListener;

    public QueryLocalMusicThread(Context context) {
        this.context = context;
        this.isSdcardMusic = true;
    }

    @Override
    public void run() {
        super.run();

        queryLocalMusics();
    }

    public void setQueryListener(QueryLocalMusicListener listener) {
        this.queryListener = listener;
    }

    /**
     * 设置从内部存储查找还是从外部存储(sdcard存储)查找. 如果不调用该方法, 则构造方法中默认为true
     *
     * @param sdcardMusic true: 从外部存储(sdcard存储查找); false: 从内部存储查找
     */
    public void setSdcardMusic(boolean sdcardMusic) {
        isSdcardMusic = sdcardMusic;
    }

    /**
     * 获取存储的全部音乐文件
     */
    private void queryLocalMusics() {
        LogUtil.i(TAG, "queryLocalMusics() start: " + System.currentTimeMillis());
        List<MusicInfoBean> musicList = new ArrayList<>();
        Uri musicUri;
        if (isSdcardMusic) {
            musicUri = uriExternal;
        } else {
            musicUri = uriInternal;
        }

        Cursor cursor = context.getContentResolver().query(musicUri, null, null,
                null, sortOrder);
        if (cursor.getCount() <= 0) {
            return;
        }

        //刚开始这句很重要
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {

            MusicInfoBean musicInfoBean = new MusicInfoBean();

            int isMuic = cursor.getInt(cursor.
                    getColumnIndex(MediaStore.Audio.Media.IS_MUSIC)); // 是否为音乐，非音乐为0
            long id = cursor.getLong(cursor
                    .getColumnIndex(MediaStore.Audio.Media._ID)); // 音乐id
            long albumId = cursor.getLong(cursor
                    .getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));// 专辑id
            long duration = cursor.getLong(cursor
                    .getColumnIndex(MediaStore.Audio.Media.DURATION)); // 时长
            long musicSize = cursor.getLong(cursor
                    .getColumnIndex(MediaStore.Audio.Media.SIZE)); // 音乐文件大小
            String title = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.TITLE));// 音乐标题
            String artist = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.ARTIST));// 艺术家
            String album = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.ALBUM));// 专辑
            String displayName = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));// 歌曲名字
            String musicPath = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.DATA));// 文件路径

            if (isMuic != 0) {
                musicInfoBean.setSongId(id);
                musicInfoBean.setAlbumId(albumId);
                musicInfoBean.setDuration(duration);
                musicInfoBean.setSize(musicSize);
                musicInfoBean.setTitle(title);
                musicInfoBean.setArtist(artist);
                musicInfoBean.setAlbum(album);
                musicInfoBean.setDisplayName(displayName);
                musicInfoBean.setPath(musicPath);
//                LogUtil.d(TAG, "queryLocalMusics() -- musicInfoBean = " + musicInfoBean.toString());
                musicList.add(musicInfoBean);
            }
            //一定要记得
            cursor.moveToNext();
        }

        if (isSdcardMusic) {
            LogUtil.d(TAG, "EXTERNAL, find " + musicList.size() + " music files");
        } else {
            LogUtil.d(TAG, "INTERNAL, find " + musicList.size() + " music files");
        }
        LogUtil.i(TAG, "getMusicList() end: " + System.currentTimeMillis());

        if (null != queryListener) {
            queryListener.onQueryMusicFinish(musicList);
        }

    }
}