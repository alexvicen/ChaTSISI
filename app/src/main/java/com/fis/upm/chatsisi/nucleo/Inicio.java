package com.fis.upm.chatsisi.nucleo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.fis.upm.chatsisi.R;
import com.fis.upm.chatsisi.SharedPreferences.GestorSharedPreferences;
import com.fis.upm.chatsisi.daos.UsuarioDAO;
import com.fis.upm.chatsisi.fragments.FragmentPerfil;

import org.json.JSONException;
import org.json.JSONObject;

public class Inicio extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private RelativeLayout cuerpo;
    private Class fragmentClass;
    private  Toolbar toolbar;
    private ListView lvGeneral;
    private ArrayAdapter arrayAdapter;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Mis Agendas");
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        inicializarVariables();
        darValorVariables();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.agendas) {
            toolbar.setTitle("Mis Agendas");
            String[] array = { "FIS","Matematicas","LXXSPXVRES" };
            arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,array);
            lvGeneral.setAdapter(arrayAdapter);
        } else if (id == R.id.chats) {
            toolbar.setTitle("Mis Chats");
            String[] array = { "Javier","Adrian","Miguel","Restituta" };
            arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,array);
            lvGeneral.setAdapter(arrayAdapter);
        } else if (id == R.id.perfil) {
            irPerfil(8);//PONER AQUI EL ID DEL USUARIO AL QUE VAS A ACCEDER AL PERFIL
        } else if (id == R.id.cerrar_sesion) {
            GestorSharedPreferences.clearSharedPreferencesUsuario(this);
            Intent i = new Intent(this,Login.class);
            startActivity(i);
            finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {
        if (fragmentClass!=null){
            fragmentClass=null;
            getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentByTag("perfil")).commit();
        }else{
            super.onBackPressed();
        }

    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.fab){
            if (toolbar.getTitle().toString().equals("Mis Agendas")){

            }else if (toolbar.getTitle().toString().equals("Mis Chats")){

            }
        }
    }
    private void inicializarVariables(){
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);
        cuerpo = findViewById(R.id.cuerpo);
        lvGeneral = findViewById(R.id.lvGeneral);
    }
    private void darValorVariables(){
        String[] array = { "FIS","Matematicas","LXXSPXVRES" };
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,array);
        lvGeneral.setAdapter(arrayAdapter);
    }
    public void irPerfil(int id){
        cuerpo.removeAllViews();
        Bundle arguments = new Bundle();
        arguments.putString("id", String.valueOf(id));
        fragmentClass = FragmentPerfil.class;
        Fragment fragment;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
            fragment.setArguments(arguments);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.cuerpo, fragment,"perfil").commit();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


}
