package com.fis.upm.chatsisi.fragments;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.fis.upm.chatsisi.R;
import com.fis.upm.chatsisi.SharedPreferences.GestorSharedPreferences;
import com.fis.upm.chatsisi.daos.UsuarioDAO;
import com.fis.upm.chatsisi.entities.Usuario;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.sql.SQLException;

public class FragmentPerfil extends Fragment {
    private View vista;
    private ImageView ivFoto;
    private EditText etNombre, etApellidos, etEstado, etDescripcion;
    private TextView txtAlias,txtCorreo;
    private int id, idUsuario;
    private Usuario usuario;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_perfil, container, false);
        inicializarVariables();
        Bundle args = getArguments();
        id = args.getInt("id");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = GestorSharedPreferences.getJsonUsuario(GestorSharedPreferences.getSharedPreferencesUsuario(getContext()));
            idUsuario = jsonObject.getInt("id");
            usuario = UsuarioDAO.buscarUsuarioPorId(getContext(), id);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (id != idUsuario) {
            bloquear();
        }
        darValorVariables();
        return vista;
    }

    @Override
    public void onPause() {
        if (id == idUsuario) {
            try {
                if (!etNombre.getText().toString().equals("")) {
                    UsuarioDAO.actualizarNombre(getContext(), etNombre.getText().toString(), usuario.getId_usuario());
                }
                if (!etApellidos.getText().toString().equals("")) {
                    UsuarioDAO.actualizarApellidos(getContext(), etApellidos.getText().toString(), usuario.getId_usuario());
                }
                if (!etEstado.getText().toString().equals("")) {
                    UsuarioDAO.actualizarEstado(getContext(), etEstado.getText().toString(), usuario.getId_usuario());
                }
                if (!etDescripcion.getText().toString().equals("")) {
                    UsuarioDAO.actualizarDescripcion(getContext(), etDescripcion.getText().toString(), usuario.getId_usuario());
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        super.onPause();
    }

    private void inicializarVariables() {
        ivFoto = vista.findViewById(R.id.expandedImage);
        txtAlias = vista.findViewById(R.id.txtAlias);
        txtCorreo = vista.findViewById(R.id.txtCorreo);
        etNombre = vista.findViewById(R.id.etNombre);
        etApellidos = vista.findViewById(R.id.etApellidos);
        etEstado = vista.findViewById(R.id.etEstado);
        etDescripcion = vista.findViewById(R.id.etDescripcion);
    }

    private void darValorVariables() {
        if (!usuario.getLogin_usuario().equals("")) {
            txtAlias.setText(usuario.getLogin_usuario());
        }
        if (!usuario.getCorreo_usuario().equals("")) {
            txtCorreo.setText(usuario.getCorreo_usuario());
        }
        if (!usuario.getNombre_usuario().equals("")) {
            etNombre.setText(usuario.getNombre_usuario());
        }
        if (!usuario.getApellidos_usuario().equals("")) {
            etApellidos.setText(usuario.getApellidos_usuario());
        }
        if (!usuario.getEstado_usuario().equals("")) {
            etEstado.setText(usuario.getEstado_usuario());
        }
        if (!usuario.getDescripcion_usuario().equals("")) {
            etDescripcion.setText(usuario.getDescripcion_usuario());
        }

    }

    private void bloquear() {
        etNombre.setEnabled(false);
        etApellidos.setEnabled(false);
        etEstado.setEnabled(false);
        etDescripcion.setEnabled(false);
    }
}
