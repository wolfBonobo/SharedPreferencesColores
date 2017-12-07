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

        sbRojo=(SeekBar)findViewById(R.id.sbRojo);
        sbVerde=(SeekBar)findViewById(R.id.sbVerde);
        sbAzul=(SeekBar)findViewById(R.id.sbAzul);
        sbT=(SeekBar)findViewById(R.id.sbT);

        tvRojo=(TextView)findViewById(R.id.tvRojo);
        tvVerde=(TextView)findViewById(R.id.tvVerde);
        tvAzul=(TextView)findViewById(R.id.tvAzul);
        tvT=(TextView)findViewById(R.id.tvT);

        svaGuardado =(SurfaceView)findViewById(R.id.sfvGuardado);
        svaActual =(SurfaceView)findViewById(R.id.sfvActual);

        btnSharedPreferences=(Button)findViewById(R.id.btnSharedPreferences);

        //Agregamos escuchadores
        sbRojo.setOnSeekBarChangeListener(this);
        sbVerde.setOnSeekBarChangeListener(this);
        sbAzul.setOnSeekBarChangeListener(this);
        sbT.setOnSeekBarChangeListener(this);
        btnSharedPreferences.setOnClickListener(this);

        //Incorporamos Objeto SharedPreferences para la persistencia de la informacion
        SharedPreferences prefe=getSharedPreferences("datos", Context.MODE_PRIVATE);
        tvRojo.setText(prefe.getString("rojo", "0"));
        tvVerde.setText(prefe.getString("verde", "0"));
        tvAzul.setText(prefe.getString("azul", "0"));
        tvT.setText(prefe.getString("opaco", "0"));

        //Ajustamos las SeekBars al valor cargado por las SharedPreferences
        sbRojo.setProgress(Integer.parseInt(tvRojo.getText().toString()));
        sbVerde.setProgress(Integer.parseInt(tvVerde.getText().toString()));
        sbAzul.setProgress(Integer.parseInt(tvAzul.getText().toString()));
        sbT.setProgress(Integer.parseInt(tvT.getText().toString()));
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
       // int color=Integer.parseInt(tvRojo.getText().toString())+Integer.parseInt(tvVerde.getText().toString())+Integer.parseInt(tvAzul.getText().toString());

        svaActual.setBackgroundColor(Color.argb(opacidad,rojo, verde, azul));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onClick(View view) {
        SharedPreferences preferencias=getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferencias.edit();
        editor.putString("rojo", tvRojo.getText().toString());
        editor.putString("verde", tvVerde.getText().toString());
        editor.putString("azul", tvAzul.getText().toString());
        editor.commit();
        Toast.makeText(this, "Se ha guardado el color elegido", Toast.LENGTH_LONG).show();
        int rojo=Integer.parseInt(tvRojo.getText().toString());
        int verde=Integer.parseInt(tvVerde.getText().toString());
        int azul=Integer.parseInt(tvAzul.getText().toString());
        svaGuardado.setBackgroundColor(Color.rgb(rojo, verde, azul));
    }
}
