package com.Holass.chuteapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private EditText emailLogin, passwordLogin;
    protected FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Initialize FireBase Auth
        mAuth = FirebaseAuth.getInstance();

        //splashScreen con logo al inicio de la app con duración de 2 seg
        setTheme(R.style.Theme_ChuteApp);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailLogin = findViewById(R.id.emailLogin);
        passwordLogin = findViewById(R.id.passwordLogin);
    }

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null){
            updateUI(user);
        }
    }
    //Evento botón ingresar en pantalla inicio de sesión
    public void onClickIngreso(View view){
        String email = emailLogin.getText().toString();
        String password = passwordLogin.getText().toString();
        if(!email.isEmpty() && !password.isEmpty()){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        }
                        else{
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Autenticación fallida", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        }
        else{
            Toast.makeText(this, "Debe completar los parámetros", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickRegistro(View view){
        Intent intent = new Intent(MainActivity.this, register.class);
        startActivity(intent);
    }

    private void reload(){ }

    private void updateUI(FirebaseUser user) {
        Intent perfilIntent = new Intent(this, Perfil.class).putExtra("user", user);
        startActivity(perfilIntent);
    }
}