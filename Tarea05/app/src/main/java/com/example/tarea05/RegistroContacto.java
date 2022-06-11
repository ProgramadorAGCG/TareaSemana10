package com.example.tarea05;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RegistroContacto extends AppCompatActivity {

    private Button btnLlamar;
    private ListView listContactos;
    private List<String> listaContactos, listaNumeros;
    private ArrayAdapter<String> contactos;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_contacto);
        listContactos = (ListView) findViewById(R.id.listContactos);
        verificarPermisos();
        ObtenerDatos();
        eventoClickItems();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void verificarPermisos(){
        int permisos = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
        if(permisos == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Permisos concedidos", Toast.LENGTH_SHORT).show();
        }else{
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, 200);
        }
    }

    private void ObtenerDatos(){
        String[] projeccion = new String[] { ContactsContract.Data._ID, ContactsContract.Data.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.TYPE };
        String selectionClause = ContactsContract.Data.MIMETYPE + "='" +
                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "' AND "
                + ContactsContract.CommonDataKinds.Phone.NUMBER + " IS NOT NULL";
        String sortOrder = ContactsContract.Data.DISPLAY_NAME + " ASC";

        Cursor c = getContentResolver().query(
                ContactsContract.Data.CONTENT_URI,
                projeccion,
                selectionClause,
                null,
                sortOrder);


        listaContactos = new ArrayList<>();
        listaNumeros = new ArrayList<>();

        while(c.moveToNext()){
            listaContactos.add(c.getString(1));
            listaNumeros.add(c.getString(2));
        }

        String[] listaContactoValores = new String[listaContactos.size()];

        contactos = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                listaContactos.toArray(listaContactoValores));

        listContactos.setAdapter(contactos);

        c.close();
    }

    private void eventoClickItems(){
        listContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String numero = listaNumeros.get(i);
                Intent intent = new Intent(android.content.Intent.ACTION_DIAL, Uri.parse("tel:" + numero));
                startActivity(intent);
            }
        });
    }

}