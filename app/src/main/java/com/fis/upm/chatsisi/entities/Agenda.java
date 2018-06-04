package com.fis.upm.chatsisi.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "agendas")
public class Agenda {

    public static final String ID_AGENDA = "_id_agenda";
    public static final String NOMBRE_AGENDA = "nombre_agenda";
    public static final String FK_USUARIO = "fk_usuario";
    public static final String B_FAVORITA = "b_favorita";

    @DatabaseField(generatedId = true, columnName = ID_AGENDA)  private int id_agenda;
    @DatabaseField(columnName = NOMBRE_AGENDA)                  private String nombre_agenda;
    @DatabaseField(columnName = FK_USUARIO)                     private int fk_usuario;
    @DatabaseField(columnName = B_FAVORITA)                     private boolean b_favorita;

    public Agenda(){}
    public Agenda(String nombre_agenda, int fk_usuario,boolean b_favorita) {
        this.nombre_agenda = nombre_agenda;
        this.fk_usuario = fk_usuario;
        this.b_favorita = b_favorita;
    }

    public int getId_agenda() {
        return id_agenda;
    }
    public String getNombre_agenda() {
        return nombre_agenda;
    }
    public void setNombre_agenda(String nombre_agenda) {
        this.nombre_agenda = nombre_agenda;
    }
    public int getFk_usuario() {
        return fk_usuario;
    }
    public void setFk_usuario(int fk_usuario) {
        this.fk_usuario = fk_usuario;
    }
    public boolean isB_favorita() {
        return b_favorita;
    }
    public void setB_favorita(boolean b_favorita) {
        this.b_favorita = b_favorita;
    }
}
