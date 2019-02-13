package com.example.riliancedigital.translation;

import android.content.Context;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by RILIANCE DIGITAL on 03-03-2018.
 */

public class Numbers extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
   private AudioManager mAudioManager;
    MediaPlayer.OnCompletionListener mCompletion = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };
    AudioManager.OnAudioFocusChangeListener mFocusRequest = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            }
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS){
                mMediaPlayer.stop();
                releaseMediaPlayer();
            }
            if (focusChange == AudioManager.AUDIOFOCUS_GAIN){
                mMediaPlayer.start();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
        mAudioManager =  (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<Word> num = new ArrayList<Word>();
        num.add(new Word("Okati", "One", R.drawable.number_one, R.raw.number_one));
        num.add(new Word("Rendu", "Two", R.drawable.number_two, R.raw.number_two));
        num.add(new Word("Moodu", "Three", R.drawable.number_three, R.raw.number_three));
        num.add(new Word("Nalugu", "Four", R.drawable.number_four, R.raw.number_four));
        num.add(new Word("Aidu", "Five", R.drawable.number_five, R.raw.number_five));
        num.add(new Word("Aaru", "Six", R.drawable.number_six, R.raw.number_six));
        num.add(new Word("Eadu", "Seven", R.drawable.number_seven, R.raw.number_seven));
        num.add(new Word("Enimidi", "Eight", R.drawable.number_eight, R.raw.number_eight));
        num.add(new Word("Tommidi", "Nine", R.drawable.number_nine, R.raw.number_nine));
        num.add(new Word("Padhi", "Ten", R.drawable.number_ten, R.raw.number_ten));
        WordAdapter adt = new WordAdapter(this, num, "#36e78b");
        ListView listView = (ListView) findViewById(R.id.num_activity);
        listView.setAdapter(adt);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word word = num.get(i);
                releaseMediaPlayer();


                int res = mAudioManager.requestAudioFocus(mFocusRequest,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                      if (res == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){

                          mMediaPlayer = MediaPlayer.create(Numbers.this, word.getSound());
                          mMediaPlayer.start();
                          //requesting the audio focus for handling the media player interruptions

                          mMediaPlayer.setOnCompletionListener(mCompletion);
                      }

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
            mAudioManager.abandonAudioFocus(mFocusRequest);
        }

    }
}
