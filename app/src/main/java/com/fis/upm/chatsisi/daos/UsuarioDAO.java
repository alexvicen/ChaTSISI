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

	public static boolean newUsuario(Context context,String login_usuario, String contrasena_usuario, String nombre_usuario, String apellidos_usuario,
									 String correo_usuario, String alias_usuario, String estado_usuario, String descripcion_usuario) {
		Usuario t = montarUsuario(login_usuario, contrasena_usuario, nombre_usuario, apellidos_usuario,
									correo_usuario, alias_usuario, estado_usuario, descripcion_usuario);
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
	public static Usuario montarUsuario(String login_usuario, String contrasena_usuario, String nombre_usuario, String apellidos_usuario,
										String correo_usuario, String alias_usuario, String estado_usuario, String descripcion_usuario) {
		Usuario u =new Usuario(login_usuario, contrasena_usuario, nombre_usuario, apellidos_usuario,
				correo_usuario, alias_usuario, estado_usuario, descripcion_usuario);
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
	public static Usuario buscarUsuarioPorNombreCorreo(Context context, String nombre,String correo) throws SQLException {
		cargarDao(context);
		List<Usuario> listadoUsuario= dao.queryBuilder().where().eq(Usuario.ALIAS_USUARIO,nombre).or().eq(Usuario.CORREO_USUARIO,correo).query();
		if(listadoUsuario.isEmpty()) {
			return null;
		}else{
			return listadoUsuario.get(0);
		}
	}
	public static Usuario validarUsuario(Context context, String nombre,String contrasena) throws SQLException {
		cargarDao(context);
		List<Usuario> listadoUsuario= dao.queryBuilder().where().eq(Usuario.ALIAS_USUARIO,nombre).and().eq(Usuario.CONTRASENA_USUARIO,contrasena).query();
		if(listadoUsuario.isEmpty()) {
			return null;
		}else{
			return listadoUsuario.get(0);
		}
	}


	//____________________________FUNCIONES DE ACTUALIZAR_________________________________________//



}
