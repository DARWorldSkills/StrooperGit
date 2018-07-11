package com.software.ragp.stroopergit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Menu extends AppCompatActivity implements OnClickListener{
    Button btnJugar, btnPuntaje, btnConfiguracion;
    public static int modo=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        modo=0;
        setContentView(R.layout.activity_menu);
        inizialite();
    }

    private void inizialite() {
        btnJugar = findViewById(R.id.btnJugar);
        btnPuntaje = findViewById(R.id.btnPuntaje);
        btnConfiguracion = findViewById(R.id.btnConfiguracion);

        btnJugar.setOnClickListener(this);
        btnPuntaje.setOnClickListener(this);
        btnConfiguracion.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btnJugar:
                intent = new Intent(Menu.this, Juego.class);
                modo=1;
                startActivity(intent);
                break;

            case R.id.btnPuntaje:
                modo=0;
                break;

            case R.id.btnConfiguracion:
                modo=0;
                break;
        }
    }
}
