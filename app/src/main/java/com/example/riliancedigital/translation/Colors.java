package com.example.riliancedigital.translation;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by RILIANCE DIGITAL on 03-03-2018.
 */

public class Colors extends AppCompatActivity {
MediaPlayer mMediaPlayer;
private MediaPlayer.OnCompletionListener mCompletionListener =  new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
       releaseMediaPlayer();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);
        final ArrayList<Word> col = new ArrayList<Word>();
        col.add(new Word("Telupu", "White", R.drawable.color_white, R.raw.color_white));
        col.add(new Word("Nalupu", "Black", R.drawable.color_black, R.raw.color_black));
        col.add(new Word("Erupu", "Red", R.drawable.color_red, R.raw.color_red));
        col.add(new Word("Nilam", "Blue", R.drawable.color_gray, R.raw.color_gray));
        col.add(new Word("Pasupu", "Yellow", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));
        col.add(new Word("Pachha", "Green", R.drawable.color_green, R.raw.color_green));
        col.add(new Word("Gulabi", "Pink", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));

        WordAdapter adt = new WordAdapter(this, col, "#fdfa16");
        ListView listView = (ListView) findViewById(R.id.col_activity);
        listView.setAdapter(adt);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word word = col.get(i);
                releaseMediaPlayer();
                mMediaPlayer = MediaPlayer.create(Colors.this, word.getSound());
                mMediaPlayer.start();
mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    /**
         * Clean up the media player by releasing its resources.
         */
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

