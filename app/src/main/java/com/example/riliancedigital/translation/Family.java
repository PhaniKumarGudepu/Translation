package com.example.riliancedigital.translation;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.media.RatingCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by RILIANCE DIGITAL on 03-03-2018.
 */

public class Family extends AppCompatActivity {
MediaPlayer mMediaPlayer;
MediaPlayer.OnCompletionListener mCompletion = new MediaPlayer.OnCompletionListener() {
    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        releaseMediaPlayer();
    }
};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);
       final ArrayList<Word> fam = new ArrayList<Word>();
        fam.add(new Word("Nanna","Daddy",R.raw.family_father));
        fam.add(new Word("Amma","Mommy",R.raw.family_mother));
        fam.add(new Word("Tammudu","Younger Brother",R.raw.family_younger_brother));
        fam.add(new Word("Annaiah","Elder Brother",R.raw.family_older_brother));
        fam.add(new Word("Chelli","Younger Sister",R.raw.family_younger_sister));
        fam.add(new Word("Akka","Elder Sister",R.raw.family_older_sister));

        WordAdapter adt = new WordAdapter(this,fam,"#ed6707");
        ListView listView = (ListView)findViewById(R.id.fam_activity);
        listView.setAdapter(adt);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word word = fam.get(i);
                releaseMediaPlayer();
                mMediaPlayer = MediaPlayer.create(Family.this,word.getSound());
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(mCompletion);
            }
        });
    }
    //orverriding onStop method

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }

    }
}
