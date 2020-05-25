package com.example.emotionalbook;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class EmotionalDatabaseHelper extends SQLiteOpenHelper {

    private static EmotionalDatabaseHelper dbInstance;
    private static final String LOG_TAG = "DBOpenHelper";
    private static final String SQL_CREATE_TABLE_TODOITEMS = "create table " //
            + EmotionalDatabaseContract.EmotionRows.TABLE_NAME + " (" //
            + EmotionalDatabaseContract.EmotionRows._ID + " integer primary key autoincrement, " //
            + EmotionalDatabaseContract.EmotionRows.COLUMN_NAME_STATE + " long not null, " //
            + EmotionalDatabaseContract.EmotionRows.COLUMN_INTENSITY + " text not null, " //
            + EmotionalDatabaseContract.EmotionRows.COLUMN_NAME_DATE + " text not null" //
            + ");";


    private EmotionalDatabaseHelper(Context context) {
        super(context, EmotionalDatabaseContract.DATABASE_NAME, null, EmotionalDatabaseContract.DATABASE_VERSION);
    }

    public static synchronized EmotionalDatabaseHelper getInstance(Context context) {
        if (dbInstance == null) {
            dbInstance = new EmotionalDatabaseHelper(context.getApplicationContext());
        }
        return dbInstance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v(LOG_TAG, "Creating database: " + EmotionalDatabaseContract.DATABASE_NAME);
        try {
            db.execSQL(SQL_CREATE_TABLE_TODOITEMS);
        } catch (SQLException e) {
            Log.e(LOG_TAG, e.getMessage());
            throw e;

        }
    }

    @Override
    public void onUpgrade (SQLiteDatabase db,int oldVersion, int newVersion){

    }
}
