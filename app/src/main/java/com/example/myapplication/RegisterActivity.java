package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    protected EditText caja1;
    protected EditText caja2;
    protected EditText caja3;
    protected EditText caja4;
    protected EditText caja5;
    protected EditText caja6;
    protected Button boton1;
    protected Spinner spinner;
    protected ImageView pict;

    private String nom = "";
    private String apell = "";
    private String email = "";
    private String psw = "";
    private String psw2 = "";
    private String type = "";
    private String email2 = "";
    private String spinnerItem = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        caja1 = (EditText) findViewById(R.id.register_label_name);
        caja2 = (EditText) findViewById(R.id.register_label_subname);
        caja3 = (EditText) findViewById(R.id.register_label_psw);
        caja4 = (EditText) findViewById(R.id.register_label_psw2);
        caja5 = (EditText) findViewById(R.id.register_label_email);
        caja6 = (EditText) findViewById(R.id.register_label_email2);
        boton1 = (Button) findViewById(R.id.register_button);
        spinner = (Spinner) findViewById(R.id.register_spinner);
        pict = (ImageView) findViewById(R.id.register_check);


        String[] options = {"", "Madre", "Padre", "Hijo", "Hija"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, options);
        spinner.setAdapter(adapter);

        caja3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                psw = caja3.getText().toString();
                psw2 = caja4.getText().toString();

                if (psw2.equals(psw)) {
                    pict.setVisibility(View.VISIBLE);
                } else {
                    pict.setVisibility(View.INVISIBLE);
                }
            }
        });
        caja4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                psw = caja3.getText().toString();
                psw2 = caja4.getText().toString();

                if (psw2.equals(psw)) {
                    pict.setVisibility(View.VISIBLE);
                } else {
                    pict.setVisibility(View.INVISIBLE);
                }
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                spinnerItem = spinner.getSelectedItem().toString();
                System.out.println(spinnerItem);
                if (spinnerItem.equals("")) {
                    caja6.setHint("");
                } else if (spinnerItem.equals("Madre") || spinnerItem.equals("Padre")) {
                    caja6.setHint("Introduce el email de tu hijo o hija");
                } else {
                    caja6.setHint("Introduce el email de tu madre o padre");
                }
                caja6.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                caja6.setVisibility(View.INVISIBLE);
            }

        });


        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nom = caja1.getText().toString();
                apell = caja2.getText().toString();
                email = caja5.getText().toString();
                psw = caja3.getText().toString();
                email2 = caja6.getText().toString();
                spinnerItem = spinner.getSelectedItem().toString();
                DataBaseSQL db = new DataBaseSQL();
                Usuarios nuevoUsuario = new Usuarios();
                boolean creado = false;

                Pattern pattern = Pattern
                        .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

                Matcher mather = pattern.matcher(email);
                Matcher mather2 = pattern.matcher(email2);

                if (nom.equals("") || apell.equals("") || email.equals("") || psw.equals("") || email2.equals("") || spinner.equals("-")) {
                    Toast.makeText(RegisterActivity.this, "Tienes que rellenar todo", Toast.LENGTH_SHORT).show();
                } else if (mather.find() != true || mather2.find() != true) {
                    Toast.makeText(RegisterActivity.this, "Email no válido", Toast.LENGTH_SHORT).show();
                } else if (!pict.isShown()) {
                    Toast.makeText(RegisterActivity.this, "Password deben coincidir", Toast.LENGTH_SHORT).show();
                } else {
                    nuevoUsuario.setNombre(nom);
                    nuevoUsuario.setApellido(apell);
                    nuevoUsuario.setEmail(email);
                    nuevoUsuario.setPassword(psw);
                    nuevoUsuario.setTipo(spinnerItem);
                    nuevoUsuario.setEmail_conect(email2);

                    try {
                        creado = db.Register(nuevoUsuario);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    if (creado) {
                        Intent pasarPantalla = new Intent(RegisterActivity.this, MenuActivity.class);
                        finish();
                        startActivity(pasarPantalla);
                    } else {
                        Toast.makeText(RegisterActivity.this, "Error durante el registro", Toast.LENGTH_SHORT).show();
                    }


                }
            }

        });

    }
}
