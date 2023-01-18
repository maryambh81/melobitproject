package com.example.melobitapplication.model;

public class SearchModel
{
    String type;
    SongModelList song;
    Artists artist;
    int position;

    public void setSong(SongModelList song) {
        this.song = song;
    }

    public SongModelList getSong() {
        return song;
    }

    public Artists getArtist() {
        return artist;
    }

    public void setArtist(Artists artist) {
        this.artist = artist;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
