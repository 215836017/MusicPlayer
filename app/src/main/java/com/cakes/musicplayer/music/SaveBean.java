package com.cakes.musicplayer.music;

public class SaveBean {

    private String musicName;
    private String musicAuthor;
    private String albumPath;

    public SaveBean(String musicName, String musicAuthor, String albumPath) {
        this.musicName = musicName;
        this.musicAuthor = musicAuthor;
        this.albumPath = albumPath;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getMusicAuthor() {
        return musicAuthor;
    }

    public void setMusicAuthor(String musicAuthor) {
        this.musicAuthor = musicAuthor;
    }

    public String getAlbumPath() {
        return albumPath;
    }

    public void setAlbumPath(String albumPath) {
        this.albumPath = albumPath;
    }

    @Override
    public String toString() {
        return "SaveBean{" +
                "musicName='" + musicName + '\'' +
                ", musicAuthor='" + musicAuthor + '\'' +
                ", albumPath='" + albumPath + '\'' +
                '}';
    }
}
