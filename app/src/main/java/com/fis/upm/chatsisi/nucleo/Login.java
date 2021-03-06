package com.fis.upm.chatsisi.nucleo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.fis.upm.chatsisi.R;
import com.fis.upm.chatsisi.SharedPreferences.GestorSharedPreferences;
import com.fis.upm.chatsisi.daos.UsuarioDAO;
import com.fis.upm.chatsisi.entities.Usuario;
import com.fis.upm.chatsisi.nucleo.Inicio;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.GenericArrayType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        try {
            if (UsuarioDAO.buscarTodosLosUsuarios(this)==null||UsuarioDAO.buscarTodosLosUsuarios(this).size()<2){
                UsuarioDAO.newUsuario(this,"javi","a","Javier",
                        "Borreguero","j.borreguero@alumnos.upm.es","","");

                UsuarioDAO.newUsuario(this,"alvaro","a","Alvaro",
                        "Calzado","a.cperez@alumnso.upm.es","","");

                UsuarioDAO.newUsuario(this,"hiubert","a","Alberto",
                        "Marcos","a.marcos@alumnos.upm.es","","");

                UsuarioDAO.newUsuario(this,"yoel","a","Yoel",
                        "Castillo","y.castillo@alumnos.upm.es","","");

                UsuarioDAO.newUsuario(this,"gomez","a","Carlos",
                        "Gomez","c.gomez@alumnos.upm.es","","");

                UsuarioDAO.newUsuario(this,"victor","a","Victor",
                        "Diaz","v.diaz@alumnos.upm.es","","");

                UsuarioDAO.newUsuario(this,"rodrigo","a","Rodrigo",
                        "Martinez","r.martinez@alumnos.upm.es","","");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = GestorSharedPreferences.getJsonUsuario(GestorSharedPreferences.getSharedPreferencesUsuario(this));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (!jsonObject.toString().equals("{}")){
            Intent i = new Intent(this,Inicio.class);
            startActivity(i);
            finish();
        }
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
            if (!etUsuario.getText().toString().trim().equals("")||!etContraseña.getText().toString().trim().equals("")){
                try {
                    if (UsuarioDAO.validarUsuario(this,etUsuario.getText().toString(),etContraseña.getText().toString())!=null){
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("id",UsuarioDAO.validarUsuario(this,etUsuario.getText().toString(),etContraseña.getText().toString()).getId_usuario());
                            GestorSharedPreferences.setJsonUsuario(GestorSharedPreferences.getSharedPreferencesUsuario(this),jsonObject);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        JSONObject jsonObject1 = new JSONObject();
                        try {
                            jsonObject1.put("tipo",1);
                            GestorSharedPreferences.setJsonTipo(GestorSharedPreferences.getSharedPreferencesTipo(this),jsonObject1);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Intent i = new Intent(this,Inicio.class);
                        startActivity(i);
                        finish();
                    }else{
                        sacarDialogo("Usuario o contraseña incorrectos","");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else{
                sacarDialogo("Rellene los campos","");
            }

        }else if (view.getId()==R.id.txtRecordar){

        }else if (view.getId()==R.id.txtCrearCuenta){
            Intent i = new Intent(this,CrearUsuario.class);
            startActivity(i);
            finish();
        }
    }
    private void sacarDialogo(String titulo,String texto){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle(titulo);
        builder1.setMessage(texto);
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                "Aceptar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.setCanceledOnTouchOutside(false);
        alert11.show();
    }
}
