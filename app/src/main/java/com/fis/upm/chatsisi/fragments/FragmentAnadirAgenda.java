package com.fis.upm.chatsisi.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;

import com.fis.upm.chatsisi.R;
import com.fis.upm.chatsisi.daos.AgendaDAO;
import com.fis.upm.chatsisi.entities.Agenda;

import java.sql.SQLException;

public class FragmentAnadirAgenda extends DialogFragment {

    private EditText etNombreAgenda;
    private View vista;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_anadir_agenda, container, false);
        String title = "FullScreen";
        Toolbar toolbar = (Toolbar) vista.findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_close);
        }
        setHasOptionsMenu(true);
        inicializarVariables();
        return vista;
    }

    private void inicializarVariables() {
        etNombreAgenda = vista.findViewById(R.id.etNombreAgenda);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.save_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.save) {
            if (comprobarDatos()){
                AgendaDAO.newAgenda(getContext(),etNombreAgenda.getText().toString());
            }
            return true;
        } else if (id == android.R.id.home) {
            dismiss();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean comprobarDatos() {
        Boolean result = true;
        try {
            if (etNombreAgenda.getText().toString().equals("")||AgendaDAO.buscarAgendaPorNombre(getContext(),etNombreAgenda.getText().toString())!=null){
                result = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}