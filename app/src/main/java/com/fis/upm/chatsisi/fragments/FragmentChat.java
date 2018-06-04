package com.fis.upm.chatsisi.fragments;

import java.sql.SQLException;
import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.fis.upm.chatsisi.R;
import com.fis.upm.chatsisi.SharedPreferences.GestorSharedPreferences;
import com.fis.upm.chatsisi.adapters.AdaptadorListaChat;
import com.fis.upm.chatsisi.clases.CommonMethods;
import com.fis.upm.chatsisi.daos.ChatDAO;
import com.fis.upm.chatsisi.daos.MensajeDAO;
import com.fis.upm.chatsisi.entities.Chat;
import com.fis.upm.chatsisi.entities.Mensaje;

import org.json.JSONException;
import org.json.JSONObject;

public class FragmentChat extends Fragment implements OnClickListener {

    private EditText msg_edittext;
    private static ArrayList<Mensaje> chatlist = new ArrayList<Mensaje>();
    private static AdaptadorListaChat adaptadorListaChat;
    private ListView msgListView;
    private View view;
    private int idUsuario,idRecibe;
    private Chat chat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chat, container, false);
        inicializarVariables();
        // ----Set autoscroll of listview when a new message arrives----//
        msgListView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        msgListView.setStackFromBottom(true);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = GestorSharedPreferences.getJsonUsuario(GestorSharedPreferences.getSharedPreferencesUsuario(getContext()));
            idUsuario = jsonObject.getInt("id");
            Bundle args = getArguments();
            idRecibe = args.getInt("id");
            chat = ChatDAO.buscarChatPorFkEnvioFkRecibe(getContext(),idUsuario,idRecibe);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            chatlist.clear();
            if (MensajeDAO.buscarMensajePorFkChat(getContext(),chat.getId_chat())!=null){
                chatlist.addAll(MensajeDAO.buscarMensajePorFkChat(getContext(),chat.getId_chat()));

            }
            adaptadorListaChat = new AdaptadorListaChat(getActivity(), chatlist);
            msgListView.setAdapter(adaptadorListaChat);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
    }

    private void inicializarVariables(){
        msg_edittext = (EditText) view.findViewById(R.id.messageEditText);
        msgListView = (ListView) view.findViewById(R.id.msgListView);
        ImageButton sendButton = (ImageButton) view.findViewById(R.id.sendMessageButton);
        sendButton.setOnClickListener(this);
    }
    public void sendTextMessage(View v) {
        String message = msg_edittext.getEditableText().toString();
        if (!message.equalsIgnoreCase("")) {
            Mensaje mensaje = new Mensaje(chat.getId_chat(),idUsuario,message,CommonMethods.getCurrentDate(),CommonMethods.getCurrentTime(),CommonMethods.getCurrentMilis());
            mensaje.setCuerpo(message);
            mensaje.setFecha(CommonMethods.getCurrentDate());
            mensaje.setHora(CommonMethods.getCurrentTime());
            MensajeDAO.newMensaje(getContext(),chat.getId_chat(),mensaje.getFk_usuario(),mensaje.getCuerpo(),mensaje.getFecha(),mensaje.getHora(),mensaje.getMili());
            msg_edittext.setText("");
            adaptadorListaChat.add(mensaje);
            adaptadorListaChat.notifyDataSetChanged();

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sendMessageButton:
                sendTextMessage(v);

        }
    }

}