package com.fis.upm.chatsisi.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.fis.upm.chatsisi.R;
import com.fis.upm.chatsisi.SharedPreferences.GestorSharedPreferences;
import com.fis.upm.chatsisi.adapters.AdaptadorListaUsuario;
import com.fis.upm.chatsisi.daos.AgendaDAO;
import com.fis.upm.chatsisi.daos.AgendaUsuarioDAO;
import com.fis.upm.chatsisi.daos.UsuarioDAO;
import com.fis.upm.chatsisi.entities.Agenda;
import com.fis.upm.chatsisi.entities.AgendaUsuario;
import com.fis.upm.chatsisi.entities.Usuario;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FragmentAgenda extends Fragment {
    private View vista;
    private int idAgenda,idUsuario;
    private ListView lvUsuarios;
    private Agenda agenda;
    private AdaptadorListaUsuario adaptadorListaUsuario;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_agenda, container, false);
        inicializarVariables();
        Bundle args = getArguments();
        idAgenda = args.getInt("id");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = GestorSharedPreferences.getJsonUsuario(GestorSharedPreferences.getSharedPreferencesUsuario(getContext()));
            idUsuario = jsonObject.getInt("id");
            agenda = AgendaDAO.buscarAgendaPorId(getContext(),idAgenda);
            darValorVariables();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vista;
    }
    private void inicializarVariables(){
        lvUsuarios = vista.findViewById(R.id.lvUsuarios);
    }
    private void darValorVariables() throws SQLException {
        List<AgendaUsuario> a = AgendaUsuarioDAO.buscarAgendaUsuarioPorFkAgenda(getContext(),agenda.getId_agenda());
        ArrayList<Integer> ids = new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            ids.add(a.get(i).getFk_usuario());
        }
        ArrayList<Usuario> usuarios = new ArrayList<>();
        usuarios.addAll(UsuarioDAO.buscarUsuariosPorIds(getContext(),ids));
        adaptadorListaUsuario = new AdaptadorListaUsuario(getContext(), R.layout.camp_adapter_list_view_usuarios, usuarios);
        lvUsuarios.setAdapter(adaptadorListaUsuario);

    }
}
