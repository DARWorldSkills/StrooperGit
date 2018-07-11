package com.software.ragp.stroopergit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Configuracion extends AppCompatActivity {
    RadioButton rbtnTiempo, rbtnIntentos;
    EditText txttiempo;
    int ab=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        ab=0;
        inizialite();
        mostrarPreferencias();
    }

    private void inizialite() {
        rbtnTiempo = findViewById(R.id.rbtnTiempo);
        rbtnIntentos = findViewById(R.id.rbtnIntentos);
        txttiempo = findViewById(R.id.txtTiempoC);

    }

    private void agregarValores(){
        SharedPreferences valores = getSharedPreferences("juegoC",MODE_PRIVATE);
        SharedPreferences.Editor editor = valores.edit();
        int tiempo=0;
        if (rbtnTiempo.isChecked()){
            editor.putInt("modo",1);
        }
        if (rbtnIntentos.isChecked()){
            editor.putInt("modo",2);
        }
        try {
            tiempo = Integer.parseInt(txttiempo.getText().toString());
        }catch (Exception e){
            Toast.makeText(this, "Por favor escriba el tiempo correctamente", Toast.LENGTH_SHORT).show();
        }

        if (tiempo>=1 && tiempo<=10){
            editor.putInt("tiempo",tiempo);
            editor.commit();
            Intent intent = new Intent(Configuracion.this, JuegoC.class);
            startActivity(intent);
        }

    }

    public void mostrarPreferencias(){
        if (ab==0) {
            SharedPreferences valores = getSharedPreferences("juegoC", MODE_PRIVATE);
            int modo = valores.getInt("modo", 1);
            int tiempo = valores.getInt("tiempo", 3);
            if (modo == 1) {
                rbtnTiempo.setChecked(true);
            }

            if (modo == 2) {
                rbtnIntentos.setChecked(true);
            }
            txttiempo.setText(Integer.toString(tiempo));
            ab=1;
        }
    }

    public void salir(View view) {
        agregarValores();
        finish();
    }
}
