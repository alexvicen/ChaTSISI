package com.fis.upm.chatsisi.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.fis.upm.chatsisi.constants.BBDDConstantes;
import com.fis.upm.chatsisi.entities.Agenda;
import com.fis.upm.chatsisi.entities.AgendaUsuario;
import com.fis.upm.chatsisi.entities.Chat;
import com.fis.upm.chatsisi.entities.Mensaje;
import com.fis.upm.chatsisi.entities.Usuario;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;

public class DBHelper extends OrmLiteSqliteOpenHelper {

	private Context context;

	public DBHelper(Context context) {
		super(context, BBDDConstantes.DATABASE_NAME, null, BBDDConstantes.DATABASE_VERSION);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			BBDDConstantes.crearTablas(connectionSource);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			BBDDConstantes.borrarTablas(connectionSource);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		onCreate(db, connectionSource);
	}

	@Override
	public void close() {
		BBDDConstantes.cerrarDao();
	}

	public Dao<Usuario, Integer> getUsuarioDAO() throws SQLException {
		if (BBDDConstantes.usuarioDao == null) {
			BBDDConstantes.usuarioDao = getDao(Usuario.class);
		}
		return BBDDConstantes.usuarioDao;
	}
	public Dao<Agenda, Integer> getAgendaDAO() throws SQLException {
		if (BBDDConstantes.agendaDao == null) {
			BBDDConstantes.agendaDao = getDao(Agenda.class);
		}
		return BBDDConstantes.agendaDao;
	}
	public Dao<Chat, Integer> getChatDAO() throws SQLException {
		if (BBDDConstantes.chatDao == null) {
			BBDDConstantes.chatDao = getDao(Chat.class);
		}
		return BBDDConstantes.chatDao;
	}
	public Dao<AgendaUsuario, Integer> getAgendaUsuarioDAO() throws SQLException {
		if (BBDDConstantes.agendaUsuarioDao == null) {
			BBDDConstantes.agendaUsuarioDao = getDao(AgendaUsuario.class);
		}
		return BBDDConstantes.agendaUsuarioDao;
	}
	public Dao<Mensaje, Integer> getMensajeDAO() throws SQLException {
		if (BBDDConstantes.mensajeDao == null) {
			BBDDConstantes.mensajeDao = getDao(Mensaje.class);
		}
		return BBDDConstantes.mensajeDao;
	}
}
