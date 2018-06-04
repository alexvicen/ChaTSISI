package com.fis.upm.chatsisi.constants;

import android.content.Context;

import com.fis.upm.chatsisi.daos.AgendaDAO;
import com.fis.upm.chatsisi.daos.AgendaUsuarioDAO;
import com.fis.upm.chatsisi.daos.ChatDAO;
import com.fis.upm.chatsisi.daos.MensajeDAO;
import com.fis.upm.chatsisi.daos.UsuarioDAO;
import com.fis.upm.chatsisi.entities.Agenda;
import com.fis.upm.chatsisi.entities.AgendaUsuario;
import com.fis.upm.chatsisi.entities.Chat;
import com.fis.upm.chatsisi.entities.Mensaje;
import com.fis.upm.chatsisi.entities.Usuario;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class BBDDConstantes {

	public static final String DATABASE_NAME = "chatsisi.db";
	public static final int DATABASE_VERSION = 1;

	public static Dao<Usuario, Integer> usuarioDao;
	public static Dao<Agenda, Integer> agendaDao;
	public static Dao<Chat, Integer> chatDao;
	public static Dao<AgendaUsuario, Integer> agendaUsuarioDao;
	public static Dao<Mensaje, Integer> mensajeDao;

    public static void cerrarDao() {
		usuarioDao = null;
		agendaDao = null;
		chatDao = null;
		agendaUsuarioDao = null;
		mensajeDao = null;

	}

	public static void crearTablas(ConnectionSource connectionSource) throws SQLException {
		TableUtils.createTable(connectionSource, Usuario.class);
		TableUtils.createTable(connectionSource, Agenda.class);
		TableUtils.createTable(connectionSource, Chat.class);
		TableUtils.createTable(connectionSource, AgendaUsuario.class);
		TableUtils.createTable(connectionSource, Mensaje.class);

	}

	public static void borrarTablas(ConnectionSource connectionSource) throws SQLException {
		TableUtils.dropTable(connectionSource, Usuario.class, true);
		TableUtils.dropTable(connectionSource, Agenda.class, true);
		TableUtils.dropTable(connectionSource, Chat.class, true);
		TableUtils.dropTable(connectionSource, AgendaUsuario.class, true);
		TableUtils.dropTable(connectionSource, Mensaje.class, true);

	}

	public static void borrarDatosTablas(Context context) throws SQLException {
		UsuarioDAO.borrarTodosLosUsuarios(context);
		AgendaDAO.borrarTodosLosAgendas(context);
		ChatDAO.borrarTodosLosChats(context);
		AgendaUsuarioDAO.borrarTodosLosAgendaUsuarios(context);
		MensajeDAO.borrarTodosLosMensajes(context);
    }
}
