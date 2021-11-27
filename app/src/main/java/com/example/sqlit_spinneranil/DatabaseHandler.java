package com.example.sqlit_spinneranil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
private  static final int DATABASE_VERSION =1;
private  static final String DATABASE_NAME = "spinner";
private static final String TABLE_NAME = "anilTable";
private  static  final  String COLUMN_NAME = "name";
private  static final String COLUMN_ID = "column_id";



    public DatabaseHandler(Context context) {
        super (context,DATABASE_NAME,null,DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ITEM_TABLE = "CREATE TABLE "+TABLE_NAME+"("+COLUMN_ID+"INTEGER PRIMARY KEY,"+COLUMN_NAME+ "TEXT)";
        db.execSQL ( CREATE_ITEM_TABLE );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL ( "DROP TABLE IF EXISTS "+TABLE_NAME);

    onCreate ( db );

    }
    public void insertAnil(String s){
        SQLiteDatabase db = this.getWritableDatabase ();
        ContentValues values = new ContentValues ();

        values.put ( COLUMN_NAME,s );


        db.close ();


    }
    public List<String>getAlls(){
        List<String>list = new ArrayList<String> ();
        String kumar = "SELECT *FROM "+TABLE_NAME;
        SQLiteDatabase db =this.getReadableDatabase ();
        Cursor cursor = db.rawQuery (kumar ,null );

        if (cursor.moveToFirst ()){
            do {
                list.add ( cursor.getString ( 1 ) );
            }while (cursor.moveToNext ());

            }
        cursor.close ();
        return list;


    }
}
