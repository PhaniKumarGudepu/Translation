package com.example.riliancedigital.translation;

/**
 * Created by RILIANCE DIGITAL on 03-03-2018.
 */

public class Word {
    //setters for miwak and english
    private String miwak,english;
    private int img = DATA,soundResourceId;
    private static final  int DATA = -1;
    public Word(String mik,String eng,int image,int mSoundResourceId){
        miwak = mik;
        english = eng;
        img = image;
        soundResourceId = mSoundResourceId;
    }
    public  Word(String mik,String eng,int mSoundResourceId){
        miwak = mik;
        english = eng;
        soundResourceId = mSoundResourceId;
    }
    //getters for miwak and english words
    public String getMiwak(){
        return miwak;
    }
    public String getEnglish(){
        return english;
    }
    public int getImg(){return img;}
public int getSound(){
        return soundResourceId;
}
    public boolean isImage(){
        return img != DATA;
    }
}
