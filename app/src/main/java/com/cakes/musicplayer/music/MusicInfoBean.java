package com.cakes.musicplayer.music;

public class MusicInfoBean {

    private final String UNKNOWN = "unknown";
    private final String UNKNOWN_ZH = "未知歌手";

    private long songId;   // 歌曲ID
    private String title;  //音乐标题
    private String album;  //专辑
    private long albumId;  //专辑ID
    private String displayName;   //歌曲名称
    private String musicNameNoTail;   //固定长度并且没有后缀的歌曲名称
    private String artist;  //歌手名称
    private long duration;   //歌曲时长
    private long size;//歌曲大小
    private String path;//歌曲路径
    private String lrcTitle;//歌词名称
    private String lrcSize;//歌词大小
    private boolean isPlaying;

    public MusicInfoBean() {
        super();
    }

    public MusicInfoBean(long songId, String title, String album, long albumId, String displayName,
                         String musicNameNoTail, String artist, long duration, long size, String path,
                         String lrcTitle, String lrcSize) {
        this.songId = songId;
        this.title = title;
        this.album = album;
        this.albumId = albumId;
        this.displayName = displayName;
        this.musicNameNoTail = musicNameNoTail;
        this.artist = artist;
        this.duration = duration;
        this.size = size;
        this.path = path;
        this.lrcTitle = lrcTitle;
        this.lrcSize = lrcSize;
    }

    /*
    “.”和“|”都是转义字符，必须得加"\\";
    　　如果用“.”作为分隔的话，必须是如下写法：
            String.split("\\."),这样才能正确的分隔开，不能用String.split(".");
    如果用“|”作为分隔的话，必须是如下写法：
            String.split("\\|"),这样才能正确的分隔开，不能用String.split("|")
   */
    public void setMusicNameNoTail(String originalName) {
        musicNameNoTail = originalName.split("\\.")[0];
        if (musicNameNoTail.length() >= 15) {
            musicNameNoTail = musicNameNoTail.substring(0, 15);
            musicNameNoTail += "...";
        }
    }

    public long getSongId() {
        return songId;
    }

    public void setSongId(long songId) {
        this.songId = songId;
    }

    public String getTitle() {
        return title;
    }

    public String getMusicNameNoTail() {
        return musicNameNoTail;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    private final String SUFFIX_MP3 = ".mp3";

    public String getDisplayName() {
        if (displayName.endsWith(SUFFIX_MP3)) {
            displayName = displayName.substring(0, displayName.length() - SUFFIX_MP3.length());
        }
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getArtist() {
        if (artist.contains(UNKNOWN)) {
            return UNKNOWN_ZH;
        }
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getLrcTitle() {
        return lrcTitle;
    }

    public void setLrcTitle(String lrcTitle) {
        this.lrcTitle = lrcTitle;
    }

    public String getLrcSize() {
        return lrcSize;
    }

    public void setLrcSize(String lrcSize) {
        this.lrcSize = lrcSize;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    @Override
    public String toString() {
        return "MusicInfoBean{" +
                "songId=" + songId +
                ", title='" + title + '\'' +
                ", album='" + album + '\'' +
                ", albumId=" + albumId +
                ", displayName='" + displayName + '\'' +
                ", musicNameNoTail='" + musicNameNoTail + '\'' +
                ", artist='" + artist + '\'' +
                ", duration=" + duration +
                ", size=" + size +
                ", path='" + path + '\'' +
                ", lrcTitle='" + lrcTitle + '\'' +
                ", lrcSize='" + lrcSize + '\'' +
                ", isPlaying=" + isPlaying +
                '}';
    }
}