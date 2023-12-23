package com.example.variant2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "UserCredentials.db";
    private static final String TABLE_NAME = "credentials_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "LOGIN";
    private static final String COL3 = "PASSWORD";
    private Cursor data;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 + " TEXT, " + COL3 + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String login, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, login);
        contentValues.put(COL3, password);

        long result = db.insert(TABLE_NAME, null, contentValues);
        db.close(); // It's important to close the database after operation.

        // If data was inserted successfully, the insert method will return -1
        return result != -1;
    }
    public boolean checkUser(String login, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = "LOGIN = ? AND PASSWORD = ?";
        String[] selectionArgs = { login, password };
        Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count > 0; // Если найдено совпадение, возвращаем true, иначе false
    }


    public Cursor getData() {
        return data;
    }

    public void setData(Cursor data) {
        this.data = data;
    }
}