package com.fzq.musicplayer.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.fzq.musicplayer.bean.MusicInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fzq on 2017/5/23.
 * MediaStore的学习： （1）http://blog.csdn.net/wuqingyidongren/article/details/53640399
 * （2）https://www.oschina.net/question/16_7603
 * （3）http://blog.csdn.net/chengkaizone/article/details/51858777
 */

public class MusicUtil {

    /**
     * 全局的唯一的查询后保存音乐信息的列表
     */
    public static List<MusicInfo> musicList = new ArrayList<>();

    /**
     * 是否要查询内部存储的音乐 默认为true-是
     */
    public boolean isQuqryInner = true;

    /**
     * 查询的排序方式
     */
    private String sortOrder = MediaStore.Audio.Albums.DEFAULT_SORT_ORDER;  //即默认以album_key排序


    private static MusicUtil instance;

    public static MusicUtil getIntance() {
        if (null == instance) {
            instance = new MusicUtil();
        }

        return instance;
    }


    public void getGlobalMusicList(Context context) {

        //MediaStore.Audio.Albums 部分
        String str1 = (MediaStore.Audio.Albums.getContentUri("aaa")).toString();
        String str2 = (MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI).toString();
        String str3 = (MediaStore.Audio.Albums.INTERNAL_CONTENT_URI).toString();
        System.out.println("\nMediaStore.Audio.Albums 部分:"
                + "\nMediaStore.Audio.Albums.getContentUri() ---- " + str1
                + "\nMediaStore.Audio.Albums.EXTERNAL_CONTENT_URI ---- " + str2
                + "\nMediaStore.Audio.Albums.INTERNAL_CONTENT_URI ---- " + str3);


        //MediaStore.Audio.Artists.Albums部分
        String str4 = (MediaStore.Audio.Artists.Albums.getContentUri("aaa", 123)).toString();
        System.out.println("\nMediaStore.Audio.Artists.Albums部分:"
                + "\nMediaStore.Audio.Artists.Albums.getContentUri() ---- " + str4);

        //MediaStore.Audio.Artists部分
        String str5 = (MediaStore.Audio.Artists.getContentUri("aaa")).toString();
        String str6 = (MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI).toString();
        String str7 = (MediaStore.Audio.Artists.INTERNAL_CONTENT_URI).toString();
        System.out.println("\nMediaStore.Audio.Artists部分:"
                + "\nMediaStore.Audio.Artists.getContentUri() ---- " + str5
                + "\nMediaStore.Audio.Artists.EXTERNAL_CONTENT_URI ---- " + str6
                + "\nMediaStore.Audio.Artists.INTERNAL_CONTENT_URI ---- " + str7);

        //MediaStore.Audio.Genres.Members部分
        String str8 = (MediaStore.Audio.Genres.Members.getContentUri("aaa", 123)).toString();
        System.out.println("\nMediaStore.Audio.Genres.Members部分:"
                + "\nMediaStore.Audio.Genres.Members.getContentUri() ---- " + str8);

        // MediaStore.Audio.Genres部分
        String str9 = (MediaStore.Audio.Genres.getContentUri("aaa")).toString();
        String str10 = (MediaStore.Audio.Genres.getContentUriForAudioId("aaa", 123)).toString();
        String str11 = (MediaStore.Audio.Genres.EXTERNAL_CONTENT_URI).toString();
        String str12 = (MediaStore.Audio.Genres.INTERNAL_CONTENT_URI).toString();
        System.out.println("\nMediaStore.Audio.Genres部分:"
                + "\nMediaStore.Audio.Genres.getContentUri() ---- " + str9
                + "\nMediaStore.Audio.Genres.getContentUriForAudioId() ---- " + str10
                + "\nMediaStore.Audio.Genres.EXTERNAL_CONTENT_URI ---- " + str11
                + "\nMediaStore.Audio.Genres.INTERNAL_CONTENT_URI ---- " + str12);

        // MediaStore.Audio.Media部分
        String str13 = (MediaStore.Audio.Media.getContentUri("aaa")).toString();
        String str14 = (MediaStore.Audio.Media.getContentUriForPath("aaa")).toString();
        String str15 = (MediaStore.Audio.Media.INTERNAL_CONTENT_URI).toString();
        String str16 = (MediaStore.Audio.Media.EXTERNAL_CONTENT_URI).toString();
        System.out.println("\nMediaStore.Audio.Media部分:"
                + "\nMediaStore.Audio.Media.getContentUri() ---- " + str13
                + "\nMediaStore.Audio.Media.getContentUriForPath() ---- " + str14
                + "\nMediaStore.Audio.Media.INTERNAL_CONTENT_URI ---- " + str15
                + "\nMediaStore.Audio.Media.EXTERNAL_CONTENT_URI ---- " + str16);

        // MediaStore.Audio.Playlists.Members部分
        String str17 = (MediaStore.Audio.Playlists.Members.getContentUri("aaa", 123)).toString();
        System.out.println("\nMediaStore.Audio.Playlists.Members部分:"
                + "\nMediaStore.Audio.Playlists.Members.getContentUri() ---- " + str17);

        // MediaStore.Audio.Playlists部分
        String str18 = (MediaStore.Audio.Playlists.getContentUri("aaa")).toString();
        String str19 = (MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI).toString();
        String str20 = (MediaStore.Audio.Playlists.INTERNAL_CONTENT_URI).toString();
        System.out.println("\nMediaStore.Audio.Playlists部分:"
                + "\nMediaStore.Audio.Playlists.getContentUri() ---- " + str18
                + "\nMediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI ---- " + str19
                + "\nMediaStore.Audio.Playlists.INTERNAL_CONTENT_URI ---- " + str20);

        // MediaStore部分
        String str21 = MediaStore.getMediaScannerUri().toString();
        System.out.println("\nMediaStore部分:"
                + "\nMediaStore.getMediaScannerUri() ---- " + str21);

        //打印结果见最下面。


        Uri uriInner = MediaStore.Audio.Media.INTERNAL_CONTENT_URI;
        Uri uriOut = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        if (isQuqryInner) {
            getMusicList(context, uriInner);
        }

        getMusicList(context, uriOut);
    }

