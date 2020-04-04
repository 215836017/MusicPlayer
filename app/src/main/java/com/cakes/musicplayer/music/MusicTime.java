package com.cakes.musicplayer.music;

public class MusicTime {

    public static String getTimeStr(long duration) {

        if (duration <= 0) {
            return "";
        }
        duration = duration / 1000;
        int min = (int) (duration / 60);
        int second = (int) (duration % 60);

        String minStr = "";
        if (min < 10) {
            minStr = "0" + min;
        } else {
            minStr = min + "";
        }

        String secStr = "";
        if (second < 10) {
            secStr = "0" + second;
        } else {
            secStr = second + "";
        }

        return minStr + ":" + secStr;
    }
}
