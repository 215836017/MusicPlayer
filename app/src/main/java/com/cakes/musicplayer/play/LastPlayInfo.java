package com.cakes.musicplayer.play;

public class LastPlayInfo {
    private String musicPath;
    private String name;
    private String albumPath;
    private int duration;

    public LastPlayInfo(String musicPath, String name, String albumPath, int duration) {
        this.musicPath = musicPath;
        this.name = name;
        this.albumPath = albumPath;
        this.duration = duration;
    }

    public String getMusicPath() {
        return musicPath;
    }

    public void setMusicPath(String musicPath) {
        this.musicPath = musicPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlbumPath() {
        return albumPath;
    }

    public void setAlbumPath(String albumPath) {
        this.albumPath = albumPath;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "LastPlayInfo{" +
                "musicPath='" + musicPath + '\'' +
                ", name='" + name + '\'' +
                ", albumPath='" + albumPath + '\'' +
                ", duration=" + duration +
                '}';
    }
}