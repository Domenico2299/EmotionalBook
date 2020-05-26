package com.example.emotionalbook;

import android.content.ContentValues;
import android.icu.util.LocaleData;
import android.util.Log;

import java.time.LocalDate;

public class EmotionalRow {
    private long _id;
    protected String state; // lo stato
    protected long intensity; //intensità dello stato
    protected LocalDate createOn;
    protected String date; // la data di creazione dello stato

    public EmotionalRow(String state, long intensity, LocalDate date){
        this.state=state;
        this.intensity=intensity;
        this.createOn=date;
    }
    public EmotionalRow(String state, String intensity, String date){
        this.state=state;
        this.intensity=Long.parseLong(intensity);
        this.date=date;
    }

    public ContentValues getAsContentValue() {
        ContentValues cv = new ContentValues();
        cv.put(EmotionalDatabaseContract.EmotionRows.COLUMN_NAME_STATE, this.state);
        cv.put(EmotionalDatabaseContract.EmotionRows.COLUMN_INTENSITY, this.intensity);
        cv.put(EmotionalDatabaseContract.EmotionRows.COLUMN_NAME_DATE, this.createOn.getDayOfWeek()+"/"+this.createOn.getDayOfMonth()+"/"+createOn.getMonth()+"/"+createOn.getYear());
        Log.i("RIGA INSERITA NEL DATABASE", this.state+" ,"+this.intensity+" ,"+this.createOn.getDayOfWeek()+"/"+this.createOn.getDayOfMonth()+"/"+this.createOn.getMonth()+"/"+this.createOn.getYear());
        return cv;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String toStringOne(){
        return state+" ,"+intensity+" ,"+this.createOn.getDayOfWeek()+"/"+this.createOn.getDayOfMonth()+"/"+this.createOn.getMonth()+"/"+this.createOn.getYear();
    }
    public String toStringTwo(){
        return state+" ,"+intensity+" ,"+date;
    }
}
