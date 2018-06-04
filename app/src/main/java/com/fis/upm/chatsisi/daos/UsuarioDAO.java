package com.fis.upm.chatsisi.daos;

import android.content.Context;

import com.fis.upm.chatsisi.dbhelper.DBHelperMOS;
import com.fis.upm.chatsisi.entities.Usuario;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends DBHelperMOS {
	static Dao<Usuario, Integer> dao;

	public static void cargarDao(Context context) throws SQLException {
		dao = getHelper(context).getUsuarioDAO();
	}

	//__________FUNCIONES DE CREACIÓN________________________//

	public static boolean newUsuario(Context context,String login_usuario, String contrasena_usuario, String nombre_usuario, String apellidos_usuario,
									 String correo_usuario, String estado_usuario, String descripcion_usuario) {
		Usuario t = montarUsuario(login_usuario, contrasena_usuario, nombre_usuario, apellidos_usuario,
									correo_usuario, estado_usuario, descripcion_usuario);
		return crearUsuario(t,context);
	}
	public static Usuario newUsuarioRet(Context context,String login_usuario, String contrasena_usuario, String nombre_usuario, String apellidos_usuario,
									 String correo_usuario, String estado_usuario, String descripcion_usuario) {
		Usuario t = montarUsuario(login_usuario, contrasena_usuario, nombre_usuario, apellidos_usuario,
									correo_usuario, estado_usuario, descripcion_usuario);
		return crearUsuarioRet(t,context);
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
	public static Usuario crearUsuarioRet(Usuario t,Context context) {
		try {
			cargarDao(context);
			dao.create(t);
			return t;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static Usuario montarUsuario(String login_usuario, String contrasena_usuario, String nombre_usuario, String apellidos_usuario,
										String correo_usuario, String estado_usuario, String descripcion_usuario) {
		Usuario u =new Usuario(login_usuario, contrasena_usuario, nombre_usuario, apellidos_usuario,
				correo_usuario, estado_usuario, descripcion_usuario);
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
		List<Usuario> listadoUsuario= dao.queryBuilder().where().eq(Usuario.LOGIN_USUARIO,nombre).or().eq(Usuario.CORREO_USUARIO,correo).query();
		if(listadoUsuario.isEmpty()) {
			return null;
		}else{
			return listadoUsuario.get(0);
		}
	}
	public static Usuario buscarUsuarioPorLogin(Context context, String nombre) throws SQLException {
		cargarDao(context);
		List<Usuario> listadoUsuario= dao.queryBuilder().where().eq(Usuario.LOGIN_USUARIO,nombre).query();
		if(listadoUsuario.isEmpty()) {
			return null;
		}else{
			return listadoUsuario.get(0);
		}
	}
	public static List<Usuario> buscarTodosLosUsuariosMenosId(Context context, int id) throws SQLException {
		cargarDao(context);
		List<Usuario> listadoUsuario= dao.queryBuilder().where().not().eq(Usuario.ID_USUARIO,id).query();
		if(listadoUsuario.isEmpty()) {
			return null;
		}else{
			return listadoUsuario;
		}
	}
	public static Usuario validarUsuario(Context context, String nombre,String contrasena) throws SQLException {
		cargarDao(context);
		List<Usuario> listadoUsuario= dao.queryBuilder().where().eq(Usuario.LOGIN_USUARIO,nombre).or().eq(Usuario.CORREO_USUARIO,nombre).and().eq(Usuario.CONTRASENA_USUARIO,contrasena).query();
		if(listadoUsuario.isEmpty()) {
			return null;
		}else{
			return listadoUsuario.get(0);
		}
	}
	public static List<Usuario> buscarUsuariosPorIds(Context context, ArrayList<Integer> ids) throws SQLException {
		cargarDao(context);
		List<Usuario> listadoUsuario = dao.queryBuilder().where().in(Usuario.ID_USUARIO,ids).query();
		if (listadoUsuario.isEmpty()) {
			return null;
		} else {
			return listadoUsuario;
		}
	}

	//____________________________FUNCIONES DE ACTUALIZAR_________________________________________//

	public static void actualizarNombre(Context context, String nombre, int id) throws SQLException {
		cargarDao(context);
		UpdateBuilder<Usuario, Integer> updateBuilder = dao.updateBuilder();
		updateBuilder.where().eq(Usuario.ID_USUARIO,id);
		updateBuilder.updateColumnValue(Usuario.NOMBRE_USUARIO,nombre);
		updateBuilder.update();
	}
	public static void actualizarApellidos(Context context, String nombre, int id) throws SQLException {
		cargarDao(context);
		UpdateBuilder<Usuario, Integer> updateBuilder = dao.updateBuilder();
		updateBuilder.where().eq(Usuario.ID_USUARIO,id);
		updateBuilder.updateColumnValue(Usuario.APELLIDOS_USUARIO,nombre);
		updateBuilder.update();
	}
	public static void actualizarEstado(Context context, String nombre, int id) throws SQLException {
		cargarDao(context);
		UpdateBuilder<Usuario, Integer> updateBuilder = dao.updateBuilder();
		updateBuilder.where().eq(Usuario.ID_USUARIO,id);
		updateBuilder.updateColumnValue(Usuario.ESTADO_USUARIO,nombre);
		updateBuilder.update();
	}
	public static void actualizarDescripcion(Context context, String nombre, int id) throws SQLException {
		cargarDao(context);
		UpdateBuilder<Usuario, Integer> updateBuilder = dao.updateBuilder();
		updateBuilder.where().eq(Usuario.ID_USUARIO,id);
		updateBuilder.updateColumnValue(Usuario.DESCRIPCION_USUARIO,nombre);
		updateBuilder.update();
	}

}
