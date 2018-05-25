package com.fis.upm.chatsisi.nucleo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fis.upm.chatsisi.R;

import java.util.ArrayList;

public class CrearUsuario extends AppCompatActivity implements View.OnClickListener {

    private EditText etNombre,etApellidos,etCorreo,etContraseña,etContraseña2;
    private Button btnCrear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_usuario);
    }
    private void inicializarVariables(){
        //BUTTON
        btnCrear = findViewById(R.id.btnCrear);
        //EDITTEXT
        etNombre = findViewById(R.id.etNombre);
        etApellidos = findViewById(R.id.etApellidos);
        etCorreo = findViewById(R.id.etCorreo);
        etContraseña = findViewById(R.id.etContraseña);
        etContraseña2 = findViewById(R.id.etContraseña2);
        //ONCLICKLISTENER
        btnCrear.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.btnCrear){

        }
    }
}