    /**
     * 获取内部存储的全部音乐文件
     *
     * @param context
     */
    private void getMusicList(Context context, Uri uri) {
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, sortOrder);

        if (cursor.getCount() <= 0) {
            return;
        }

        //刚开始这句很重要
        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {

            MusicInfo musicInfo = new MusicInfo();

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
//            String title = cursor.getString(cursor
//                    .getColumnIndex("_display_name"));// 音乐标题
            String artist = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.ARTIST));// 艺术家
            String album = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.ALBUM));// 专辑
            String displayName = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));// 歌曲名字
            String musicPath = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.DATA));// 文件路径
//            String musicPath = cursor.getString(cursor
//                    .getColumnIndex("_data"));// 文件路径

            if (isMuic != 0) {
                System.out.println("hahahaha");
                musicInfo.setSongId(id);
                musicInfo.setAlbumId(albumId);
                musicInfo.setDuration(duration);
                musicInfo.setSize(musicSize);
                musicInfo.setTitle(title);
                musicInfo.setArtist(artist);
                musicInfo.setAlbum(album);
                musicInfo.setDisplayName(displayName);
                musicInfo.setPath(musicPath);

                musicList.add(musicInfo);
            }

            //一定要记得
            cursor.moveToNext();
        }

        System.out.println("总共找到" + musicList.size() + "个音乐文件");
    }


}


    /*


06-24 10:07:11.149 27968-27968/com.fzq.musicplayer I/InstantRun: starting instant run server: is main process

06-24 10:07:11.155 27968-27968/com.fzq.musicplayer I/System.out: MediaStore.Audio.Albums 部分:
06-24 10:07:11.155 27968-27968/com.fzq.musicplayer I/System.out: MediaStore.Audio.Albums.getContentUri() ---- content://media/aaa/audio/albums
06-24 10:07:11.155 27968-27968/com.fzq.musicplayer I/System.out: MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI ---- content://media/external/audio/albums
06-24 10:07:11.155 27968-27968/com.fzq.musicplayer I/System.out: MediaStore.Audio.Albums.INTERNAL_CONTENT_URI ---- content://media/internal/audio/albums

06-24 10:07:11.155 27968-27968/com.fzq.musicplayer I/System.out: MediaStore.Audio.Artists.Albums部分:
06-24 10:07:11.155 27968-27968/com.fzq.musicplayer I/System.out: MediaStore.Audio.Artists.Albums.getContentUri() ---- content://media/aaa/audio/artists/123/albums

06-24 10:07:11.155 27968-27968/com.fzq.musicplayer I/System.out: MediaStore.Audio.Artists部分:
06-24 10:07:11.155 27968-27968/com.fzq.musicplayer I/System.out: MediaStore.Audio.Artists.getContentUri() ---- content://media/aaa/audio/artists
06-24 10:07:11.155 27968-27968/com.fzq.musicplayer I/System.out: MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI ---- content://media/external/audio/artists
06-24 10:07:11.155 27968-27968/com.fzq.musicplayer I/System.out: MediaStore.Audio.Artists.INTERNAL_CONTENT_URI ---- content://media/internal/audio/artists

06-24 10:07:11.155 27968-27968/com.fzq.musicplayer I/System.out: MediaStore.Audio.Genres.Members部分:
06-24 10:07:11.155 27968-27968/com.fzq.musicplayer I/System.out: MediaStore.Audio.Genres.Members.getContentUri() ---- content://media/aaa/audio/genres/123/members

06-24 10:07:11.156 27968-27968/com.fzq.musicplayer I/System.out: MediaStore.Audio.Genres部分:
06-24 10:07:11.156 27968-27968/com.fzq.musicplayer I/System.out: MediaStore.Audio.Genres.getContentUri() ---- content://media/aaa/audio/genres
06-24 10:07:11.156 27968-27968/com.fzq.musicplayer I/System.out: MediaStore.Audio.Genres.getContentUriForAudioId() ---- content://media/aaa/audio/media/123/genres
06-24 10:07:11.156 27968-27968/com.fzq.musicplayer I/System.out: MediaStore.Audio.Genres.EXTERNAL_CONTENT_URI ---- content://media/external/audio/genres
06-24 10:07:11.156 27968-27968/com.fzq.musicplayer I/System.out: MediaStore.Audio.Genres.INTERNAL_CONTENT_URI ---- content://media/internal/audio/genres

06-24 10:07:11.157 27968-27968/com.fzq.musicplayer I/System.out: MediaStore.Audio.Media部分:
06-24 10:07:11.157 27968-27968/com.fzq.musicplayer I/System.out: MediaStore.Audio.Media.getContentUri() ---- content://media/aaa/audio/media
06-24 10:07:11.157 27968-27968/com.fzq.musicplayer I/System.out: MediaStore.Audio.Media.getContentUriForPath() ---- content://media/internal/audio/media
06-24 10:07:11.157 27968-27968/com.fzq.musicplayer I/System.out: MediaStore.Audio.Media.INTERNAL_CONTENT_URI ---- content://media/internal/audio/media
06-24 10:07:11.158 27968-27968/com.fzq.musicplayer I/System.out: MediaStore.Audio.Media.EXTERNAL_CONTENT_URI ---- content://media/external/audio/media

06-24 10:07:11.158 27968-27968/com.fzq.musicplayer I/System.out: MediaStore.Audio.Playlists.Members部分:
06-24 10:07:11.158 27968-27968/com.fzq.musicplayer I/System.out: MediaStore.Audio.Playlists.Members.getContentUri() ---- content://media/aaa/audio/playlists/123/members

06-24 10:07:11.158 27968-27968/com.fzq.musicplayer I/System.out: MediaStore.Audio.Playlists部分:
06-24 10:07:11.158 27968-27968/com.fzq.musicplayer I/System.out: MediaStore.Audio.Playlists.getContentUri() ---- content://media/aaa/audio/playlists
06-24 10:07:11.158 27968-27968/com.fzq.musicplayer I/System.out: MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI ---- content://media/external/audio/playlists
06-24 10:07:11.158 27968-27968/com.fzq.musicplayer I/System.out: MediaStore.Audio.Playlists.INTERNAL_CONTENT_URI ---- content://media/internal/audio/playlists

06-24 10:07:11.158 27968-27968/com.fzq.musicplayer I/System.out: MediaStore部分:
06-24 10:07:11.158 27968-27968/com.fzq.musicplayer I/System.out: MediaStore.getMediaScannerUri() ---- content://media/none/media_scanner

06-24 10:07:11.182 27968-27968/com.fzq.musicplayer I/System.out: hahahaha


     */


