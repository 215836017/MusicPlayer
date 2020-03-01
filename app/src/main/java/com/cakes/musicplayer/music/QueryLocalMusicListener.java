package com.cakes.musicplayer.music;

import java.util.List;

public interface QueryLocalMusicListener {
    void onQueryMusicFinish(List<MusicInfoBean> musicList);
}