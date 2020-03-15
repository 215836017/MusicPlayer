package com.cakes.musicplayer.play;

import com.cakes.musicplayer.utils.LogUtil;

import java.util.Random;

public class PlayMode {

    private final String TAG = "PlayMode";

    /*** save to SharedPreferences, or read from SharedPreferences */
    public static final String LAST_PLAY_MODE = "lastPlayMode";

    public static final int PLAY_MODE_JUST_SINGLE = 0;
    public static final int PLAY_MODE_LIST_ORDER = 1;
    public static final int PLAY_MODE_LIST_LOOP = 2;
    public static final int PLAY_MODE_SINGLE_LOOP = 3;
    public static final int PLAY_MODE_RANDOM = 4;

    private static PlayMode INSTANCE;
    private int playMode;
    private Random random;

    private PlayMode() {
        playMode = PLAY_MODE_JUST_SINGLE;
    }

    public static PlayMode getInstance() {
        if (null == INSTANCE) {
            synchronized (PlayMode.class) {
                if (null == INSTANCE) {
                    INSTANCE = new PlayMode();
                }
            }
        }

        return INSTANCE;
    }

    public int getPlayMode() {
        return playMode;
    }

    public void setPlayMode(int playMode) {
        this.playMode = playMode;
    }

    public int getNextPosition(int currentPosition, int listLength) {
        LogUtil.d(TAG, "getNextPosition() --playMode = " + playMode
                + ",currentPosition = " + currentPosition
                + ", listLength = " + listLength);
        switch (playMode) {
            case PLAY_MODE_JUST_SINGLE:
                return 1;

            case PLAY_MODE_LIST_ORDER:
                if (currentPosition < (listLength - 1)) {
                    return currentPosition + 1;
                } else {
                    return -1;
                }

            case PLAY_MODE_LIST_LOOP:
                if (currentPosition < (listLength - 1)) {
                    return currentPosition + 1;
                } else if (currentPosition == (listLength - 1)) {
                    return 0;
                }

            case PLAY_MODE_SINGLE_LOOP:
                return currentPosition;

            case PLAY_MODE_RANDOM:
                if (null == random) {
                    random = new Random();
                }
                return random.nextInt(listLength);
        }

        return -1;
    }
}
