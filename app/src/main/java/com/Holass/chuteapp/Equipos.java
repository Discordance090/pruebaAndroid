package com.Holass.chuteapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;



public class Equipos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipos);
    }

    public void onClickMaps(View view){
        Intent intent = new Intent(this,Maps.class);
        startActivity(intent);
    }



}
