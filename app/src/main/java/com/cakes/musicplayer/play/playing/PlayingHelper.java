package com.cakes.musicplayer.play.playing;

import com.cakes.musicplayer.music.MusicInfoBean;
import com.cakes.musicplayer.utils.LogUtil;

import java.util.List;

public class PlayingHelper {

    private final String TAG = "PlayingHelper";

    private static PlayingHelper INSTANCE;

    private List<MusicInfoBean> playingList;
    private int playingPosition;

    private PlayingHelper() {

    }

    public static PlayingHelper getInstance() {
        if (null == INSTANCE) {
            synchronized (PlayingHelper.class) {
                if (null == INSTANCE) {
                    INSTANCE = new PlayingHelper();
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
//        showList();

    }

    public void setPlayingList(List<MusicInfoBean> playingList) {
        this.playingList = playingList;
//        showList();

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

    public MusicInfoBean getPlayingInfoBean() {
        try {
            return playingList.get(playingPosition);
        } catch (Exception e) {
            return null;
        }
    }
}
