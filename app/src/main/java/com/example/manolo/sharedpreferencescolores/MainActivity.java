package com.example.manolo.sharedpreferencescolores;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {

    SeekBar sbRojo, sbVerde, sbAzul,sbT;
    TextView tvRojo, tvVerde, tvAzul,tvT;
    Button btnSharedPreferences;
    SurfaceView svaGuardado,svaActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sbRojo=findViewById(R.id.sbRojo);
        sbVerde=findViewById(R.id.sbVerde);
        sbAzul=findViewById(R.id.sbAzul);
        sbT=findViewById(R.id.sbT);

        tvRojo=findViewById(R.id.tvRojo);
        tvVerde=findViewById(R.id.tvVerde);
        tvAzul=findViewById(R.id.tvAzul);
        tvT=findViewById(R.id.tvT);

        svaGuardado =findViewById(R.id.sfvGuardado);
        svaActual =findViewById(R.id.sfvActual);

        btnSharedPreferences=findViewById(R.id.btnSharedPreferences);

        //Agregamos escuchadores/Eventos a SeekBars
        sbRojo.setOnSeekBarChangeListener(this);
        sbVerde.setOnSeekBarChangeListener(this);
        sbAzul.setOnSeekBarChangeListener(this);
        sbT.setOnSeekBarChangeListener(this);
        btnSharedPreferences.setOnClickListener(this);

        //SHARED PREFERENCES
        //Incorporamos Objeto SharedPreferences para la persistencia de la informacion
        //Clave valor. Siempre poner un datos entre comillas para evitar exceptions
        SharedPreferences prefe=getSharedPreferences("datos", Context.MODE_PRIVATE);
        tvRojo.setText(prefe.getString("rojo", "0"));
        tvVerde.setText(prefe.getString("verde", "0"));
        tvAzul.setText(prefe.getString("azul", "0"));
        tvT.setText(prefe.getString("opaco", "250"));

        //Ajustamos las SeekBars al valor cargado por las SharedPreferences
        sbRojo.setProgress(Integer.parseInt(tvRojo.getText().toString()));
        sbVerde.setProgress(Integer.parseInt(tvVerde.getText().toString()));
        sbAzul.setProgress(Integer.parseInt(tvAzul.getText().toString()));
        sbT.setProgress(Integer.parseInt(tvT.getText().toString()));
        // svaGuardado.setBackgroundColor(Color.rgb(rojo,verde,azul));

        //PARA OPACIDAD
        int opacidad=Integer.parseInt(tvT.getText().toString());
        int rojo=Integer.parseInt(tvRojo.getText().toString());
        int verde=Integer.parseInt(tvVerde.getText().toString());
        int azul=Integer.parseInt(tvAzul.getText().toString());
        svaGuardado.setBackgroundColor(Color.argb(opacidad,rojo, verde, azul));

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        int id=seekBar.getId();

        if(id==sbRojo.getId())
            tvRojo.setText(new Integer(i).toString());
        if(id==sbVerde.getId())
            tvVerde.setText(new Integer(i).toString());
        if(id==sbAzul.getId())
            tvAzul.setText(new Integer(i).toString());
        if(id==sbT.getId())
            tvT.setText(new Integer(i).toString());
        int rojo=Integer.parseInt(tvRojo.getText().toString());
        int verde=Integer.parseInt(tvVerde.getText().toString());
        int azul=Integer.parseInt(tvAzul.getText().toString());
        int opacidad=Integer.parseInt(tvT.getText().toString());
      //  svaActual.setBackgroundColor(Color.rgb(opacidad,rojo, verde, azul));
        //se actualiza el sufaceView co los colores (Opaciodad incluida
        svaActual.setBackgroundColor(Color.argb(opacidad,rojo, verde, azul));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    //Evento bonto
    @Override
    public void onClick(View view) {
        //SE RECUPERA LOS DATOS CUADADOS EN EL MOVIL "datos"
        SharedPreferences preferencias=getSharedPreferences("datos", Context.MODE_PRIVATE);
        //se indica que lo vamos a editar
        SharedPreferences.Editor editor=preferencias.edit();
        //Recuperamos valores de los textView y lo añadimos con metodo put
        editor.putString("rojo", tvRojo.getText().toString());
        editor.putString("verde", tvVerde.getText().toString());
        editor.putString("azul", tvAzul.getText().toString());
        editor.putString("opaco", tvT.getText().toString());
        //commit
        editor.commit();
        Toast.makeText(this, "Se ha guardado el color elegido", Toast.LENGTH_LONG).show();

        //ACTUALIZAMOS SURFACE VIEW GUARDADO
        int rojo=Integer.parseInt(tvRojo.getText().toString());
        int verde=Integer.parseInt(tvVerde.getText().toString());
        int azul=Integer.parseInt(tvAzul.getText().toString());
        int opaco=Integer.parseInt(tvT.getText().toString());
        svaGuardado.setBackgroundColor(Color.argb(opaco,rojo, verde, azul));
    }
}
