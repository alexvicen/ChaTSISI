package com.fis.upm.chatsisi.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "mensajes")
public class Mensaje {

    public static final String ID_MENSAJE = "_id_mensaje";
    public static final String FK_CHAT = "fk_chat";
    public static final String FK_USUARIO = "fk_usuario";
    public static final String CUERPO = "cuerpo";
    public static final String FECHA = "fecha";
    public static final String HORA = "hora";
    public static final String MILI = "mili";

    @DatabaseField(generatedId = true, columnName = ID_MENSAJE) private int id_mensaje;
    @DatabaseField(columnName = FK_CHAT)                        private int fk_chat;
    @DatabaseField(columnName = FK_USUARIO)                     private int fk_usuario;
    @DatabaseField(columnName = CUERPO)                         private String cuerpo;
    @DatabaseField(columnName = FECHA)                          private String fecha;
    @DatabaseField(columnName = HORA)                           private String hora;
    @DatabaseField(columnName = MILI)                           private String mili;

    public Mensaje() {
    }

    public Mensaje(int fk_chat, int fk_usuario, String cuerpo, String fecha, String hora, String mili) {
        this.fk_chat = fk_chat;
        this.fk_usuario = fk_usuario;
        this.cuerpo = cuerpo;
        this.fecha = fecha;
        this.hora = hora;
        this.mili = mili;
    }

    public int getId_mensaje() {
        return id_mensaje;
    }

    public int getFk_chat() {
        return fk_chat;
    }

    public void setFk_chat(int fk_chat) {
        this.fk_chat = fk_chat;
    }

    public int getFk_usuario() {
        return fk_usuario;
    }

    public void setFk_usuario(int fk_usuario) {
        this.fk_usuario = fk_usuario;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getMili() {
        return mili;
    }

    public void setMili(String mili) {
        this.mili = mili;
    }
}
