package ru.pavlenty.surfacegame2;

/**
 * Created by Сергей on 18.03.2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "Scorces";
    public static final String TABLE_NAME = "Score";
    public static final String COLUMN_ID = "id";

    public static final String COLUMN_ADD = "score";


    private static final int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME
                + "(" + COLUMN_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_ADD +
                " VARCHAR);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Score;";
        db.execSQL(sql);
        onCreate(db);
    }

    public boolean addScore( int[] add) {
        SQLiteDatabase db;
        db=this.getWritableDatabase();
        db.delete(TABLE_NAME,"1=1",null);


        for(int t=0;t <add.length;t++){
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_ADD, String.valueOf(add[t]));

            db.insert(TABLE_NAME, null, contentValues);}
        db.close();
        return true;
    }

    public Cursor getScore() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM Score;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }
}
