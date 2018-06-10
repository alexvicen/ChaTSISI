package com.fis.upm.chatsisi.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "usuarios")
public class Usuario {

    public static final String ID_USUARIO = "_id_usuario";
    public static final String LOGIN_USUARIO = "login_usuario";
    public static final String CONTRASENA_USUARIO = "contrasena_usuario";
    public static final String NOMBRE_USUARIO = "nombre_usuario";
    public static final String APELLIDOS_USUARIO = "apellidos_usuario";
    public static final String CORREO_USUARIO = "correo_usuario";
    public static final String ESTADO_USUARIO = "estado_usuario";
    public static final String DESCRIPCION_USUARIO = "descripcion_usuario";

    @DatabaseField(generatedId = true, columnName = ID_USUARIO)  private int id_usuario;
    @DatabaseField(columnName = LOGIN_USUARIO)          private String login_usuario;
    @DatabaseField(columnName = CONTRASENA_USUARIO)     private String contrasena_usuario;
    @DatabaseField(columnName = NOMBRE_USUARIO)     private String nombre_usuario;
    @DatabaseField(columnName = APELLIDOS_USUARIO)     private String apellidos_usuario;
    @DatabaseField(columnName = CORREO_USUARIO)     private String correo_usuario;
    @DatabaseField(columnName = ESTADO_USUARIO)         private String estado_usuario;
    @DatabaseField(columnName = DESCRIPCION_USUARIO)    private String descripcion_usuario;

    public Usuario(){}
    public Usuario(String login_usuario, String contrasena_usuario, String nombre_usuario, String apellidos_usuario,
                   String correo_usuario, String estado_usuario, String descripcion_usuario) {

        this.login_usuario = login_usuario;
        this.contrasena_usuario = contrasena_usuario;
        this.nombre_usuario = nombre_usuario;
        this.apellidos_usuario = apellidos_usuario;
        this.correo_usuario = correo_usuario;
        this.estado_usuario = estado_usuario;
        this.descripcion_usuario = descripcion_usuario;
    }

    public int getId_usuario() {
        return id_usuario;
    }
    public String getLogin_usuario() {
        return login_usuario;
    }
    public void setLogin_usuario(String login_usuario) {
        this.login_usuario = login_usuario;
    }
    public String getContrasena_usuario() {
        return contrasena_usuario;
    }
    public void setContrasena_usuario(String contrasena_usuario) {
        this.contrasena_usuario = contrasena_usuario;
    }
    public String getNombre_usuario() {
        return nombre_usuario;
    }
    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }
    public String getApellidos_usuario() {
        return apellidos_usuario;
    }
    public void setApellidos_usuario(String apellidos_usuario) {
        this.apellidos_usuario = apellidos_usuario;
    }
    public String getCorreo_usuario() {
        return correo_usuario;
    }
    public void setCorreo_usuario(String correo_usuario) {
        this.correo_usuario = correo_usuario;
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
