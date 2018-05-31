package com.fis.upm.chatsisi.daos;

import android.content.Context;

import com.fis.upm.chatsisi.dbhelper.DBHelperMOS;
import com.fis.upm.chatsisi.entities.Agenda;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;

import java.sql.SQLException;
import java.util.List;

public class AgendaDAO extends DBHelperMOS {
	static Dao<Agenda, Integer> dao;

	public static void cargarDao(Context context) throws SQLException {
		dao = getHelper(context).getAgendaDAO();
	}

	//__________FUNCIONES DE CREACIÓN________________________//

	public static boolean newAgenda(Context context,String nombre_agenda) {
		Agenda t = montarAgenda(nombre_agenda);
		return crearAgenda(t,context);
	}
	public static boolean crearAgenda(Agenda t,Context context) {
		try {
			cargarDao(context);
			dao.create(t);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static Agenda montarAgenda(String nombre_agenda) {
		Agenda u =new Agenda(nombre_agenda);
		return u;
	}

	//__________FUNCIONES DE BORRADO______________________//

	public static void borrarTodosLosAgendas(Context context) throws SQLException {
		cargarDao(context);
		DeleteBuilder<Agenda, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.delete();
	}
	public static void borrarAgendaPorID(Context context, int id) throws SQLException {
		cargarDao(context);
		DeleteBuilder<Agenda, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.where().eq(Agenda.ID_AGENDA, id);
		deleteBuilder.delete();
	}

	//__________FUNCIONES DE BUSQUEDA______________________//


	public static List<Agenda> buscarTodosLosAgendas(Context context) throws SQLException {
		cargarDao(context);
		List<Agenda> listadoAgenda= dao.queryForAll();
		if(listadoAgenda.isEmpty()) {
			return null;
		}else{
			return listadoAgenda;
		}
	}
	public static Agenda buscarAgendaPorId(Context context, int id) throws SQLException {
		cargarDao(context);
		List<Agenda> listadoAgenda= dao.queryForEq(Agenda.ID_AGENDA, id);
		if(listadoAgenda.isEmpty()) {
			return null;
		}else{
			return listadoAgenda.get(0);
		}
	}


	//____________________________FUNCIONES DE ACTUALIZAR_________________________________________//



}
