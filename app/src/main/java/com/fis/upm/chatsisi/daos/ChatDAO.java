package com.fis.upm.chatsisi.daos;

import android.content.Context;

import com.fis.upm.chatsisi.dbhelper.DBHelperMOS;
import com.fis.upm.chatsisi.entities.Agenda;
import com.fis.upm.chatsisi.entities.Chat;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

public class ChatDAO extends DBHelperMOS {
	static Dao<Chat, Integer> dao;

	public static void cargarDao(Context context) throws SQLException {
		dao = getHelper(context).getChatDAO();
	}

	//__________FUNCIONES DE CREACIÓN________________________//

	public static boolean newChat(Context context,String fecha_inicio, int fk_usaurio_envia, int fk_usaurio_recibe) {
		Chat t = montarChat(fecha_inicio, fk_usaurio_envia, fk_usaurio_recibe);
		return crearChat(t,context);
	}
	public static Chat newChatRet(Context context,String fecha_inicio, int fk_usaurio_envia, int fk_usaurio_recibe) {
		Chat t = montarChat(fecha_inicio, fk_usaurio_envia, fk_usaurio_recibe);
		return crearChatRet(t,context);
	}
	public static boolean crearChat(Chat t,Context context) {
		try {
			cargarDao(context);
			dao.create(t);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static Chat crearChatRet(Chat t,Context context) {
		try {
			cargarDao(context);
			dao.create(t);
			return t;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static Chat montarChat(String fecha_inicio, int fk_usaurio_envia, int fk_usaurio_recibe) {
		Chat u =new Chat(fecha_inicio, fk_usaurio_envia, fk_usaurio_recibe);
		return u;
	}

	//__________FUNCIONES DE BORRADO______________________//

	public static void borrarTodosLosChats(Context context) throws SQLException {
		cargarDao(context);
		DeleteBuilder<Chat, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.delete();
	}
	public static void borrarChatPorID(Context context, int id) throws SQLException {
		cargarDao(context);
		DeleteBuilder<Chat, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.where().eq(Chat.ID_CHAT, id);
		deleteBuilder.delete();
	}

	//__________FUNCIONES DE BUSQUEDA______________________//


	public static List<Chat> buscarTodosLosChats(Context context) throws SQLException {
		cargarDao(context);
		List<Chat> listadoChat= dao.queryForAll();
		if(listadoChat.isEmpty()) {
			return null;
		}else{
			return listadoChat;
		}
	}
	public static Chat buscarChatPorId(Context context, int id) throws SQLException {
		cargarDao(context);
		List<Chat> listadoChat= dao.queryForEq(Chat.ID_CHAT, id);
		if(listadoChat.isEmpty()) {
			return null;
		}else{
			return listadoChat.get(0);
		}
	}
	public static List<Chat> buscarChatPorFkUsuarioEnvia(Context context, int id) throws SQLException {
		cargarDao(context);
		List<Chat> listadoChat= dao.queryBuilder().where().eq(Chat.FK_USUARIO_ENVIA,id).or().eq(Chat.FK_USUARIO_RECIBE,id).query();
		if(listadoChat.isEmpty()) {
			return null;
		}else{
			return listadoChat;
		}
	}
	public static List<Chat> buscarChatPorFkUsuarioRecibe(Context context, int id) throws SQLException {
		cargarDao(context);
		List<Chat> listadoChat= dao.queryBuilder().where().eq(Chat.FK_USUARIO_RECIBE,id).query();
		if(listadoChat.isEmpty()) {
			return null;
		}else{
			return listadoChat;
		}
	}
	public static Chat buscarChatPorFkEnvioFkRecibe(Context context, int envia, int recibe) throws SQLException {
		cargarDao(context);
		List<Chat> listadoChat= dao.queryBuilder().where().eq(Chat.FK_USUARIO_ENVIA,envia).and().eq(Chat.FK_USUARIO_RECIBE,recibe).query();
		if(listadoChat.isEmpty()) {
			return null;
		}else{
			return listadoChat.get(0);
		}
	}


	//____________________________FUNCIONES DE ACTUALIZAR_________________________________________//



}
