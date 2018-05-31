package com.fis.upm.chatsisi.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "agenda_usuarios")
public class AgendaUsuario {

    public static final String ID_AGENDA_USUARIO = "_id_agenda_usuario";
    public static final String FK_AGENDA = "fk_agenda";
    public static final String FK_USUARIO = "fk_usuario";

    @DatabaseField(generatedId = true, columnName = ID_AGENDA_USUARIO)  private int id_agenda;
    @DatabaseField(columnName = FK_AGENDA)                              private int fk_agenda;
    @DatabaseField(columnName = FK_USUARIO)                             private int fk_usuario;

    public AgendaUsuario(){}

    public AgendaUsuario(int fk_agenda, int fk_usuario) {
        this.fk_agenda = fk_agenda;
        this.fk_usuario = fk_usuario;
    }

    public int getId_agenda() {
        return id_agenda;
    }
    public int getFk_agenda() {
        return fk_agenda;
    }
    public void setFk_agenda(int fk_agenda) {
        this.fk_agenda = fk_agenda;
    }
    public int getFk_usuario() {
        return fk_usuario;
    }
    public void setFk_usuario(int fk_usuario) {
        this.fk_usuario = fk_usuario;
    }
}
