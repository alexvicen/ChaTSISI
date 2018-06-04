package com.fis.upm.chatsisi.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fis.upm.chatsisi.R;
import com.fis.upm.chatsisi.SharedPreferences.GestorSharedPreferences;
import com.fis.upm.chatsisi.daos.AgendaDAO;
import com.fis.upm.chatsisi.daos.AgendaUsuarioDAO;
import com.fis.upm.chatsisi.daos.UsuarioDAO;
import com.fis.upm.chatsisi.entities.Agenda;
import com.fis.upm.chatsisi.entities.Usuario;
import com.fis.upm.chatsisi.nucleo.Inicio;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class FragmentAnadirAgenda extends DialogFragment implements View.OnClickListener {

    private EditText etNombreAgenda;
    private View vista;
    private int idUsuario;
    private LinearLayout llCuerpo;
    private Usuario usuario;
    private ArrayList<Integer> ids = new ArrayList<>();

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
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = GestorSharedPreferences.getJsonUsuario(GestorSharedPreferences.getSharedPreferencesUsuario(getContext()));
            idUsuario = jsonObject.getInt("id");
            usuario = UsuarioDAO.buscarUsuarioPorId(getContext(),idUsuario);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        inicializarVariables();
        darValoresVariables();
        return vista;
    }

    private void darValoresVariables() {
        try {
            ArrayList<Usuario> usuarios = new ArrayList<>();
            if (UsuarioDAO.buscarTodosLosUsuariosMenosId(getContext(),idUsuario)!=null){
                usuarios.addAll(UsuarioDAO.buscarTodosLosUsuariosMenosId(getContext(),idUsuario));
                TextView textView = new TextView(getContext());
                textView.setTextSize(18);
                textView.setTextColor(Color.BLACK);
                textView.setText("Selecciona los usuarios para esta agenda");
                llCuerpo.addView(textView);
                for (int i = 0; i < usuarios.size(); i++) {
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    lp.setMargins(5, 10, 5, 10);
                    LinearLayout ll = new LinearLayout(getContext());
                    ll.setOrientation(LinearLayout.VERTICAL);
                    ll.setLayoutParams(lp);
                    ll.setTag(usuarios.get(i).getId_usuario());
                    ll.setOnClickListener(this);
                    TextView txt = new TextView(getContext());
                    TextView txt2 = new TextView(getContext());
                    txt.setTextSize(20);
                    txt2.setTextSize(16);
                    txt.setTextColor(Color.BLACK);
                    txt2.setTextColor(Color.BLACK);
                    txt.setText(usuarios.get(i).getNombre_usuario()+" "+usuarios.get(i).getApellidos_usuario());
                    txt2.setText(usuarios.get(i).getCorreo_usuario());
                    ll.addView(txt);
                    ll.addView(txt2);
                    llCuerpo.addView(ll);
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void inicializarVariables() {
        etNombreAgenda = vista.findViewById(R.id.etNombreAgenda);
        llCuerpo = vista.findViewById(R.id.llCuerpo);
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
                Agenda a = AgendaDAO.newAgendaRet(getContext(),etNombreAgenda.getText().toString(),idUsuario,false);
                for (int i = 0; i < ids.size(); i++) {
                    AgendaUsuarioDAO.newAgendaUsuario(getContext(),a.getId_agenda(),ids.get(i));
                }
                dismiss();
                ((Inicio)getContext()).rellenarListAgendas(getContext(),idUsuario);
                return true;
            }else{
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                builder1.setMessage("Es necesario un nombre y al menos un contacto para crear la agenda.");
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
                return false;
            }

        } else if (id == android.R.id.home) {
            try {
                if (AgendaDAO.buscarAgendaPorFkUsuario(getContext(),idUsuario)==null){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                    builder1.setMessage("Es necesario tener al menos una agenda para continuar.");
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
                }else{
                    dismiss();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean comprobarDatos() {
        Boolean result = true;
        try {
            if (etNombreAgenda.getText().toString().equals("")||AgendaDAO.buscarAgendaPorNombre(getContext(),etNombreAgenda.getText().toString())!=null||ids.size()==0){
                result = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    private void borrarUsuarioArray(int id){
        for (int i = 0; i < ids.size(); i++) {
            if (ids.get(i)==id){
                ids.remove(i);
            }
        }
    }
    private void anadirUsuarioArray(int id){
        ids.add(id);
    }

    @Override
    public void onClick(View view) {
        int id = Integer.parseInt(String.valueOf(view.getTag()));
        LinearLayout ll = (LinearLayout) view;
        TextView txt = (TextView)ll.getChildAt(0);
        TextView txt2 = (TextView)ll.getChildAt(1);
        if (ll.getBackground()!=null){
            txt.setTextColor(Color.BLACK);
            txt2.setTextColor(Color.BLACK);
            ll.setBackground(null);
            borrarUsuarioArray(id);
        }else{
            txt.setTextColor(Color.WHITE);
            txt2.setTextColor(Color.WHITE);
            ll.setBackground(getContext().getDrawable(R.drawable.fondo_azul));
            anadirUsuarioArray(id);
        }


    }
}