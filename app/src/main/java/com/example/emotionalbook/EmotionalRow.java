package com.example.emotionalbook;

import android.content.ContentValues;
import android.icu.util.LocaleData;
import android.util.Log;

import java.time.LocalDate;

public class EmotionalRow {
    private long _id;
    private String state; // lo stato
    private long intensity; //intensità dello stato
    private LocalDate createOn; // la data di creazione dello stato

    public EmotionalRow(String stateRadioButton, long intensitySeekBar, LocalDate date){
        this.state=stateRadioButton;
        this.intensity=intensitySeekBar;
        this.createOn=date;
    }

    public ContentValues getAsContentValue() {
        ContentValues cv = new ContentValues();
        cv.put(EmotionalDatabaseContract.EmotionRows.COLUMN_NAME_STATE, this.state);
        cv.put(EmotionalDatabaseContract.EmotionRows.COLUMN_INTENSITY, this.intensity);
        cv.put(EmotionalDatabaseContract.EmotionRows.COLUMN_NAME_DATE, this.createOn.getDayOfWeek()+"/"+this.createOn.getMonth()+"/"+createOn.getMonth()+"/"+createOn.getYear());
        Log.i("RIGA INSERITA NEL DATABASE", this.state+" ,"+this.intensity+" ,"+this.createOn.getDayOfWeek()+"/"+this.createOn.getDayOfMonth()+"/"+this.createOn.getMonth()+"/"+this.createOn.getYear());
        return cv;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String toString(){
        return state+" ,"+intensity;
    }
}
