package com.cakes.musicplayer.play;


import com.cakes.musicplayer.music.MusicInfoBean;
import com.cakes.musicplayer.utils.LogUtil;

import java.util.List;

public class PlayHelper {

    private final String TAG = "PlayHelper";

    private static PlayHelper INSTANCE;

    private List<MusicInfoBean> playingList;
    private int playingPosition;

    private PlayHelper() {

    }

    public static PlayHelper getInstance() {
        if (null == INSTANCE) {
            synchronized (PlayHelper.class) {
                if (null == INSTANCE) {
                    INSTANCE = new PlayHelper();
                }
            }
        }

        return INSTANCE;
    }


    public List<MusicInfoBean> getPlayingList() {
        return playingList;
    }

    public void setPlayingListAndPosition(List<MusicInfoBean> playingList, int playingPosition) {
        this.playingPosition = playingPosition;
        setPlayingList(playingList);
        showList();

    }

    public void setPlayingList(List<MusicInfoBean> playingList) {
        this.playingList = playingList;
        showList();

    }

    private void showList() {
        if (null != playingList) {
            LogUtil.d(TAG, "playingList.size() = " + playingList.size());
            int i = 0;
            for (MusicInfoBean bean : playingList) {
                LogUtil.d(TAG, "playingList--bean = " + bean.toString());

                if (i >= 5) {
                    break;
                }
                i++;
            }
        }
    }

    public int getPlayingPosition() {
        return playingPosition;
    }

    public void setPlayingPosition(int playingPosition) {
        this.playingPosition = playingPosition;
    }
}
