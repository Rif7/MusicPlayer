package com.example.android.musicplayer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

public class PlayingNowActivity extends Activity {
    TextView playedTime;
    ProgressBar progressBar;
    Thread updater;
    ContentManager.CurrentlyPlaying currentlyPlaying;
    Song playingSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing_now);

        Button backButton = (Button) findViewById(R.id.play_now_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if (ContentManager.getInstance().isPlayingSongNow()) setLayout();
    }

    void setLayout() {
        currentlyPlaying = ContentManager.getInstance().getPlayingSongNow();
        playingSong = currentlyPlaying.currentSong;

        TextView songName = (TextView) findViewById(R.id.play_now_song_name);
        songName.setText(playingSong.getName());

        TextView artistName = (TextView) findViewById(R.id.play_now_artist_name);
        artistName.setText(playingSong.getArtist().getName());

        TextView duration = (TextView) findViewById(R.id.play_now_duration);
        duration.setText(playingSong.getDuration().getTime());

        playedTime = (TextView) findViewById(R.id.play_now_played_time);
        playedTime.setText(currentlyPlaying.getPlayedTime().getTime());

        progressBar = (ProgressBar) findViewById(R.id.play_now_progress_bar);
        progressBar.setMax(playingSong.getDuration().getSeconds());
        progressBar.setProgress(currentlyPlaying.playedTime.getSeconds());

        updater = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        public void run() {
                            progressBar.setProgress(currentlyPlaying.playedTime.getSeconds());
                            playedTime.setText(currentlyPlaying.getPlayedTime().getTime());
                        }
                    });
                }
            }
        });
        updater.start();

        ImageButton pauseButton = (ImageButton) findViewById(R.id.play_now_pause_button);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentlyPlaying.isPlaying()) {
                    currentlyPlaying.stop();
                }
            }
        });

        ImageButton playButton = (ImageButton) findViewById(R.id.play_now_play_button);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!currentlyPlaying.isPlaying()) {
                    currentlyPlaying.play();
                }
            }
        });

        ImageButton rewindButton = (ImageButton) findViewById(R.id.play_now_rewind_button);
        rewindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentlyPlaying.rewind();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (updater != null) {
            updater.interrupt();
        }
    }
}
