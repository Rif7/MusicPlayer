package com.example.android.musicplayer;

import android.support.annotation.Nullable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

class ContentManager {
    private static final ContentManager ourInstance = new ContentManager();
    CurrentlyPlaying currentlyPlaying;
    HashMap<String, Artist> artists;
    ArrayList<Song> songs;


    static ContentManager getInstance() {
        return ourInstance;
    }

    public void setFooterView(TextView songTextViewRef, TextView durationTextViewRef) {
        currentlyPlaying = new CurrentlyPlaying(songTextViewRef, durationTextViewRef);
    }

    public void setPlayingSongNow(Song song){
        currentlyPlaying.changeSong(song);
    }

    private ContentManager() {
        artists = new HashMap<>();
        songs = new ArrayList<>();
    }

    void addSong(String songName, String artistName, int normalizedMinutes, int normalizedSeconds) {
        if (!artists.containsKey(artistName)) {
            artists.put(artistName, new Artist(artistName));
        }
        songs.add(new Song(songName, artists.get(artistName), new Duration(normalizedMinutes, normalizedSeconds)));
    }

    @Nullable
    Artist getArtis(String artistName) {
        if (!artists.containsKey(artistName)) {
            return artists.get(artistName);
        }
        return null;
    }

    void createLibrary() {
        // In real app this function should list file system, find song with adiuo file extension, read metadata and accordingly add to app library
        Random generator = new Random();
        addSong("Hot Winter", "Mad Seasons", generator.nextInt(6), generator.nextInt(60));
        addSong("Cloudy Spring", "Mad Seasons", generator.nextInt(6), generator.nextInt(60));
        addSong("Rainy Summer", "Mad Seasons", generator.nextInt(6), generator.nextInt(60));
        addSong("Sunny Autumn", "Mad Seasons", generator.nextInt(6), generator.nextInt(60));
        addSong("R2D2", "Robots", generator.nextInt(6), generator.nextInt(60));
        addSong("C3PO", "Robots", generator.nextInt(6), generator.nextInt(60));
        addSong("Terminator", "Robots", generator.nextInt(6), generator.nextInt(60));
        addSong("T1000", "Robots", generator.nextInt(6), generator.nextInt(60));
        addSong("WALL-E", "Robots", generator.nextInt(6), generator.nextInt(60));
    }

    class CurrentlyPlaying {
        TextView songTextViewRef;
        TextView durationTextViewRef;
        Song currentSong;

        CurrentlyPlaying(TextView songTextViewRef, TextView durationTextViewRef) {
            this.songTextViewRef = songTextViewRef;
            this.durationTextViewRef = durationTextViewRef;
        }

        void changeSong(Song newSong) {
            currentSong = newSong;
            update();
        }

        void update() {
            songTextViewRef.setText(currentSong.getName());
            String duration = "0:00/" + currentSong.getDuration().getNormalizedMinutes() + ":" + currentSong.getDuration().getNormalizedSeconds();
            durationTextViewRef.setText(duration);
        }
    }

}
