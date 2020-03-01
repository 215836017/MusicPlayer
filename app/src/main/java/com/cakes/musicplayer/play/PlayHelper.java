package com.cakes.musicplayer.play;

import java.util.Random;

public class PlayHelper {

    private String[] pathSdcard = {
            "sdcard/a.mp3",
            "sdcard/b.mp3",
            "sdcard/c.mp3"
    };

    public int changeMusicOrder(int currentPlayIndex, int musicCount, boolean isNext) {

        int index = 0;
        if (isNext) {
            if (currentPlayIndex == (musicCount - 1)) {
                index = 0;
            } else {
                index = currentPlayIndex++;
            }
        } else {
            if (currentPlayIndex == 0) {
                index = musicCount - 1;
            } else {
                index = currentPlayIndex--;
            }
        }
        return index;
    }

    public int changeMusicRandom(int musicCount) {

        Random random = new Random();
        return random.nextInt(musicCount);
    }
}
