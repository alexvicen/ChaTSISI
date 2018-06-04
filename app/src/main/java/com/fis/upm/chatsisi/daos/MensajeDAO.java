package com.fis.upm.chatsisi.daos;

import android.content.Context;

import com.fis.upm.chatsisi.dbhelper.DBHelperMOS;
import com.fis.upm.chatsisi.entities.Mensaje;
import com.fis.upm.chatsisi.entities.Mensaje;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;

import java.sql.SQLException;
import java.util.List;

public class MensajeDAO extends DBHelperMOS {
	static Dao<Mensaje, Integer> dao;

	public static void cargarDao(Context context) throws SQLException {
		dao = getHelper(context).getMensajeDAO();
	}

	//__________FUNCIONES DE CREACIÓN________________________//

	public static boolean newMensaje(Context context,int fk_chat, int fk_usuario, String cuerpo, String fecha, String hora, String mili) {
		Mensaje t = montarMensaje(fk_chat, fk_usuario, cuerpo, fecha, hora, mili);
		return crearMensaje(t,context);
	}
	public static boolean crearMensaje(Mensaje t,Context context) {
		try {
			cargarDao(context);
			dao.create(t);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static Mensaje montarMensaje(int fk_chat, int fk_usuario, String cuerpo, String fecha, String hora, String mili) {
		Mensaje u =new Mensaje(fk_chat, fk_usuario, cuerpo, fecha, hora, mili);
		return u;
	}

	//__________FUNCIONES DE BORRADO______________________//

	public static void borrarTodosLosMensajes(Context context) throws SQLException {
		cargarDao(context);
		DeleteBuilder<Mensaje, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.delete();
	}
	public static void borrarMensajePorID(Context context, int id) throws SQLException {
		cargarDao(context);
		DeleteBuilder<Mensaje, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.where().eq(Mensaje.ID_MENSAJE, id);
		deleteBuilder.delete();
	}

	//__________FUNCIONES DE BUSQUEDA______________________//


	public static List<Mensaje> buscarTodosLosMensajes(Context context) throws SQLException {
		cargarDao(context);
		List<Mensaje> listadoMensaje= dao.queryForAll();
		if(listadoMensaje.isEmpty()) {
			return null;
		}else{
			return listadoMensaje;
		}
	}
	public static Mensaje buscarMensajePorId(Context context, int id) throws SQLException {
		cargarDao(context);
		List<Mensaje> listadoMensaje= dao.queryForEq(Mensaje.ID_MENSAJE, id);
		if(listadoMensaje.isEmpty()) {
			return null;
		}else{
			return listadoMensaje.get(0);
		}
	}
	public static List<Mensaje> buscarMensajePorFkChat(Context context, int id) throws SQLException {
		cargarDao(context);
		List<Mensaje> listadoMensaje= dao.queryBuilder().orderBy(Mensaje.MILI,true).where().eq(Mensaje.FK_CHAT, id).query();
		if(listadoMensaje.isEmpty()) {
			return null;
		}else{
			return listadoMensaje;
		}
	}


	//____________________________FUNCIONES DE ACTUALIZAR_________________________________________//



}
