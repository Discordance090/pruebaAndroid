package com.example.chuteapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class MisEquipos extends AppCompatActivity {

    ListView lista;
    EditText edtName;
    TextView id;
    private long selectedItemID;
    Button btnEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_equipos);
        lista = (ListView) findViewById(R.id.listaMisEquipos);
        btnEdit = (Button) findViewById(R.id.btnEditar);

        CargarLista();

    }
    public void CargarLista(){
        DataHelper dh = new DataHelper(this, "equipos.db", null, 1);
        SQLiteDatabase bd = dh.getWritableDatabase();
        Cursor c = bd.rawQuery("SELECT id, name FROM equipos", null);

        List<EquipoAd> equiposlista = new ArrayList<>();


        if (c.moveToFirst()) {
            do {
                long idBase = c.getLong(c.getColumnIndexOrThrow("id"));
                String nameBase = c.getString(c.getColumnIndexOrThrow("name"));
                equiposlista.add(new EquipoAd(idBase, nameBase));
            } while (c.moveToNext());
        }

        ArrayAdapter<EquipoAd> adapter = new ArrayAdapter<EquipoAd>
                (this, android.R.layout.simple_expandable_list_item_1, equiposlista);
        lista.setAdapter(adapter);

        ListView listView = findViewById(R.id.listaMisEquipos);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Aquí obtengo el ID del elemento seleccionado en la base de datos Profe, lo logré :D
                selectedItemID = equiposlista.get(position).getId();
                TextView txtEquipo = findViewById(R.id.idEquipo);
                txtEquipo.setText(String.valueOf(selectedItemID));
            }
        });

    }
    public void onClickCrear(View view){
        Intent intent = new Intent(this,Crear.class);
        startActivity(intent);
    }

    public void onClickEditar(View view){
        TextView id = (TextView) findViewById(R.id.idEquipo);
        // En ActivityA
        String txtId = id.getText().toString();

        Intent intent = new Intent(MisEquipos.this, Equipo.class);
        intent.putExtra("contenido", txtId);
        startActivity(intent);
    }
    public void onClickEliminar(View view){
        DataHelper dh = new DataHelper(this, "equipos.db",  null, 1);
        SQLiteDatabase bd = dh.getWritableDatabase();
        id = (TextView) findViewById(R.id.idEquipo);
        int iD = Integer.parseInt(id.getText().toString());
        long resp = bd.delete("equipos", "id="+ iD, null);
        Log.d("EliminarRegistro", "ID a eliminar: " + iD);

        bd.close();
        if(resp ==-1){
            Toast.makeText(this, "No se pudo Eliminar",
                    Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Equipo Eliminado",
                    Toast.LENGTH_LONG).show();
        }
        CargarLista();
    }

}