package com.fis.upm.chatsisi;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class FragmentPerfil extends Fragment {
    private View vista;
    private ImageView ivFoto;
    private TextView txtAlias,txtEstado,txtDescripcion;
    private int id;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_perfil, container, false);
        inicializarVariables();
        id = getArguments().getInt("id");
        return vista;
    }
    private void inicializarVariables(){
        ivFoto = vista.findViewById(R.id.expandedImage);
        txtAlias = vista.findViewById(R.id.txtAlias);
        txtEstado = vista.findViewById(R.id.txtEstado);
        txtDescripcion = vista.findViewById(R.id.txtDescripcion);
    }
}
