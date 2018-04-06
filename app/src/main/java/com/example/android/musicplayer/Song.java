package com.example.android.musicplayer;

public class Song {
    private String name;
    private Artist artist;
    private Duration duration;
    private Boolean isFavourite;

    Song(String name, Artist artist, Duration duration) {
        this.name = name;
        this.artist = artist;
        this.duration = duration;
        this.isFavourite = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Boolean getFavourite() {
        return isFavourite;
    }

    public void setFavourite(Boolean favourite) {
        isFavourite = favourite;
    }


}
