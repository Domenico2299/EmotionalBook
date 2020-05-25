package com.example.emotionalbook;

import android.provider.BaseColumns;

class EmotionalDatabaseContract {
    static final String DATABASE_NAME = "emotional_database";
    static final int DATABASE_VERSION = 1;

    private EmotionalDatabaseContract() {}
    /* Inner class that defines the table contents */
    static abstract class EmotionRows implements BaseColumns {
        static final String TABLE_NAME = "emotional_state";
        static final String COLUMN_NAME_STATE = "state";
        static final String COLUMN_NAME_DATE = "date";
        static final String COLUMN_INTENSITY = "intensity";
    }
}
