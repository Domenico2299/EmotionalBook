package com.example.emotionalbook;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

class EmotionalDatabaseAdapter {
    private static String TAG = "DBAdapter";
    private static EmotionalDatabaseAdapter dbInstance;

    private SQLiteDatabase db; // reference to the DB
    private EmotionalDatabaseHelper dbHelper; // reference to the OpenHelper

    public static synchronized EmotionalDatabaseAdapter getInstance(Context context) {
        if (dbInstance == null)
            dbInstance = new EmotionalDatabaseAdapter(context.getApplicationContext());
        return dbInstance;
    }

    private EmotionalDatabaseAdapter(Context context) {
        this.dbHelper = EmotionalDatabaseHelper.getInstance(context);
    }

    public EmotionalDatabaseAdapter open() throws SQLException {
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLiteException e) {
            Log.e(TAG, e.getMessage());
            throw e;
        }
        return this;
    }

    public void close() {
        db.close();
    }

    public void deleteAll(){
        db.delete(EmotionalDatabaseContract.EmotionRows.TABLE_NAME,null,null);
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + EmotionalDatabaseContract.EmotionRows.TABLE_NAME + "'");

    }

    public long insert(EmotionalRow row) {
        Log.v(TAG, "Adding todoItem to the database.");
        long idx = db.insert(EmotionalDatabaseContract.EmotionRows.TABLE_NAME, null, row.getAsContentValue());
        Log.v(TAG, "Added value idx: " + idx);
        return idx;
    }

    public Cursor getAllEntries() {
        return db.query(EmotionalDatabaseContract.EmotionRows.TABLE_NAME, null, null, null, null, null, null);
    }

}
