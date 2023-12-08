package com.Holass.chuteapp;

import android.content.ContentValues;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



public class Crear extends AppCompatActivity {
    EditText edtNombre;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        edtNombre = (EditText) findViewById(R.id.nomEquipo);
    }
    public void onClickCrearEquipo(View view) {
        DataHelper dh = new DataHelper(this, "equipos.db", null, 1);
        SQLiteDatabase bd = dh.getWritableDatabase();
        ContentValues reg = new ContentValues();
        reg.put("name", edtNombre.getText().toString());
        long resp = bd.insert("equipos", null, reg);
        bd.close();
        if (resp == -1) {
            Toast.makeText(this, "No se pudo Ingresar", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Equipo creado Correctamente", Toast.LENGTH_LONG).show();

        }
        finish();

    }
}