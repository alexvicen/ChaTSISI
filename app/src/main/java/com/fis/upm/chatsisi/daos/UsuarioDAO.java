package com.fis.upm.chatsisi.daos;

import android.content.Context;

import com.fis.upm.chatsisi.dbhelper.DBHelperMOS;
import com.fis.upm.chatsisi.entities.Usuario;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;

import java.sql.SQLException;
import java.util.List;

public class UsuarioDAO extends DBHelperMOS {
	static Dao<Usuario, Integer> dao;

	public static void cargarDao(Context context) throws SQLException {
		dao = getHelper(context).getUsuarioDAO();
	}

	//__________FUNCIONES DE CREACIÓN________________________//

	public static boolean newUsuario(Context context) {
		Usuario t = montarUsuario();
		return crearUsuario(t,context);
	}
	public static boolean crearUsuario(Usuario t,Context context) {
		try {
			cargarDao(context);
			dao.create(t);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static Usuario montarUsuario() {
		Usuario u =new Usuario();
		return u;
	}

	//__________FUNCIONES DE BORRADO______________________//

	public static void borrarTodosLosUsuarios(Context context) throws SQLException {
		cargarDao(context);
		DeleteBuilder<Usuario, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.delete();
	}
	public static void borrarUsuarioPorID(Context context, int id) throws SQLException {
		cargarDao(context);
		DeleteBuilder<Usuario, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.where().eq(Usuario.ID_USUARIO, id);
		deleteBuilder.delete();
	}

	//__________FUNCIONES DE BUSQUEDA______________________//


	public static List<Usuario> buscarTodosLosUsuarios(Context context) throws SQLException {
		cargarDao(context);
		List<Usuario> listadoUsuario= dao.queryForAll();
		if(listadoUsuario.isEmpty()) {
			return null;
		}else{
			return listadoUsuario;
		}
	}
	public static Usuario buscarUsuarioPorId(Context context, int id) throws SQLException {
		cargarDao(context);
		List<Usuario> listadoUsuario= dao.queryForEq(Usuario.ID_USUARIO, id);
		if(listadoUsuario.isEmpty()) {
			return null;
		}else{
			return listadoUsuario.get(0);
		}
	}


	//____________________________FUNCIONES DE ACTUALIZAR_________________________________________//



}
