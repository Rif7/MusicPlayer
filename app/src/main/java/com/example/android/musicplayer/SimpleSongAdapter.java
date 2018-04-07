package com.example.android.musicplayer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SimpleSongAdapter extends ArrayAdapter<Song>{
        ArrayList<Song> songs;

        public SimpleSongAdapter(Context context, ArrayList<Song> songs) {
            super(context, 0, songs);
            this.songs = songs;
        }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.simple_song_item, parent, false);
        }

        Song singleSong = getItem(position);
        TextView songNameTextView = (TextView) listItemView.findViewById(R.id.simple_song_name);
        songNameTextView.setText(singleSong.getName());

        TextView artistNameTextView = (TextView) listItemView.findViewById(R.id.song_duration);
        artistNameTextView.setText(singleSong.getDuration().getTime());

        return listItemView;
    }
}
