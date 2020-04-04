package com.cakes.musicplayer.play.last;

import com.cakes.musicplayer.utils.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

public class LastPlayHelper {

    private static final String KEY_MUSIC_PATH = "musicPath";
    private static final String KEY_NAME = "name";
    private static final String KEY_ALBUM_PATH = "albumPath";
    private static final String KEY_DURATION = "duration";

    public static String getStrOfLastPlayInfo(LastPlayInfo info) {
        try {
            JSONObject json = new JSONObject();
            json.put(KEY_NAME, info.getName());
            json.put(KEY_MUSIC_PATH, info.getMusicPath());
            json.put(KEY_ALBUM_PATH, info.getAlbumPath());
            json.put(KEY_DURATION, info.getDuration());

            return json.toString();
        } catch (JSONException e) {
            LogUtil.e("LastPlayHelper", "getStrOfLastPlayInfo(): " + e.getMessage());
            return null;
        }
    }

    public static LastPlayInfo getLastPlayInfoFromStr(String playInfo) {
        try {
            JSONObject jsonObject = new JSONObject(playInfo);
            String musicPath = jsonObject.getString(KEY_MUSIC_PATH);
            String name = jsonObject.getString(KEY_NAME);
            String albumPath = jsonObject.getString(KEY_ALBUM_PATH);
            int duration = jsonObject.getInt(KEY_DURATION);
            return new LastPlayInfo(musicPath, name, albumPath, duration);
        } catch (JSONException e) {
            LogUtil.e("LastPlayHelper", "getStrOfLastPlayInfo(): " + e.getMessage());

            return null;
        }
    }

}
