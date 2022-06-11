package com.example.ejercicio1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Especialidad extends AppCompatActivity {

    private EditText txtespCod,txtespNom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_especialidad);
        txtespCod=findViewById(R.id.txtcodesp);
        txtespNom=findViewById(R.id.txtnomesp);
    }

    public void ingresar(View view){
        Datos datos=new Datos(this,"dbcita",null,1);
        SQLiteDatabase sqlitedb= datos.getWritableDatabase();
        try {
            int codesp=Integer.parseInt(txtespCod.getText().toString());
            String nomesp=txtespNom.getText().toString();
            ContentValues registro = new ContentValues();
            registro.put("cod_esp",codesp);
            registro.put("nom_esp",nomesp);
            sqlitedb.insert("especialidad",null,registro);
            Toast.makeText(this, "Registro insertado satisfactoriamente", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Ocurrió un error al insertar", Toast.LENGTH_SHORT).show();
        }finally {
            sqlitedb.close();
        }
    }
    public void eliminar(View view){
        Datos datos=new Datos(this,"dbcita",null,1);
        SQLiteDatabase sqlitedb= datos.getWritableDatabase();
        try {
            int codesp=Integer.parseInt(txtespCod.getText().toString());
            sqlitedb.delete("especialidad","cod_esp="+codesp,null);
            Toast.makeText(this, "Registro eliminado satisfactoriamente", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Ocurrió un error al eliminar", Toast.LENGTH_SHORT).show();
        } finally {
            sqlitedb.close();
        }
    }
    public void modificar(View view){
        Datos datos=new Datos(this,"dbcita",null,1);
        SQLiteDatabase sqlitedb= datos.getWritableDatabase();
        try {
            int codesp=Integer.parseInt(txtespCod.getText().toString());
            String nomesp=txtespNom.getText().toString();
            ContentValues registro = new ContentValues();
            registro.put("cod_esp",codesp);
            registro.put("nom_esp",nomesp);
            sqlitedb.update("especialidad",registro,"cod_esp="+codesp,null);
            Toast.makeText(this, "Registro actualizado satisfactoriamente", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Ocurrió un error al actualizar", Toast.LENGTH_SHORT).show();
        } finally {
            sqlitedb.close();
        }

    }
    public void consultar(View v){
        Datos datos=new Datos(this,"dbcita",null,1);
        SQLiteDatabase sqlitedb= datos.getWritableDatabase();
        int codesp=Integer.parseInt(txtespCod.getText().toString());
        Cursor fila=sqlitedb.rawQuery("select nom_esp from especialidad where cod_esp="+codesp,null);
        if(fila.moveToFirst()){
            txtespNom.setText(fila.getString(0));
            Toast.makeText(this, "Registro consultado satisfactoriamente", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "No existe el código consultado", Toast.LENGTH_SHORT).show();
        sqlitedb.close();
    }
}