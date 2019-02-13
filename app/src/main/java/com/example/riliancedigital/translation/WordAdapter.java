package com.example.riliancedigital.translation;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by RILIANCE DIGITAL on 03-03-2018.
 */

public class WordAdapter extends ArrayAdapter<Word> {
    String color;
    public WordAdapter(Activity context, List<Word> words,String back_color)
    {
        super(context,0,words);
        color = back_color;
    }

    @Nullable

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View done_view = convertView;
        Word item = getItem(position);
        if(done_view == null)
        {
            done_view = LayoutInflater.from(getContext()).inflate(R.layout.list_view, parent, false);
        }
        //setting texts
        LinearLayout out = (LinearLayout) done_view.findViewById(R.id.text_view);
        out.setBackgroundColor(Color.parseColor(color));

        ImageView img = (ImageView) done_view.findViewById(R.id.img_view);
        if (item.isImage()) {
            img.setImageResource(item.getImg());
             img.setVisibility(View.VISIBLE);
        }
        else
            img.setVisibility(View.GONE);
        TextView miwakView = (TextView) done_view.findViewById(R.id.miwakWord);
        miwakView.setText(item.getMiwak());
        TextView engView = (TextView) done_view.findViewById(R.id.engWord);
        engView.setText(item.getEnglish());

        return done_view;
    }
}
