package com.example.android.musicplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContentManager.getInstance().createLibrary();

        SongAdapter songsAdapter = new SongAdapter(this, ContentManager.getInstance().songs);

        ListView listView = (ListView) findViewById(R.id.songs_list_view);
        
        listView.setAdapter(songsAdapter);

        View footer = getLayoutInflater().inflate(R.layout.playing_now_footer, null);
        TextView songNameTextView = (TextView) footer.findViewById(R.id.song_play_now);
        TextView durationTextView = (TextView) footer.findViewById(R.id.duration);

        LinearLayout mainView = (LinearLayout) findViewById(R.id.main_activity_root);
        ContentManager.getInstance().setFooterView(songNameTextView, durationTextView);
        mainView.addView(footer);
    }

}
