package com.example.ejercicio1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class Datos extends SQLiteOpenHelper {

    public Datos(Context context, String name, CursorFactory factory,
                 int version) {
        super(context, name, factory, version);
    }
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table paciente(cod_pac integer primary key autoincrement, ape_pac text, dir_pac text, tel_pac text, cor_pac text)");
        db.execSQL("create table especialidad(cod_esp integer primary key autoincrement, nom_esp text)");
        db.execSQL("create table cita(cod_cita integer primary key autoincrement, cod_pac integer, cod_esp integer," +
                "fecha_cita text, hora_cita text, foreign key (cod_pac) references paciente(cod_pac), " +
                "foreign key (cod_esp) references especialidad(cod_esp))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}