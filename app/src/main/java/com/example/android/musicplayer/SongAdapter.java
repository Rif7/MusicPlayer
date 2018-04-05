package com.example.android.musicplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class SongAdapter extends ArrayAdapter<Song> {
    ArrayList<Song> songs;
    HashMap<ImageButton, Song> refSong;

    public SongAdapter(Context context, ArrayList<Song> songs) {
        super(context, 0, songs);
        this.songs = songs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.song_item, parent, false);
        }

        Song singleSong = getItem(position);

        TextView songNameTextView = (TextView) listItemView.findViewById(R.id.song_name);
        songNameTextView.setText(singleSong.getName());
        TextView artistNameTextView = (TextView) listItemView.findViewById(R.id.artist_name);
        artistNameTextView.setText(singleSong.getArtist().getName());

        ImageButton addToFavouritesButton = (ImageButton) listItemView.findViewById(R.id.add_to_favourites_button);
        addToFavouritesButton.setOnClickListener(new AddToFavouritesListener(addToFavouritesButton, position));

        ImageButton playButton = (ImageButton) listItemView.findViewById(R.id.play_button);
        playButton.setOnClickListener(new PlayListener(position));

        return listItemView;
    }

    class AddToFavouritesListener implements View.OnClickListener {
        ImageButton imageButtonRef;
        int position;

        AddToFavouritesListener(ImageButton imageButtonRef, int position) {
            this.imageButtonRef = imageButtonRef;
            this.position = position;
        }

        @Override
        public void onClick(View view) {
            if (songs.get(position).getFavourite()) {
                songs.get(position).setFavourite(false);
                imageButtonRef.setImageResource(R.drawable.fav_n);
            } else {
                songs.get(position).setFavourite(true);
                imageButtonRef.setImageResource(R.drawable.fav_y);
            }

        }
    }

    class PlayListener implements View.OnClickListener {
        int position;

        PlayListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View view) {
            ContentManager.getInstance().setPlayingSongNow(songs.get(position));

        }
    }

}
