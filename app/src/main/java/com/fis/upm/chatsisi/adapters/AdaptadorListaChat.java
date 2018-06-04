package com.fis.upm.chatsisi.adapters;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fis.upm.chatsisi.R;
import com.fis.upm.chatsisi.SharedPreferences.GestorSharedPreferences;
import com.fis.upm.chatsisi.entities.Mensaje;

import org.json.JSONException;
import org.json.JSONObject;

public class AdaptadorListaChat extends BaseAdapter {

    private static LayoutInflater inflater = null;
    private int idUsuario;
    ArrayList<Mensaje> chatMessageList;
    public AdaptadorListaChat(Activity activity, ArrayList<Mensaje> list) {
        chatMessageList = list;
        inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = GestorSharedPreferences.getJsonUsuario(GestorSharedPreferences.getSharedPreferencesUsuario(activity));
            idUsuario = jsonObject.getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getCount() {
        return chatMessageList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Mensaje message = (Mensaje) chatMessageList.get(position);
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.chatbubble, null);

        TextView msg = vi.findViewById(R.id.message_text);
        msg.setText(message.getCuerpo());
        TextView fecha =  vi.findViewById(R.id.txtFecha);
        fecha.setText(message.getFecha()+" "+message.getHora());
        LinearLayout layout = vi.findViewById(R.id.bubble_layout);
        LinearLayout parent_layout = vi.findViewById(R.id.bubble_layout_parent);
        if (message.getFk_usuario()==idUsuario) {
            layout.setBackgroundResource(R.drawable.bubble2);
            parent_layout.setGravity(Gravity.RIGHT);
        } else {
            layout.setBackgroundResource(R.drawable.bubble1);
            parent_layout.setGravity(Gravity.LEFT);
        }
        msg.setTextColor(Color.BLACK);
        return vi;
    }

    public void add(Mensaje object) {
        chatMessageList.add(object);
    }
}