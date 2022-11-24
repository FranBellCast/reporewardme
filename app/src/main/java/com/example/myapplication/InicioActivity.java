package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class InicioActivity extends AppCompatActivity {

    protected Button button_registro;
    protected Button button_iniciar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);


        button_registro = (Button) findViewById(R.id.button_registro);
        button_iniciar = (Button) findViewById(R.id.button_iniciar);

        button_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent pasarPantalla = new Intent(InicioActivity.this, RegisterActivity.class);
                finish();
                startActivity(pasarPantalla);

            }
        });

        button_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent pasarPantalla = new Intent(InicioActivity.this, LoginActivity.class);
                finish();
                startActivity(pasarPantalla);

            }


        });
    }
}
