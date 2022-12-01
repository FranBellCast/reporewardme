package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecuperarActivity extends AppCompatActivity {
    protected Button boton1;
    protected EditText caja1;
    protected EditText caja2;
    protected DataBaseSQL db;

    private String cadena = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar);


        boton1 = (Button) findViewById(R.id.recuperar_boton);
        caja1 = (EditText) findViewById(R.id.recuperar_email);
        caja2 = (EditText) findViewById(R.id.recuperar_codigo);

        caja2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String code = caja2.getText().toString();


                if (code.equals("")) {
                    boton1.setText("Solicitar codigo");
                    boton1.setBackgroundColor(Color.parseColor("#013ADF"));

                } else {
                    boton1.setText("VALIDAR");
                    boton1.setBackgroundColor(Color.parseColor("#FF0000"));

                }
            }
        });
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String contenido = boton1.getText().toString();
                String email = caja1.getText().toString();
                String codigo = caja2.getText().toString();
                db = new DataBaseSQL();
                Boolean comprobado = false;
                try {
                    comprobado = db.ValidarEmial(email);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                System.out.println(contenido);
                System.out.println(cadena);

                Pattern pattern = Pattern
                        .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

                Matcher mather = pattern.matcher(email);

                //Genero codigo simbólico (habría que incluir sistema de mailing para mandar un codigo)

                if (!contenido.equals("VALIDAR")) {
                    cadena = "";
                    Random rand = new Random();

                    for (int i = 0; i < 4; i++) {

                        int randomNum = rand.nextInt(9 - 0);
                        String s = Integer.toString(randomNum);
                        cadena += s;
                    }

                    Toast.makeText(RecuperarActivity.this, "Introduce: " + cadena, Toast.LENGTH_SHORT).show();
                } else if (contenido.equals("") || email.equals("")) {
                    Toast.makeText(RecuperarActivity.this, "Tienes que rellenar todo", Toast.LENGTH_SHORT).show();
                } else if (mather.find() != true) {
                    Toast.makeText(RecuperarActivity.this, "Email no válido", Toast.LENGTH_SHORT).show();
                } else if (!codigo.equals(cadena)) {
                    Toast.makeText(RecuperarActivity.this, "Código erroneo", Toast.LENGTH_SHORT).show();
                } else if (!comprobado) {
                    Toast.makeText(RecuperarActivity.this, "Email no registrado", Toast.LENGTH_SHORT).show();
                } else {
                    Intent pasarPantalla = new Intent(RecuperarActivity.this, ChangePSWActivity.class);
                    pasarPantalla.putExtra("NAME", email);
                    finish();
                    startActivity(pasarPantalla);
                }

            }
        });
    }
}