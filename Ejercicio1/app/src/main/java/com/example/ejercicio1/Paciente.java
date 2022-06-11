package com.example.ejercicio1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Paciente extends AppCompatActivity {

    private EditText txtCodPac,txtApe,txtDir,txtTel,txtCor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente);
        txtCodPac=findViewById(R.id.txtcodpac);
        txtApe=findViewById(R.id.txtape);
        txtDir=findViewById(R.id.txtdir);
        txtTel=findViewById(R.id.txttel);
        txtCor=findViewById(R.id.txtcor);
    }

    public void ingresar(View view){
        Datos datos=new Datos(this,"dbcita",null,1);
        SQLiteDatabase sqlitedb= datos.getWritableDatabase();
        try {
            int codpac=Integer.parseInt(txtCodPac.getText().toString());
            String ape=txtApe.getText().toString();
            String dir=txtDir.getText().toString();
            String tel=txtTel.getText().toString();
            String cor=txtCor.getText().toString();
            //int tel=Integer.parseInt(txtEmpTel.getText().toString());
            ContentValues registro = new ContentValues();
            registro.put("cod_pac",codpac);
            registro.put("ape_pac",ape);
            registro.put("dir_pac",dir);
            registro.put("tel_pac",tel);
            registro.put("cor_pac",cor);
            sqlitedb.insert("paciente",null,registro);
            Toast.makeText(this, "Registro insertado satisfactoriamente", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Ocurrió un error al insertar. "+e, Toast.LENGTH_SHORT).show();
        }finally {
            sqlitedb.close();
        }
    }
    public void eliminar(View view){
        Datos datos=new Datos(this,"dbcita",null,1);
        SQLiteDatabase sqlitedb= datos.getWritableDatabase();
        try {
            int codpac=Integer.parseInt(txtCodPac.getText().toString());
            sqlitedb.delete("paciente","cod_pac="+codpac,null);
            Toast.makeText(this, "Registro eliminado satisfactoriamente", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Ocurrió un error al eliminar. "+e, Toast.LENGTH_SHORT).show();
        } finally {
            sqlitedb.close();
        }
    }
    public void modificar(View view){
        Datos datos=new Datos(this,"dbcita",null,1);
        SQLiteDatabase sqlitedb= datos.getWritableDatabase();
        try {
            int codpac = Integer.parseInt(txtCodPac.getText().toString());
            String ape=txtApe.getText().toString();
            String dir=txtDir.getText().toString();
            String tel=txtTel.getText().toString();
            String cor=txtCor.getText().toString();
            //int tel=Integer.parseInt(txtEmpTel.getText().toString());
            ContentValues registro = new ContentValues();
            registro.put("cod_pac",codpac);
            registro.put("ape_pac",ape);
            registro.put("dir_pac",dir);
            registro.put("tel_pac",tel);
            registro.put("cor_pac",cor);
            sqlitedb.update("paciente", registro, "cod_pac=" + codpac, null);
            Toast.makeText(this, "Registro actualizado satisfactoriamente", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Ocurrió un error al insertar. "+e, Toast.LENGTH_SHORT).show();
        }finally {
            sqlitedb.close();
        }
    }
    public void consultar(View v){
        Datos datos=new Datos(this,"dbcita",null,1);
        SQLiteDatabase sqlitedb= datos.getWritableDatabase();
        try {
            int codpac=Integer.parseInt(txtCodPac.getText().toString());
            Cursor fila=sqlitedb.rawQuery("select ape_pac, dir_pac, tel_pac, cor_pac from paciente where cod_pac="+codpac,null);
            if(fila.moveToFirst()){
                // txtCodPac.setText(fila.getString(0));
                txtApe.setText(fila.getString(0));
                txtDir.setText(fila.getString(1));
                txtTel.setText(fila.getString(2));
                txtCor.setText(fila.getString(3));
                Toast.makeText(this, "Registro consultado satisfactoriamente", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(this, "No existe el código consultado", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al consultar", Toast.LENGTH_SHORT).show();
        } finally {
            sqlitedb.close();
        }
    }

    public void metodoLimpiar(){
        txtCodPac.setText("");
        txtApe.setText("");
        txtDir.setText("");
        txtTel.setText("");
        txtCor.setText("");
    }
}