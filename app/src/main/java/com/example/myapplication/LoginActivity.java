package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity {

    protected Button boton1;
    protected EditText caja1;
    protected EditText caja2;
    protected DataBaseSQL db;
    protected Button boton2;

    private String contenidoCaja1 = "";
    private String contenidoCaja2 = "";
    private boolean tieneAcceso = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        boton1 = (Button) findViewById(R.id.button_entrar);
        caja1 = (EditText) findViewById(R.id.text_email);
        caja2 = (EditText) findViewById(R.id.text_password);
        boton2 = (Button) findViewById(R.id.login_botonCambiar);

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contenidoCaja1 = caja1.getText().toString();
                contenidoCaja2 = caja2.getText().toString();
                System.out.println(contenidoCaja1);
                System.out.println(contenidoCaja2);
                if (contenidoCaja1.equals("") || contenidoCaja2.equals("")) {
                    Toast.makeText(LoginActivity.this, "ERROR", Toast.LENGTH_SHORT).show();

                } else {
                    try {
                        db = new DataBaseSQL();
                        tieneAcceso = db.Validar(contenidoCaja1, contenidoCaja2);
                        if (tieneAcceso) {
                            Intent pasarPantalla = new Intent(LoginActivity.this, MenuActivity.class);
                            finish();
                            startActivity(pasarPantalla);
                        } else {
                            Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrectos ", Toast.LENGTH_SHORT).show();
                            Intent pasarPantalla = new Intent(LoginActivity.this, MenuActivity.class);
                            finish();
                            startActivity(pasarPantalla);
                        }
                    } catch (Exception e) {

                    }


                }
            }
        });
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pasarPantalla = new Intent(LoginActivity.this, RecuperarActivity.class);
                finish();
                startActivity(pasarPantalla);
            }
        });

    }

}
