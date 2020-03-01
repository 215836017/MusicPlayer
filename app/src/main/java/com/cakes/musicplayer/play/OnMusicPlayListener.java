package com.cakes.musicplayer.play;

public interface OnMusicPlayListener {
    void onStart();

    void onStop();

    void onComplete();

    void onError(int errorCode);
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