package com.cakes.musicplayer.music;

import java.util.ArrayList;
import java.util.List;

public class MusicList {

    private List<MusicInfoBean> sdcardMusicList = new ArrayList<>();
    private List<MusicInfoBean> innerMusicList = new ArrayList<>();

    private static MusicList INSTANCE;

    public static MusicList getInstance() {
        if (null == INSTANCE) {
            synchronized (MusicList.class) {
                if (null == INSTANCE) {
                    INSTANCE = new MusicList();
                }
            }
        }

        return INSTANCE;
    }

    public List<MusicInfoBean> getSdcardMusicList() {
        return sdcardMusicList;
    }

    public void setSdcardMusicList(List<MusicInfoBean> sdcardMusicList) {
        this.sdcardMusicList = sdcardMusicList;
    }

    public List<MusicInfoBean> getInnerMusicList() {
        return innerMusicList;
    }

    public void setInnerMusicList(List<MusicInfoBean> innerMusicList) {
        this.innerMusicList = innerMusicList;
    }

    public void addMusicBean(MusicInfoBean bean, boolean isSdcard) {
        if (null != bean) {
            if (isSdcard) {
                if (!sdcardMusicList.contains(bean)) {
                    sdcardMusicList.add(bean);
                }

            } else {
                if (!innerMusicList.contains(bean)) {
                    innerMusicList.add(bean);
                }
            }
        }
    }

    public void removeMusicBean(MusicInfoBean bean, boolean isSdcard) {
        if (null != bean) {
            try {
                if (isSdcard) {
                    sdcardMusicList.remove(bean);

                } else {
                    innerMusicList.remove(bean);
                }
            } catch (Exception e) {
            }
        }
    }
}
