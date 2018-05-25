package com.fis.upm.chatsisi.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "usaurios")
public class Usuario {

    public static final String ID_USUARIO = "_id_usuario";
    public static final String LOGIN_USUARIO = "login_usuario";
    public static final String CONTRASEÑA_USUARIO = "contraseña_usuario";
    public static final String ALIAS_USUARIO = "alias_usuario";
    public static final String ESTADO_USUARIO = "estado_usuario";
    public static final String DESCRIPCION_USUARIO = "descripcion_usuario";

    @DatabaseField(id = true, columnName = ID_USUARIO)  private int id_usuario;
    @DatabaseField(columnName = LOGIN_USUARIO)          private String login_usuario;
    @DatabaseField(columnName = CONTRASEÑA_USUARIO)     private String contraseña_usuario;
    @DatabaseField(columnName = ALIAS_USUARIO)          private String alias_usuario;
    @DatabaseField(columnName = ESTADO_USUARIO)         private String estado_usuario;
    @DatabaseField(columnName = DESCRIPCION_USUARIO)    private String descripcion_usuario;


    public Usuario() {
    }
    public Usuario(int id_usuario, String login_usuario, String contraseña_usuario, String alias_usuario, String estado_usuario, String descripcion_usuario) {
        this.id_usuario = id_usuario;
        this.login_usuario = login_usuario;
        this.contraseña_usuario = contraseña_usuario;
        this.alias_usuario = alias_usuario;
        this.estado_usuario = estado_usuario;
        this.descripcion_usuario = descripcion_usuario;
    }

    public int getId_usuario() {
        return id_usuario;
    }
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    public String getLogin_usuario() {
        return login_usuario;
    }
    public void setLogin_usuario(String login_usuario) {
        this.login_usuario = login_usuario;
    }
    public String getContraseña_usuario() {
        return contraseña_usuario;
    }
    public void setContraseña_usuario(String contraseña_usuario) {
        this.contraseña_usuario = contraseña_usuario;
    }
    public String getAlias_usuario() {
        return alias_usuario;
    }
    public void setAlias_usuario(String alias_usuario) {
        this.alias_usuario = alias_usuario;
    }
    public String getEstado_usuario() {
        return estado_usuario;
    }
    public void setEstado_usuario(String estado_usuario) {
        this.estado_usuario = estado_usuario;
    }
    public String getDescripcion_usuario() {
        return descripcion_usuario;
    }
    public void setDescripcion_usuario(String descripcion_usuario) {
        this.descripcion_usuario = descripcion_usuario;
    }
}
