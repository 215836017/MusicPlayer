package com.fzq.musicplayer;


import com.fzq.musicplayer.bean.MusicInfo;

/**
 * Created by fzq on 2017/5/13.
 * 保存全局相关的各种字段或对象。  类似于配置文件
 */
public class Constant {

    /**
     * SharedPreferens的name
     */
    public final static String spName = "MyMusicPlayer";
    ///////////////////////////SharedPreferens中保存信息的字段//////////////////////////////////////
    /**
     * 正在播放的music的路径，这样的话程序启动后就会加载上次的路径
     */
    public final static String PLAYING_MUSIC_PATH = "currentPlayingPath";

    /////////////////////////// 音乐文件播放/暂停相关的信息 //////////////////////////////////
    /**
     * 需要或正在播放的音乐文件
     */
    public static MusicInfo currentPlayingMusic = null;
    /**
     * 标识播放状态
     */
    public final static int PLAY_STOP = 0;
    public final static int PLAY_PLAYING = 1;
    public final static int PLAY_PAUSE = 2;
    /**
     * 播放状态, 0:停止， 1：播放中， 2：暂停
     */
    public static int playState = 0;

    /**
     * 暂停时的播放时长
     */
    public static int currentProgress = 0;
    /** 暂停时保存的文件的路径，这个要保存到sp中，再次启动时根据这个找上次的music信息 */
    public static String currentPlayingPath;

    public static final int PLAYMODE_SINGLE_PLAY = 0;
    public static final int PLAYMODE_SINGLE_REPEAT = 1;
    public static final int PLAYMODE_LIST_PLAY = 3;
    public static final int PLAYMODE_LIST_REPEAT = 4;
    /** 播放模式 */
    public static int playMode = 0;

    /** 当前正在播放音乐文件在musicList中的位置， 在列表播放或列表循环中用到 */
    public static int currentPlayingPosition = 0;

}
