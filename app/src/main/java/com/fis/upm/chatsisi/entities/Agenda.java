package com.fis.upm.chatsisi.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "agendas")
public class Agenda {

    public static final String ID_AGENDA = "_id_agenda";
    public static final String NOMBRE_AGENDA = "nombre_agenda";

    @DatabaseField(generatedId = true, columnName = ID_AGENDA)  private int id_agenda;
    @DatabaseField(columnName = NOMBRE_AGENDA)          private String nombre_agenda;

    public Agenda(){}
    public Agenda(String nombre_agenda) {
        this.nombre_agenda = nombre_agenda;
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
}
