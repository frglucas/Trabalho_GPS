package com.edu.uniritter.gpsapplication.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "BancoDeDados";
    private static final Integer DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String stm = "CREATE TABLE usuario(id INTEGER PRIMARY KEY);";
        sqLiteDatabase.execSQL(stm);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int currentVersion) {
        String stm = "ALTER TABLE usuario ADD nome text;";
        if(oldVersion == 0){
            sqLiteDatabase.execSQL(stm);
        }
    }
}
