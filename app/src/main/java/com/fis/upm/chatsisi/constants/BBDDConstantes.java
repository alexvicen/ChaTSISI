package com.fis.upm.chatsisi.constants;

import android.content.Context;

import com.fis.upm.chatsisi.daos.UsuarioDAO;
import com.fis.upm.chatsisi.entities.Usuario;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class BBDDConstantes {

	public static final String DATABASE_NAME = "chatsisi.db";
	public static final int DATABASE_VERSION = 1;

	public static Dao<Usuario, Integer> usuarioDao;

    public static void cerrarDao() {
		usuarioDao = null;

	}

	public static void crearTablas(ConnectionSource connectionSource) throws SQLException {
		TableUtils.createTable(connectionSource, Usuario.class);

	}

	public static void borrarTablas(ConnectionSource connectionSource) throws SQLException {
		TableUtils.dropTable(connectionSource, Usuario.class, true);

	}

	public static void borrarDatosTablas(Context context) throws SQLException {
		UsuarioDAO.borrarTodosLosUsuarios(context);
    }
}
