package com.example.android.musicplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    static ActivityChanger activityChanger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityChanger = new ActivityChanger();

        if (ContentManager.getInstance().songs.size() == 0) {
            loadSongsLibrary();
        }

        createSongsView(true);
        createPlayingNowFooter();
        setRadioButtonsListeners();
    }

    void loadSongsLibrary() {
        ContentManager.getInstance().createLibrary();
    }

    void createSongsView(Boolean all) {
        SongAdapter songsAdapter;
        if (all) {
            songsAdapter = new SongAdapter(this, ContentManager.getInstance().songs);
        } else {
            songsAdapter = new SongAdapter(this, ContentManager.getInstance().getFavouritesSongs());
        }
        ListView listView = (ListView) findViewById( R.id.songs_list_view);
        listView.setAdapter(songsAdapter);
    }

    void createPlayingNowFooter() {
        View footer = getLayoutInflater().inflate(R.layout.playing_now_footer, null);
        TextView songNameTextView = (TextView) footer.findViewById(R.id.song_play_now);
        TextView durationTextView = (TextView) footer.findViewById(R.id.duration);
        LinearLayout mainView = (LinearLayout) findViewById(R.id.main_activity_root);
        ContentManager.getInstance().setFooterView(songNameTextView, durationTextView);
        ContentManager.getInstance().updateCurrentlyPlayingLayout();
        Button playingNowButton = (Button) footer.findViewById(R.id.button_play_now);
        playingNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityChanger.changeActivity();
            }
        });
        mainView.addView(footer);
    }

    void setRadioButtonsListeners() {
        RadioGroup rGroup = findViewById(R.id.radio_button_group);
        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.all_radio_button:
                        createSongsView(true);
                        break;

                    case R.id.fav_radio_button:
                        createSongsView(false);
                        break;
                }
            }
        });
    }

    class ActivityChanger {
        void changeActivity(Artist artist) {
            ContentManager.getInstance().setChosenArtist(artist);
            Intent changeActivityIntent = new Intent(MainActivity.this, ArtistActivity.class);
            startActivity(changeActivityIntent);
        }

        void changeActivity() {
            Intent changeActivityIntent = new Intent(MainActivity.this, PlayingNowActivity.class);
            startActivity(changeActivityIntent);
        }
    }

}
