package com.fis.upm.chatsisi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fis.upm.chatsisi.R;
import com.fis.upm.chatsisi.SharedPreferences.GestorSharedPreferences;
import com.fis.upm.chatsisi.clases.CommonMethods;
import com.fis.upm.chatsisi.daos.ChatDAO;
import com.fis.upm.chatsisi.entities.Chat;
import com.fis.upm.chatsisi.entities.Usuario;
import com.fis.upm.chatsisi.nucleo.Inicio;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;


public class AdaptadorListaUsuario extends ArrayAdapter implements View.OnClickListener {
    //VARIABLES GLOBALES
    private Context context;
    private int view;
    private ArrayList<Usuario> arrayList;
    private int idUsuario;
    //CONSTRUCTOR
    public AdaptadorListaUsuario(Context context, int view, ArrayList<Usuario> arrayList) {
        super(context, view, arrayList);
        this.context = context;
        this.view = view;
        this.arrayList=arrayList;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = GestorSharedPreferences.getJsonUsuario(GestorSharedPreferences.getSharedPreferencesUsuario(getContext()));
            idUsuario = jsonObject.getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //OVERRIDE METHODS
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        if (item == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            item = inflater.inflate(view, null);
        }
        LinearLayout llCuerpo = item.findViewById(R.id.llCuerpo);
        TextView txtNombre = item.findViewById(R.id.txtNombre);
        TextView txtCorreo = item.findViewById(R.id.txtCorreo);
        Button btnChat = item.findViewById(R.id.btnChat);
        llCuerpo.setOnClickListener(this);
        llCuerpo.setTag(arrayList.get(position).getId_usuario());
        btnChat.setOnClickListener(this);
        btnChat.setTag(arrayList.get(position).getId_usuario());
        txtNombre.setText(arrayList.get(position).getNombre_usuario());
        txtCorreo.setText(arrayList.get(position).getCorreo_usuario());

        return item;
    }
    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.llCuerpo){
            ((Inicio)context).irPerfil(Integer.parseInt(String.valueOf(v.getTag())));
        }else if(v.getId()==R.id.btnChat){
            try {
                if (ChatDAO.buscarChatPorFkEnvioFkRecibe(context,idUsuario,Integer.parseInt(String.valueOf(v.getTag())))!=null||ChatDAO.buscarChatPorFkEnvioFkRecibe(context,Integer.parseInt(String.valueOf(v.getTag())),idUsuario)!=null){
                    ((Inicio)context).irChat(Integer.parseInt(String.valueOf(v.getTag())));
                }else{
                    String fecha = CommonMethods.getCurrentDate();
                    Chat c = ChatDAO.newChatRet(context,fecha,idUsuario,Integer.parseInt(String.valueOf(v.getTag())));
                    ((Inicio)context).irChat(c.getFk_usuario_recibe());
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
