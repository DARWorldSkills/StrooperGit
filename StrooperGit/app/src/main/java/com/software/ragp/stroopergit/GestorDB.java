package com.software.ragp.stroopergit;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class GestorDB extends SQLiteOpenHelper{

    public GestorDB(Context context) {
        super(context, "datos.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE DATOS (PUNTAJE TEXT, INCORRECTAS TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List<Score> listarResultados(){
        List<Score> results= new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM DATOS ORDER BY PUNTAJE ASC, INCORRECTAS ASC",null);
        Score score;
        if (cursor.moveToFirst()){
            do {
                score = new Score();
                score.setPuntaje(cursor.getString(0));
                score.setIncorrectas(cursor.getString(1));
                results.add(score);

            }while (cursor.moveToNext());
        }


        return  results;
    }

}
