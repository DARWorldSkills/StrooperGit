package com.software.ragp.stroopergit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Juego extends AppCompatActivity implements View.OnClickListener{
    TextView txtaciertos, txttiempo, txtintentos, txtcorrectas, txtincorrectas, txtpalabra;
    Button btnColor1, btnColor2, btnColor3, btnColor4;
    int [] segundos ={30, 0};
    List<String> listaPalabras= new ArrayList<>();
    List<Integer> listaColores=new ArrayList<>();
    boolean bandera = true;
    int ab = 0;
    int icR ,ipR, valorcito;
    public static int correctas, incorrectas, aciertos, intentos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        correctas=0;
        incorrectas=0;
        aciertos=0;
        intentos=0;
        valorcito=0;
        segundos =new int[]{30, 0};
        inizialite();
        listar();
        randomizar();
        insertarValores();
        txttiempo.setText("Tiempo: "+segundos[0]);
        bandera =true;
        ab=0;
        goGame();
    }

    private void goGame() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (bandera){
                    try{
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            insertarValores();
                            segundos [0]--;
                            segundos [1]++;
                            txttiempo.setText("Tiempo: "+segundos[0]);
                            if (segundos[1]>=3){
                                intentos++;
                                incorrectas++;
                                segundos[1]=0;
                                randomizar();
                                insertarValores();
                            }
                            endGame();


                        }
                    });

                }
            }
        });
        thread.start();
    }

    private void endGame() {
        if ((incorrectas==3 || segundos[0]==0) && ab==0 ){
            ab=1;
            bandera=false;
            Intent intent = new Intent(Juego.this, Resumen.class);
            startActivity(intent);
            finish();
        }
    }

    private void inizialite() {
        txtaciertos = findViewById(R.id.txtAciertosJ);
        txttiempo = findViewById(R.id.txtTiempoJ);
        txtintentos = findViewById(R.id.txtIntentosJ);
        txtcorrectas = findViewById(R.id.txtCorrectasJ);
        txtincorrectas = findViewById(R.id.txtIncorrectasJ);
        txtpalabra = findViewById(R.id.txtPalabraJ);

        btnColor1 = findViewById(R.id.btnColor1);
        btnColor2 = findViewById(R.id.btnColor2);
        btnColor3 = findViewById(R.id.btnColor3);
        btnColor4 = findViewById(R.id.btnColor4);

        btnColor1.setOnClickListener(this);
        btnColor2.setOnClickListener(this);
        btnColor3.setOnClickListener(this);
        btnColor4.setOnClickListener(this);


    }

    public void listar(){
        listaPalabras.add("AMARILLO");
        listaColores.add(getColor(R.color.colorAmarilloJ));
        listaPalabras.add("AZUL");
        listaColores.add(getColor(R.color.colorAzulJ));
        listaPalabras.add("ROJO");
        listaColores.add(getColor(R.color.colorRojoJ));
        listaPalabras.add("VERDE");
        listaColores.add(getColor(R.color.colorVerdeJ));
    }

    public void randomizar(){
        ipR = (int) (Math.random()*4);
        icR = (int) (Math.random()*4);
        txtpalabra.setText(listaPalabras.get(ipR));
        txtpalabra.setTextColor(listaColores.get(icR));

        List<Integer> tmplista = listaColores;
        Collections.shuffle(tmplista);

        btnColor1.setBackgroundColor(tmplista.get(0));
        btnColor2.setBackgroundColor(tmplista.get(1));
        btnColor3.setBackgroundColor(tmplista.get(2));
        btnColor4.setBackgroundColor(tmplista.get(3));

    }

    public void compobrar(){
        segundos[1]=0;
        if (valorcito==1){
            if (icR==0){
                correctas++;
            }else {
                incorrectas++;
            }
        }

        if (valorcito==2){
            if (icR==1){
                correctas++;
            }else {
                incorrectas++;
            }
        }

        if (valorcito==3){
            if (icR==2){
                correctas++;
            }else {
                incorrectas++;
            }
        }

        if (valorcito==4){
            if (icR==3){
                correctas++;
            }else {
                incorrectas++;
            }
        }

        intentos++;
        endGame();
        insertarValores();
        randomizar();
    }

    private void insertarValores() {

        if (intentos==0){
            aciertos=100;
        }else {
            if (correctas==0){
                aciertos=0;
            }else {
                double tmp = (correctas/ intentos) *100;
                aciertos = (int) tmp;
            }
        }

        txtcorrectas.setText("Correctas: "+correctas);
        txtincorrectas.setText("Incorrectas: "+incorrectas);
        txtaciertos.setText("Aciertos: "+aciertos);
        txtintentos.setText("Intentos: "+intentos);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnColor1:
                valorcito =1;
                compobrar();
                break;

            case R.id.btnColor2:
                valorcito =2;
                compobrar();
                break;

            case R.id.btnColor3:
                valorcito =3;
                compobrar();
                break;

            case R.id.btnColor4:
                valorcito =4;
                compobrar();
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        bandera=false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        bandera=false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bandera=false;
    }
}
