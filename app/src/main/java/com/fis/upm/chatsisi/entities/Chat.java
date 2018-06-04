package com.fis.upm.chatsisi.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "chats")
public class Chat{

    public static final String ID_CHAT = "_id_chat";
    public static final String FECHA_INICIO = "fecha_inicio";
    public static final String FK_USUARIO_ENVIA = "fk_usuario_envia";
    public static final String FK_USUARIO_RECIBE = "fk_usuario_recibe";


    @DatabaseField(generatedId = true, columnName = ID_CHAT)    private int id_chat;
    @DatabaseField(columnName = FECHA_INICIO)                   private String fecha_inicio;
    @DatabaseField(columnName = FK_USUARIO_ENVIA)               private int fk_usuario_envia;
    @DatabaseField(columnName = FK_USUARIO_RECIBE)              private int fk_usuario_recibe;

    public Chat(){}

    public Chat(String fecha_inicio, int fk_usuario_envia, int fk_usuario_recibe) {
        this.fecha_inicio = fecha_inicio;
        this.fk_usuario_envia = fk_usuario_envia;
        this.fk_usuario_recibe = fk_usuario_recibe;
    }

    public int getId_chat() {
        return id_chat;
    }
    public String getFecha_inicio() {
        return fecha_inicio;
    }
    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }
    public int getFk_usuario_envia() {
        return fk_usuario_envia;
    }
    public void setFk_usuario_envia(int fk_usuario_envia) {
        this.fk_usuario_envia = fk_usuario_envia;
    }
    public int getFk_usuario_recibe() {
        return fk_usuario_recibe;
    }
    public void setFk_usuario_recibe(int fk_usuario_recibe) {
        this.fk_usuario_recibe = fk_usuario_recibe;
    }
}
