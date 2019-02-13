package com.example.riliancedigital.translation;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView numbers_view = (TextView) findViewById(R.id.numbers);
        TextView colors_view = (TextView) findViewById(R.id.colors);
        TextView family_view = (TextView) findViewById(R.id.family);
        TextView phrases_view = (TextView) findViewById(R.id.phrases);
        //setting onclick listeners and intents
        numbers_view.setOnClickListener(new View.OnClickListener(){
            @Override
                    public void onClick(View view){
                Intent i = new Intent(MainActivity.this,Numbers.class);
                startActivity(i);
            }
        });
        colors_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Colors.class);
                startActivity(i);
            }
        });
        family_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Family.class);
                startActivity(i);
            }
        });
        phrases_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this, Phrases.class);
                startActivity(i);
            }
        });
    }

}
