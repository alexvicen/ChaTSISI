package com.fis.upm.chatsisi.nucleo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fis.upm.chatsisi.R;
import com.fis.upm.chatsisi.SharedPreferences.GestorSharedPreferences;
import com.fis.upm.chatsisi.daos.UsuarioDAO;
import com.fis.upm.chatsisi.entities.Usuario;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CrearUsuario extends AppCompatActivity implements View.OnClickListener {

    private EditText etNombreUsuario, etNombre,etApellidos,etCorreo,etContraseña,etContraseña2;
    private Button btnCrear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_usuario);
        inicializarVariables();
    }


    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.btnCrear){
            if (etNombreUsuario.getText().toString().trim().equals("")){
                String titulo="Es necesario rellenar el nobre usuario.";
                String texto="(Este nombre sera utilizado para loguearte junto con el Email)";
                sacarDialogo(titulo,texto);
            } else if (etNombre.getText().toString().trim().equals("")){
                String titulo="Es necesario rellenar el nombre.";
                String texto="(Esta informacion le saldra a tus contactos)";
                sacarDialogo(titulo,texto);
            } else if (etApellidos.getText().toString().trim().equals("")){
                String titulo="Es necesario rellenar los apellidos.";
                String texto="(Esta informacion le saldra a tus contactos)";
                sacarDialogo(titulo,texto);
            } else if (etCorreo.getText().toString().trim().equals("")||!checkEmail(etCorreo.getText().toString().trim())){
                String titulo="Es necesario rellenar el correo valido de la UPM.";
                String texto="(Esto nos permite saber que eres de la UPM)";
                sacarDialogo(titulo,texto);
            } else if (etContraseña.getText().toString().trim().equals("")){
                String titulo="Es necesario rellenar la contraseña.";
                sacarDialogo(titulo,"");
            } else if (etContraseña.getText().toString().trim().equals(etContraseña2.getText().toString().trim())){
                try {
                    if (UsuarioDAO.buscarUsuarioPorNombreCorreo(this,etNombreUsuario.getText().toString().trim(), etCorreo.getText().toString().trim())==null){
                        Usuario usu = UsuarioDAO.newUsuarioRet(this,
                                etNombreUsuario.getText().toString().trim(),
                                etContraseña.getText().toString().trim(),
                                etNombre.getText().toString().trim(),
                                etApellidos.getText().toString().trim(),
                                etCorreo.getText().toString().trim(),
                                "",
                                "",
                                "");
                            JSONObject jsonObject = new JSONObject();
                            try {
                                jsonObject.put("id",usu.getId_usuario());
                                GestorSharedPreferences.setJsonUsuario(GestorSharedPreferences.getSharedPreferencesUsuario(this),jsonObject);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Intent i = new Intent(this,Inicio.class);
                            startActivity(i);
                            finish();
                    }else{
                        String titulo="Ya existe otro usuario con ese nombre o ese correo.";
                        String texto="(Si no te acuerdas de la contraseña puedes recordarla desde la pantalla anterior)";
                        sacarDialogo(titulo,texto);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }else{
                String titulo="La contraseña debe ser la misma en los dos campos.";
                sacarDialogo(titulo,"");
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(CrearUsuario.this,Login.class);
        startActivity(i);
        finish();
    }

    private void inicializarVariables(){
        //BUTTON
        btnCrear = findViewById(R.id.btnCrear);
        //EDITTEXT
        etNombreUsuario = findViewById(R.id.etNombreUsuario);
        etNombre = findViewById(R.id.etNombre);
        etApellidos = findViewById(R.id.etApellidos);
        etCorreo = findViewById(R.id.etCorreo);
        etContraseña = findViewById(R.id.etContraseña);
        etContraseña2 = findViewById(R.id.etContraseña2);
        //ONCLICKLISTENER
        btnCrear.setOnClickListener(this);

    }
    private boolean checkEmail (String email) {
        Pattern p = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher m = p.matcher(email);
        return m.matches();
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
