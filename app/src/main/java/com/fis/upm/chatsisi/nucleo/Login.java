package com.fis.upm.chatsisi.nucleo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.fis.upm.chatsisi.R;
import com.fis.upm.chatsisi.nucleo.Inicio;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button btnLogin;
    private ImageView ivLogo;
    private EditText etUsuario,etContraseña;
    private TextView txtRecordar,txtCrearCuenta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        inicializarVariables();
    }
    private void inicializarVariables(){
        //BUTTON
        btnLogin = findViewById(R.id.btnLogin);
        //EDITTEXT
        etUsuario = findViewById(R.id.etUsuario);
        etContraseña = findViewById(R.id.etContraseña);
        //TEXTVIEW
        txtRecordar = findViewById(R.id.txtRecordar);
        txtCrearCuenta = findViewById(R.id.txtCrearCuenta);
        //ONCLICKLISTENER
        btnLogin.setOnClickListener(this);
        txtRecordar.setOnClickListener(this);
        txtCrearCuenta.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.btnLogin){
            Intent i = new Intent(this,Inicio.class);
            startActivity(i);
            finish();
        }else if (view.getId()==R.id.txtRecordar){

        }else if (view.getId()==R.id.txtCrearCuenta){

        }
    }
}
