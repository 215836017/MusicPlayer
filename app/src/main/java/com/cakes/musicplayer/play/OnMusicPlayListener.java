package com.cakes.musicplayer.play;

import com.cakes.musicplayer.music.MusicInfoBean;

public interface OnMusicPlayListener {
    void onStart(MusicInfoBean currentMusicInfo, int duration);

    void onStop(MusicInfoBean currentMusicInfo);

    void onComplete(MusicInfoBean currentMusicInfo);

    void onProgress(int currentDuration);

    void onError(MusicInfoBean currentMusicInfo, int errorCode);
}

//public interface OnMusicServiceListener {
//    void onPlayStart();
//
//    void onPlayFinsh();
//
//    void onPlayStoped();
//
//    void onPlayError();
//}