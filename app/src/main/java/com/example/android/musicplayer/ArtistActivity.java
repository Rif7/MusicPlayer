package com.example.android.musicplayer;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ArtistActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);
        Artist chosenArtist = ContentManager.getInstance().chosenArtist;

        Button backButton = (Button) findViewById(R.id.artist_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        TextView artistName = (TextView) findViewById(R.id.artist_header);
        artistName.setText(chosenArtist.getName());

        TextView artistDescription = (TextView) findViewById(R.id.artist_description);
        artistDescription.setText(chosenArtist.getDesctiption());

        ImageView artistPhoto = (ImageView) findViewById(R.id.artist_photo);
        artistPhoto.setImageResource(chosenArtist.getImageID());

        SimpleSongAdapter songsAdapter = new SimpleSongAdapter(this, ContentManager.getInstance().getArtistSongs(chosenArtist));
        ListView listView = (ListView) findViewById( R.id.artist_songs_list_view);
        listView.setAdapter(songsAdapter);
    }
}
