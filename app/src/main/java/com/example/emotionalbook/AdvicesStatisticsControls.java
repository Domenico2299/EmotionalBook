package com.example.emotionalbook;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.service.autofill.DateTransformation;
import android.util.Log;

class DatabaseManager {

    protected static Double[] valYear;
    protected static Double[] valMonth;
    protected static Double[] valWeek;

    static void setStatistics(Cursor year, Cursor month, Cursor week){//METODO CHIAMATO DAL MAIN PER RECUPERARE LE STATISTICHE
        EmotionalRow[] yearRows=DatabaseManager.getRow(year);
        EmotionalRow[] monthRows=DatabaseManager.getRow(month);
        EmotionalRow[] weekRows= DatabaseManager.getRow(week);

        valYear=DatabaseManager.percentages(yearRows);
        valMonth=DatabaseManager.percentages(monthRows);
        valWeek=DatabaseManager.percentages(weekRows);

        Log.i("FUNZIONANTE!","dati ottenuti per l'anno: felicità "+String.valueOf(valYear[0])+" ,tristezza "+String.valueOf(valYear[1])+
                " ,stress "+String.valueOf(valYear[2])+" ,rabbia "+String.valueOf(valYear[3]));
        Log.i("FUNZIONANTE!","dati ottenuti per il mese: felicità "+String.valueOf(valMonth[0])+" ,tristezza "+String.valueOf(valMonth[1])+
                " ,stress "+String.valueOf(valMonth[2])+" ,rabbia "+String.valueOf(valMonth[3]));
        Log.i("FUNZIONANTE!","dati ottenuti per la settimana: felicità "+String.valueOf(valWeek[0])+" ,tristezza "+String.valueOf(valWeek[1])+
                " ,stress "+String.valueOf(valWeek[2])+" ,rabbia "+String.valueOf(valWeek[3]));
    }

    private static EmotionalRow[] getRow(Cursor cursor){//METODO PER RIDEFINIRE LE ROW DAL DB
        EmotionalRow[] row=new EmotionalRow[cursor.getCount()];
        int i=0;
        String[] tokenizer;
        cursor.moveToFirst();
        do{
            row[i]=new EmotionalRow(cursor.getString(0),cursor.getString(1),cursor.getString(2));
            Log.i("COSA HA DATO CURSOR:", cursor.getString(0)+cursor.getString(1)+cursor.getString(2));
            i++;
        }while (cursor.moveToNext());
        return row;
    }

    private static Double[] percentages(EmotionalRow[] rows){//CALCOLO PERCENTUALE STATISTICHE, UTILE ANCHE PER I CONSIGLI
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
        Double total=happy+sad+angry+stressed;
        Double[] percentages={Math.round(((happy*100)/total)*100.0)/100.0,Math.round(((sad*100)/total)*100.0)/100.0,
                Math.round(((stressed*100)/total)*100.0)/100.0,Math.round(((angry*100)/total)*100.0)/100.0};
        return percentages;
    }

    public static int max(int a, int b, int c, int d) {//VALORE PIù ALTO, PER DEFINIRE IL CONSIGLIO DEL GIORNO

        int max = a;

        if (b > max)
            max = b;
        if (c > max)
            max = c;
        if (d > max)
            max = d;

        return max;
    }

}
