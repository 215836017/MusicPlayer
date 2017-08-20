package com.fzq.musicplayer;


/**
 * Created by fzq on 2017/5/13.
 */

public class Constant {

    /** SharedPreferens的name */
    public final static String spName = "MyMusicPlayer";
    ///////////////////////////SharedPreferens中保存信息的字段//////////////////////////////////////
    /** 正在播放的music的路径，这样的话程序启动后就会加载上次的路径 */
    public final static String PLAYING_MUSIC_PATH = "currentPlayingPath";


    /** 需要播放的音乐文件的路径 */
    public static String currentPlayingPath = "";

    /** 是否正在播放中 */
    public static boolean isPlaying;

    /** 播放进度，计划100ms刷新一次 */
    public static int currentPosition;

    /** 是否正在播放中 */
    //public static boolean isPlaying;




}
