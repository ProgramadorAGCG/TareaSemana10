package com.example.ejercicio1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class CitaMedica extends AppCompatActivity {

    private EditText txtCodCita,txtCodPac,txtCodEsp,txtfecha,txthora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cita_medica);
        txtCodCita=findViewById(R.id.txtcodCita);
        txtCodPac=findViewById(R.id.txtcodPac);
        txtCodEsp=findViewById(R.id.txtcodEsp);
        txtfecha=findViewById(R.id.txtFecha);
        txthora=findViewById(R.id.txtHora);
    }

    public void ingresar(View view){
        Datos datos=new Datos(this,"dbcita",null,1);
        SQLiteDatabase sqlitedb= datos.getWritableDatabase();
        try {
            int codcita=Integer.parseInt(txtCodCita.getText().toString());
            int codpac=Integer.parseInt(txtCodPac.getText().toString());
            int codesp=Integer.parseInt(txtCodEsp.getText().toString());
            String fecha=txtfecha.getText().toString();
            String hora=txthora.getText().toString();
            ContentValues registro = new ContentValues();
            registro.put("cod_cita",codcita);
            registro.put("cod_pac",codpac);
            registro.put("cod_esp",codesp);
            registro.put("fecha_cita",fecha);
            registro.put("hora_cita",hora);
            sqlitedb.insert("cita",null,registro);
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
            sqlitedb.delete("cita","cod_pac="+codpac,null);
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
            int codcita=Integer.parseInt(txtCodCita.getText().toString());
            int codpac=Integer.parseInt(txtCodPac.getText().toString());
            int codesp=Integer.parseInt(txtCodEsp.getText().toString());
            String fecha=txtfecha.getText().toString();
            String hora=txthora.getText().toString();
            ContentValues registro = new ContentValues();
            registro.put("cod_cita",codcita);
            registro.put("cod_pac",codpac);
            registro.put("cod_esp",codesp);
            registro.put("fecha_cita",fecha);
            registro.put("hora_cita",hora);
            sqlitedb.update("cita", registro, "cod_cita=" + codcita, null);
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
            int codcita=Integer.parseInt(txtCodCita.getText().toString());
            Cursor fila=sqlitedb.rawQuery("select cod_pac, cod_esp, fecha_cita, hora_cita from cita where cod_cita="+codcita,null);
            if(fila.moveToFirst()){
                //  txtCodCita.setText(fila.getString(0));
                txtCodPac.setText(fila.getString(0));
                txtCodEsp.setText(fila.getString(1));
                txtfecha.setText(fila.getString(2));
                txthora.setText(fila.getString(3));
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
}