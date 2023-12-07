package com.example.chuteapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Firebase extends AppCompatActivity {

    private EditText txtComuna,txtNombreEquipo;
    private ListView listaEquipos;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_firebase);
        CargarListaFirestore();

        db= FirebaseFirestore.getInstance();
        txtNombreEquipo= findViewById(R.id.txtNombreEquipos);
        txtComuna= findViewById(R.id.txtComuna);
        listaEquipos= findViewById(R.id.listafb);


    }
    public void CargarLista(View view){
        CargarListaFirestore();
    }
    public void CargarListaFirestore(){

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("equipos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            List<String> listaEquipos = new ArrayList<>();

                            //Recorre todos los datos obtenidos ordenandolos en la lista
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String linea = "||" + document.getString("nombre") + "||" +
                                        document.getString("comuna") ;
                                listaEquipos.add(linea);
                            }

                        } else {
                            //Se imprimira en consola si ahi errores al traer los datos
                            Log.e("TAG", "Error al obtener datos de Firestore", task.getException());
                        }
                    }
                });
    }

    public void enviarDatosFirestone(View  view){
        String nombreEquipo = txtNombreEquipo.getText().toString();
        String comuna = txtComuna.getText().toString();

        Map<String,Object> equipo = new HashMap<>();
        equipo.put("NombreEquipo",txtNombreEquipo);
        equipo.put("Comuna",txtComuna);

        db.collection("equipos")
                .document(nombreEquipo)
                .set(equipo)
                .addOnSuccessListener(aVoid ->{
                    Toast.makeText(this,"DATOS ENVIADOS CORRECTAMENTE",Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e->{
                   Toast.makeText(this,"ERROR AL ENVIAR LOS DATOS "+e.getMessage(),Toast.LENGTH_SHORT).show();
                });
    }



}
