package com.example.android.musicplayer;

import java.util.ArrayList;
import java.util.HashMap;

class ContentManager {
    private static final ContentManager ourInstance = new ContentManager();
    private static Song playingSongNow;
    HashMap<String, Artist> artists;
    ArrayList<Song> songs;


    static ContentManager getInstance() {
        return ourInstance;
    }

    private ContentManager() {
    }

    void addSong(String songName, String ArtistName) {

    }

    Artist getArtis(String artistName) {
        return null;
    }

}
