package com.example.emotionalbook;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.service.autofill.DateTransformation;
import android.util.Log;

class DatabaseManager {

    static void setStatistics(Cursor year, Cursor month, Cursor week){
        EmotionalRow[] yearRows=DatabaseManager.getYear(year);
        EmotionalRow[] monthRows=DatabaseManager.getRealMonth(month);
        EmotionalRow[] weekRows= DatabaseManager.getRealWeek(week);

        double[] yearPercentages=DatabaseManager.percentages(yearRows);
        double[] monthPercentages=DatabaseManager.percentages(monthRows);
        double[] weekPercentages=DatabaseManager.percentages(weekRows);

        Log.i("FUNZIONANTE!","dati ottenuti per l'anno: felicità "+String.valueOf(yearPercentages[0])+" ,tristezza "+String.valueOf(yearPercentages[1])+
                " ,stress "+String.valueOf(yearPercentages[2])+" ,rabbia "+String.valueOf(yearPercentages[3]));
        Log.i("FUNZIONANTE!","dati ottenuti per il mese: felicità "+String.valueOf(monthPercentages[0])+" ,tristezza "+String.valueOf(monthPercentages[1])+
                " ,stress "+String.valueOf(monthPercentages[2])+" ,rabbia "+String.valueOf(monthPercentages[3]));
        Log.i("FUNZIONANTE!","dati ottenuti per la settimana: felicità "+String.valueOf(weekPercentages[0])+" ,tristezza "+String.valueOf(weekPercentages[1])+
                " ,stress "+String.valueOf(weekPercentages[2])+" ,rabbia "+String.valueOf(weekPercentages[3]));
    }

    private static EmotionalRow[] getRealWeek(Cursor cursor){
        EmotionalRow[] realWeek=new EmotionalRow[cursor.getCount()];
        int i=0;
        String[] tokenizer;
        cursor.moveToFirst();
        do{
            realWeek[i]=new EmotionalRow(cursor.getString(0),cursor.getString(1),cursor.getString(2));
            Log.i("COSA HA DATO CURSOR:", cursor.getString(0)+cursor.getString(1)+cursor.getString(2));
            i++;
            tokenizer=cursor.getString(2).split("/");
           if(tokenizer[0]=="MONDAY"){
               break;
           }
        }while (cursor.moveToNext());
        return realWeek;
    }

    private static EmotionalRow[] getRealMonth(Cursor cursor){
        EmotionalRow[] realMonth=new EmotionalRow[cursor.getCount()];
        int i=0;
        String[] tokenizer;
        cursor.moveToFirst();
        do{
            realMonth[i]=new EmotionalRow(cursor.getString(0),cursor.getString(1),cursor.getString(2));
            Log.i("COSA HA DATO CURSOR:", cursor.getString(0)+cursor.getString(1)+cursor.getString(2));
            i++;
            tokenizer=cursor.getString(2).split("/");
            if(tokenizer[1]=="1"){
                break;
            }
        }while (cursor.moveToNext());
        return realMonth;
    }
    private static EmotionalRow[] getYear(Cursor cursor){
        EmotionalRow[] year=new EmotionalRow[cursor.getCount()];
        int i=0;
        String[] tokenizer;
        cursor.moveToFirst();
        do{
            year[i]=new EmotionalRow(cursor.getString(0),cursor.getString(1),cursor.getString(2));
            Log.i("COSA HA DATO CURSOR:", cursor.getString(0)+cursor.getString(1)+cursor.getString(2));
            i++;
        }while (cursor.moveToNext());
        return year;
    }

    private static double[] percentages(EmotionalRow[] rows){
        double happy=0;
        double sad=0;
        double angry=0;
        double stressed=0;
        for(EmotionalRow row:rows){
            switch (row.state){
                case "felice":
                    happy+=row.intensity;
                    break;
                case "arrabbiato":
                    angry+=row.intensity;
                    break;
                case "triste":
                    sad+=row.intensity;
                    break;
                case "stressato":
                    stressed+=row.intensity;
                    break;
            }
        }
        double total=happy+sad+angry+stressed;
        double[] percentages={Math.round(((happy*100)/total)*100.0)/100.0,Math.round(((sad*100)/total)*100.0)/100.0,
                Math.round(((stressed*100)/total)*100.0)/100.0,Math.round(((angry*100)/total)*100.0)/100.0};
        return percentages;
    }

}
