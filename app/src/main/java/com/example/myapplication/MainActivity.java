package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Intent pasarPantalla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
               startActivity(pasarPantalla = new Intent(MainActivity.this, InicioActivity.class));

            }
        };

        Timer t = new Timer();
        t.schedule(tt, 2000);
    }
}