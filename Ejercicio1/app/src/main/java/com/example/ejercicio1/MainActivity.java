package com.example.ejercicio1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button b1,b2,b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)findViewById(R.id.dbPaciente);
        b2=(Button)findViewById(R.id.dbEspecialidad);
        b3=(Button)findViewById(R.id.dbCitaMedica);
    }

    public void abrirPaciente(View view) {
        startActivity(new Intent(MainActivity.this,Paciente.class));
    }
    public void abrirEspecialidad(View view) {
        startActivity(new Intent(MainActivity.this,Especialidad.class));
    }
    public void abrirCitaMedica(View view) {
        startActivity(new Intent(MainActivity.this,CitaMedica.class));
    }
}