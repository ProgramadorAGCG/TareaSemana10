package com.example.tarea05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnTomarFoto, btnRegistroLlamadas, btnRegistroContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnTomarFoto = (Button) findViewById(R.id.btnTomarFoto);
        btnRegistroLlamadas = (Button) findViewById(R.id.btnRegistroLlamadas);
        btnRegistroContactos = (Button) findViewById(R.id.btnRegistroContactos);
        btnTomarFoto.setOnClickListener(this);
        btnRegistroLlamadas.setOnClickListener(this);
        btnRegistroContactos.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Intent i;
        if(view == btnTomarFoto){
            i = new Intent(this, TomarFoto.class);
        }else if(view == btnRegistroLlamadas){
            i = new Intent(this, RegistroLlamada.class);
        }else{
            i = new Intent(this, RegistroContacto.class);
        }
        startActivity(i);
    }
}