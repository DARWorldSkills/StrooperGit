package com.software.ragp.stroopergit;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Resumen extends AppCompatActivity {
    TextView txtcorrectas, txtincorrectas, txtporcentaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen);
        inizialite();
        inputData();
    }

    private void inputData() {
        if (Menu.modo==1) {
            txtcorrectas.setText(Integer.toString(Juego.correctas));
            txtincorrectas.setText(Integer.toString(Juego.incorrectas));
            txtporcentaje.setText(Integer.toString(Juego.aciertos));
            inputDataBase();
        }

        if (Menu.modo==0) {
            txtcorrectas.setText(Integer.toString(JuegoC.correctas));
            txtincorrectas.setText(Integer.toString(JuegoC.incorrectas));
            txtporcentaje.setText(Integer.toString(JuegoC.aciertos));
            inputDataBase();
        }
    }

    private void inputDataBase(){
        GestorDB gestorDB = new GestorDB(Resumen.this);
        SQLiteDatabase db = gestorDB.getWritableDatabase();
        ContentValues values =  new ContentValues();
        values.put("PUNTAJE",Juego.aciertos);
        values.put("INCORRECTAS",Juego.incorrectas);
        db.insert("DATOS", null,values);

    }

    private void inizialite() {
        txtcorrectas = findViewById(R.id.txtCorrectasR);
        txtincorrectas = findViewById(R.id.txtIncorrectasR);
        txtporcentaje = findViewById(R.id.txtAciertosR);
    }

}
