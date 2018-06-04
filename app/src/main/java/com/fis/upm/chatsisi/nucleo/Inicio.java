package com.fis.upm.chatsisi.nucleo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.fis.upm.chatsisi.R;
import com.fis.upm.chatsisi.SharedPreferences.GestorSharedPreferences;
import com.fis.upm.chatsisi.daos.AgendaDAO;
import com.fis.upm.chatsisi.daos.ChatDAO;
import com.fis.upm.chatsisi.daos.UsuarioDAO;
import com.fis.upm.chatsisi.entities.Agenda;
import com.fis.upm.chatsisi.entities.Chat;
import com.fis.upm.chatsisi.fragments.FragmentAgenda;
import com.fis.upm.chatsisi.fragments.FragmentChat;
import com.fis.upm.chatsisi.fragments.FragmentAnadirAgenda;
import com.fis.upm.chatsisi.fragments.FragmentPerfil;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class Inicio extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, AdapterView.OnItemClickListener {
    private RelativeLayout cuerpo;
    private Class fragmentClass;
    private Toolbar toolbar;
    private ArrayAdapter arrayAdapter;
    private FloatingActionButton fab;
    private int idUsuario;
    private ListView lvGeneral;
    private ArrayList<String> nombres = new ArrayList<>();
    private int tipo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = GestorSharedPreferences.getJsonUsuario(GestorSharedPreferences.getSharedPreferencesUsuario(this));
            idUsuario = jsonObject.getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1 = GestorSharedPreferences.getJsonTipo(GestorSharedPreferences.getSharedPreferencesTipo(this));
            tipo = jsonObject1.getInt("tipo");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        inicializarVariables();
        darValorVariables();

        if (tipo == 1) {
            toolbar.setTitle("Mis Agendas");
            fab.setVisibility(View.VISIBLE);
            try {
                if (AgendaDAO.buscarAgendaPorFkUsuario(this, idUsuario) == null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentAnadirAgenda newFragment = new FragmentAnadirAgenda();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    transaction.add(android.R.id.content, newFragment, "FullScreenFragment").commit();
                } else {
                    rellenarListAgendas(this, idUsuario);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (tipo == 2) {
            toolbar.setTitle("Mis Chats");
            setSupportActionBar(toolbar);
            fab.setVisibility(View.GONE);
            rellenarListChat(this, idUsuario);
        }
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void recreate() {
        Intent i = new Intent(this, Inicio.class);
        startActivity(i);
        finish();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.agendas) {
            JSONObject jsonObject1 = new JSONObject();
            try {
                jsonObject1.put("tipo", 1);
                GestorSharedPreferences.setJsonTipo(GestorSharedPreferences.getSharedPreferencesTipo(this), jsonObject1);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            recreate();
        } else if (id == R.id.chats) {
            JSONObject jsonObject1 = new JSONObject();
            try {
                jsonObject1.put("tipo", 2);
                GestorSharedPreferences.setJsonTipo(GestorSharedPreferences.getSharedPreferencesTipo(this), jsonObject1);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            recreate();
        } else if (id == R.id.perfil) {
            irPerfil(idUsuario);
        } else if (id == R.id.cerrar_sesion) {
            GestorSharedPreferences.clearSharedPreferencesUsuario(this);
            Intent i = new Intent(this, Login.class);
            startActivity(i);
            finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (fragmentClass != null) {
            recreate();
        } else {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("¿Desea salir de la aplicacion?");
            builder1.setCancelable(true);
            builder1.setPositiveButton(
                    "Aceptar",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Inicio.super.onBackPressed();
                            dialog.cancel();
                        }
                    });
            builder1.setNegativeButton(
                    "Cancelar",
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

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.fab) {
            try {
                if (toolbar.getTitle().toString().equals("Mis Agendas")) {
                    fragmentClass = FragmentAnadirAgenda.class;
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    Fragment fragment = null;
                    fragment = (Fragment) fragmentClass.newInstance();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    transaction.add(android.R.id.content, fragment).commit();
                } else if (toolbar.getTitle().toString().equals("Mis Chats")) {

                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public void rellenarListAgendas(Context context, int id) {
        try {
            if (AgendaDAO.buscarAgendaPorFkUsuario(context, id) != null) {
                nombres.clear();
                ArrayList<Agenda> agendas = new ArrayList<>();
                agendas.addAll(AgendaDAO.buscarAgendaPorFkUsuario(context, id));
                nombres = new ArrayList<>();
                for (int i = 0; i < agendas.size(); i++) {
                    nombres.add(agendas.get(i).getNombre_agenda());
                }
                ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, nombres);
                lvGeneral.setAdapter(itemsAdapter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rellenarListChat(Context context, int id) {
        try {
            if (ChatDAO.buscarChatPorFkUsuario(context, id) != null) {
                nombres.clear();
                ArrayList<Chat> chats = new ArrayList<>();
                chats.addAll(ChatDAO.buscarChatPorFkUsuario(context, id));
                nombres = new ArrayList<>();
                for (int i = 0; i < chats.size(); i++) {
                    nombres.add(UsuarioDAO.buscarUsuarioPorId(context, chats.get(i).getFk_usuario_recibe()).getLogin_usuario());
                }
                ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, nombres);
                lvGeneral.setAdapter(itemsAdapter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void inicializarVariables() {
        toolbar = findViewById(R.id.toolbar);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);
        cuerpo = findViewById(R.id.cuerpo);
        lvGeneral = findViewById(R.id.lvGeneral);
        lvGeneral.setOnItemClickListener(this);
    }

    private void darValorVariables() {
    }

    public void irPerfil(int id) {
        cuerpo.removeAllViews();
        Bundle arguments = new Bundle();
        arguments.putString("id", String.valueOf(id));
        fragmentClass = FragmentPerfil.class;
        Fragment fragment;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
            fragment.setArguments(arguments);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.cuerpo, fragment).commit();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void irChat(int id) {
        fab.setVisibility(View.GONE);
        cuerpo.removeAllViews();
        Bundle arguments = new Bundle();
        arguments.putInt("id", id);
        fragmentClass = FragmentChat.class;
        Fragment fragment;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
            fragment.setArguments(arguments);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.cuerpo, fragment).commit();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void irAgenda(int id) {
        try {
            cuerpo.removeAllViews();
            Bundle arguments = new Bundle();
            arguments.putInt("id", id);
            fragmentClass = FragmentAgenda.class;
            Fragment fragment;
            fragment = (Fragment) fragmentClass.newInstance();
            fragment.setArguments(arguments);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.cuerpo, fragment).commit();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (toolbar.getTitle().toString().equals("Mis Agendas")) {
            try {
                irAgenda(AgendaDAO.buscarAgendaPorNombre(this, nombres.get(i)).getId_agenda());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (toolbar.getTitle().toString().equals("Mis Chats")) {
            try {
                irChat(UsuarioDAO.buscarUsuarioPorLogin(this, nombres.get(i)).getId_usuario());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
