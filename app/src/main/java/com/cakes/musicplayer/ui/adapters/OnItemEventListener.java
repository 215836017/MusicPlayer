package com.cakes.musicplayer.ui.adapters;

import com.cakes.musicplayer.music.MusicInfoBean;

public interface OnItemEventListener {

    void playMusic(int position, MusicInfoBean musicInfoBean);

    void onItemClick(int position, MusicInfoBean musicInfoBean);
}