package com.example.android.musicplayer;

import android.support.annotation.Nullable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

class ContentManager {
    private static final ContentManager ourInstance = new ContentManager();
    CurrentlyPlaying currentlyPlaying;
    Artist chosenArtist;
    HashMap<String, Artist> artists;
    ArrayList<Song> songs;


    static ContentManager getInstance() {
        return ourInstance;
    }

    public void setFooterView(TextView songTextViewRef, TextView durationTextViewRef) {
        currentlyPlaying = new CurrentlyPlaying(songTextViewRef, durationTextViewRef);
    }

    public boolean isPlayingSongNow(){
        return currentlyPlaying.currentSong != null;
    }

    public void updateCurrentlyPlayingLayout() {
        if (isPlayingSongNow()) {
            currentlyPlaying.update();
        }
    }

    public void setPlayingSongNow(Song song){
        currentlyPlaying.changeSong(song);
    }

    private ContentManager() {
        artists = new HashMap<>();
        songs = new ArrayList<>();
    }

    public ArrayList<Song> getFavouritesSongs() {
        ArrayList<Song> favSongs = new ArrayList<>();
        for (Song song: songs) {
            if (song.getFavourite()) {
                favSongs.add(song);
            }
        }
        return favSongs;
    }

    public ArrayList<Song> getArtistSongs(Artist artist) {
        ArrayList<Song> artistsSongs = new ArrayList<>();
        for (Song song: songs) {
            if (song.getArtist() == artist) {
                artistsSongs.add(song);
            }
        }
        return artistsSongs;
    }

    void addSong(String songName, String artistName, int normalizedMinutes, int normalizedSeconds) {
        if (!artists.containsKey(artistName)) {
            artists.put(artistName, new Artist(artistName));
        }
        songs.add(new Song(songName, artists.get(artistName), new Duration(normalizedMinutes, normalizedSeconds)));
    }

    void setChosenArtist(Artist artist) {
        chosenArtist = artist;
    }

    @Nullable
    Artist getChosenArtist() {
        return chosenArtist;
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
            String duration = "0:00/" + currentSong.getDuration().getTime();
            durationTextViewRef.setText(duration);
        }
    }

    void createLibrary() {
        // In real app this function should list file system, find song with adiuo file extension, read metadata and accordingly add to app library
        Random generator = new Random();
        String[] bands = {"Mad Seasons", "Robots", "The Netflixers", "The Drinkers"};
        addSong("Hot Winter", bands[0],1 + generator.nextInt(6), generator.nextInt(60));
        addSong("Cloudy Spring", bands[0], 1 + generator.nextInt(6), generator.nextInt(60));
        addSong("Rainy Summer", bands[0], 1 + generator.nextInt(6), generator.nextInt(60));
        addSong("Sunny Autumn", bands[0], 1 + generator.nextInt(6), generator.nextInt(60));
        addSong("R2D2", bands[1],1 + generator.nextInt(6), generator.nextInt(60));
        addSong("C3PO", bands[1], 1 + generator.nextInt(6), generator.nextInt(60));
        addSong("Terminator", bands[1], 1 + generator.nextInt(6), generator.nextInt(60));
        addSong("T1000", bands[1], 1 + generator.nextInt(6), generator.nextInt(60));
        addSong("WALL-E", bands[1], 1 + generator.nextInt(6), generator.nextInt(60));
        addSong("Android", bands[1], 1 + generator.nextInt(6), generator.nextInt(60));
        addSong("Gerty", bands[1], 1 + generator.nextInt(6), generator.nextInt(60));
        addSong("Dark", bands[2], 1 + generator.nextInt(6), generator.nextInt(60));
        addSong("Thirteen Reasons", bands[2], 1 + generator.nextInt(6), generator.nextInt(60));
        addSong("The Punisher", bands[2], 1 + generator.nextInt(6), generator.nextInt(60));
        addSong("Black Mirror", bands[2], 1 + generator.nextInt(6), generator.nextInt(60));
        addSong("Chocolate Baileys", bands[3], 1 + generator.nextInt(6), generator.nextInt(60));
        artists.get(bands[0]).setImageID(R.drawable.artists1);
        artists.get(bands[1]).setImageID(R.drawable.artists2);
        artists.get(bands[2]).setImageID(R.drawable.artists3);
        artists.get(bands[3]).setImageID(R.drawable.artists4);
        artists.get(bands[0]).setDesctiption("Hard Rock");
        artists.get(bands[1]).setDesctiption("Metal");
        artists.get(bands[2]).setDesctiption("Disco Polo");
        artists.get(bands[3]).setDesctiption("Indie");
    }
}
