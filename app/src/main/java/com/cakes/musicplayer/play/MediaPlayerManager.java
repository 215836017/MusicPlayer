package com.cakes.musicplayer.play;

import android.media.MediaPlayer;
import android.text.TextUtils;
import android.util.Log;

import com.cakes.musicplayer.music.MusicInfoBean;
import com.cakes.musicplayer.utils.LogUtil;

import java.io.IOException;

public class MediaPlayerManager {
    private static final String TAG = "MediaPlayerManager";

    private MediaPlayer mediaPlayer;
    private OnMusicPlayListener musicPlayerListener;

    private boolean isPreparing;
    private MusicInfoBean currentMusicInfo;

    public MediaPlayerManager(OnMusicPlayListener listener) {
        mediaPlayer = new MediaPlayer();
        this.musicPlayerListener = listener;
        isPreparing = false;
        setMediaplayerListeners();
    }

    private void setMediaplayerListeners() {
        mediaPlayer.setOnErrorListener(mOnErrorListener);
        mediaPlayer.setOnCompletionListener(mOnCompletionListener);
        mediaPlayer.setOnPreparedListener(mOnPreparedListener);
    }

    public boolean isPreparing() {
        return isPreparing;
    }

    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    /**
     * 播放网络URL对应的歌曲
     *
     * @param musicPath
     */
    public void play(String musicPath) {
        if (TextUtils.isEmpty(musicPath)) {
            musicPlayerListener.onError(0);
            return;
        }

        currentMusicInfo = new MusicInfoBean();
        currentMusicInfo.setPath(musicPath);
        prepare(musicPath);
    }

    /**
     * 播放本地存在的文件
     *
     * @param musicInfoBean
     */
    public void play(MusicInfoBean musicInfoBean) {
        if (null == musicInfoBean) {
            musicPlayerListener.onError(0);
            return;
        }
        String musicPath = musicInfoBean.getPath();
        if (TextUtils.isEmpty(musicPath)) {
            musicPlayerListener.onError(0);
            return;
        }
        currentMusicInfo = musicInfoBean;
        prepare(musicPath);
    }

    private void prepare(String url) {
        LogUtil.i(TAG, "prepare() --- start, url = " + url);
//        try {
//            isPreparing = true;
//            mediaPlayer.reset();
//            mediaPlayer.setDataSource(url);
//            mediaPlayer.prepareAsync();
//
//        } catch (IOException e1) {
//            isPreparing = false;
//            LogUtil.d(TAG, "set datasource failed");
//            e1.printStackTrace();
//        }
    }

    public void pause() {
        mediaPlayer.pause();
    }

    public void resume() {
        mediaPlayer.start();
    }

    public void stop() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();

            if (musicPlayerListener != null) {
                musicPlayerListener.onStop();
            }
        }
    }

    public void release() {
        if (null != mediaPlayer) {
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public int getCurrentPosition() {
        return mediaPlayer.getCurrentPosition();
    }

    public void seekToPosition(int position) {
        mediaPlayer.seekTo(position);
    }

    private MediaPlayer.OnSeekCompleteListener onSeekCompleteListener = new MediaPlayer.OnSeekCompleteListener() {
        @Override
        public void onSeekComplete(MediaPlayer mp) {

        }
    };
    private MediaPlayer.OnPreparedListener mOnPreparedListener = new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mp) {
            isPreparing = false;
            Log.d(TAG, "onPrepared");
        }
    };

    private MediaPlayer.OnErrorListener mOnErrorListener = new MediaPlayer.OnErrorListener() {
        @Override
        public boolean onError(MediaPlayer mp, int what, int extra) {
            Log.d(TAG, "onError = " + what);
            isPreparing = false;
            if (musicPlayerListener != null) {
                musicPlayerListener.onError(what);
            }
            return false;
        }
    };

    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {

            if (musicPlayerListener != null) {
                musicPlayerListener.onComplete();
            }
        }
    };
}
