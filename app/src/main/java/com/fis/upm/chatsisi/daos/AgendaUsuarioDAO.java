package com.fis.upm.chatsisi.daos;

import android.content.Context;

import com.fis.upm.chatsisi.dbhelper.DBHelperMOS;
import com.fis.upm.chatsisi.entities.Agenda;
import com.fis.upm.chatsisi.entities.AgendaUsuario;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;

import java.sql.SQLException;
import java.util.List;

public class AgendaUsuarioDAO extends DBHelperMOS {
	static Dao<AgendaUsuario, Integer> dao;

	public static void cargarDao(Context context) throws SQLException {
		dao = getHelper(context).getAgendaUsuarioDAO();
	}

	//__________FUNCIONES DE CREACIÓN________________________//

	public static boolean newAgendaUsuario(Context context,int fk_agenda, int fk_usuario) {
		AgendaUsuario t = montarAgendaUsuario(fk_agenda, fk_usuario);
		return crearAgendaUsuario(t,context);
	}
	public static boolean crearAgendaUsuario(AgendaUsuario t,Context context) {
		try {
			cargarDao(context);
			dao.create(t);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static AgendaUsuario montarAgendaUsuario(int fk_agenda, int fk_usuario) {
		AgendaUsuario u =new AgendaUsuario(fk_agenda, fk_usuario);
		return u;
	}

	//__________FUNCIONES DE BORRADO______________________//

	public static void borrarTodosLosAgendaUsuarios(Context context) throws SQLException {
		cargarDao(context);
		DeleteBuilder<AgendaUsuario, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.delete();
	}
	public static void borrarAgendaUsuarioPorID(Context context, int id) throws SQLException {
		cargarDao(context);
		DeleteBuilder<AgendaUsuario, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.where().eq(AgendaUsuario.ID_AGENDA_USUARIO, id);
		deleteBuilder.delete();
	}

	//__________FUNCIONES DE BUSQUEDA______________________//


	public static List<AgendaUsuario> buscarTodosLosAgendaUsuarios(Context context) throws SQLException {
		cargarDao(context);
		List<AgendaUsuario> listadoAgendaUsuario= dao.queryForAll();
		if(listadoAgendaUsuario.isEmpty()) {
			return null;
		}else{
			return listadoAgendaUsuario;
		}
	}
	public static AgendaUsuario buscarAgendaUsuarioPorId(Context context, int id) throws SQLException {
		cargarDao(context);
		List<AgendaUsuario> listadoAgendaUsuario= dao.queryForEq(AgendaUsuario.ID_AGENDA_USUARIO, id);
		if(listadoAgendaUsuario.isEmpty()) {
			return null;
		}else{
			return listadoAgendaUsuario.get(0);
		}
	}
	public static List<AgendaUsuario> buscarAgendaUsuarioPorFkAgenda(Context context, int id) throws SQLException {
		cargarDao(context);
		List<AgendaUsuario> listadoAgendaUsuario= dao.queryForEq(AgendaUsuario.FK_AGENDA, id);
		if(listadoAgendaUsuario.isEmpty()) {
			return null;
		}else{
			return listadoAgendaUsuario;
		}
	}


	//____________________________FUNCIONES DE ACTUALIZAR_________________________________________//



}
