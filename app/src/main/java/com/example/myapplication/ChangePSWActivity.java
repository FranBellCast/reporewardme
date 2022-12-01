package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.sql.SQLException;

public class ChangePSWActivity extends AppCompatActivity {

    protected EditText caja1;
    protected EditText caja2;
    protected Button boton;
    protected ImageView pict;

    private String psw = "";
    private String psw2 = "";
    private Bundle extras;
    private String paquete1="";
    private DataBaseSQL db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pswactivity);

        caja1 = (EditText) findViewById(R.id.change_psw);
        caja2 = (EditText) findViewById(R.id.change_psw2);
        boton = (Button) findViewById(R.id.change_boton);
        pict = (ImageView) findViewById(R.id.change_pict);

        extras =getIntent().getExtras();
        paquete1 = extras.getString("NAME");

        caja1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                psw = caja1.getText().toString();
                psw2 = caja2.getText().toString();

                if (psw2.equals(psw)) {
                    pict.setVisibility(View.VISIBLE);
                } else {
                    pict.setVisibility(View.INVISIBLE);
                }
            }
        });
        caja2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                psw = caja1.getText().toString();
                psw2 = caja2.getText().toString();

                if (psw2.equals(psw)) {
                    pict.setVisibility(View.VISIBLE);
                } else {
                    pict.setVisibility(View.INVISIBLE);
                }
            }
        });


        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = new DataBaseSQL();
                boolean realizado = false;
                psw2 = caja2.getText().toString();

                try {
                    realizado= db.ChangePsw(psw2, paquete1);
                } catch (SQLException e) {
                    e.printStackTrace();
                }


                if (!realizado){
                    Toast.makeText(ChangePSWActivity.this, "Volver a intentar", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ChangePSWActivity.this, "Cambiado", Toast.LENGTH_SHORT).show();
                    Intent pasarPantalla = new Intent(ChangePSWActivity.this, LoginActivity.class);
                    finish();
                    startActivity(pasarPantalla);
                }


            }
        });
    }
}