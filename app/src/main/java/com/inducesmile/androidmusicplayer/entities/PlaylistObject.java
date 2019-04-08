package com.inducesmile.androidmusicplayer.entities;

public class PlaylistObject {

    private String songCover;
    private String songTitle;
    private String time;

    public PlaylistObject(String songTitle, String songAuthor, String time) {
        this.songCover = songAuthor;
        this.time = time;
        this.songTitle = songTitle;
    }

    public String getime() {
        return time;
    }

    public String getname() {
        return songCover;
    }

    public String getorderid() {
        return songTitle;
    }
}
