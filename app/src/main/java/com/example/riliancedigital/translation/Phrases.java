package com.example.riliancedigital.translation;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by RILIANCE DIGITAL on 03-03-2018.
 */

public class Phrases extends AppCompatActivity {
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
        setContentView(R.layout.activity_phrases);

      final ArrayList<Word> phr = new ArrayList<Word>();
        phr.add(new Word("Nanna","Daddy",R.raw.phrase_are_you_coming));
        phr.add(new Word("Amma","Mommy",R.raw.phrase_im_feeling_good));
        phr.add(new Word("Tammudu","Younger Brother",R.raw.phrase_what_is_your_name));
        phr.add(new Word("Annaiah","Elder Brother",R.raw.phrase_im_coming));
        phr.add(new Word("Chelli","Younger Sister",R.raw.phrase_how_are_you_feeling));
        phr.add(new Word("Akka","Elder Sister",R.raw.phrase_yes_im_coming));

        WordAdapter adt = new WordAdapter(this,phr,"#0fcce1");
        ListView listView = (ListView)findViewById(R.id.phr_activity);
        listView.setAdapter(adt);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word word = phr.get(i);
                releaseMediaPlayer();
                 mMediaPlayer = MediaPlayer.create(Phrases.this,word.getSound());
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(mCompletion);
            }
        });
    }

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
