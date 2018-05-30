package com.fis.upm.chatsisi.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class GestorSharedPreferences {
    //<---------------------------------------------------------------------------------->
    public static SharedPreferences getSharedPreferencesUsuario(Context context) {
        return context.getSharedPreferences("spUsuario", context.MODE_PRIVATE);
    }

    public static void setJsonUsuario(SharedPreferences sharedPreferences, JSONObject jsonObject) {
        SharedPreferences.Editor spe = sharedPreferences.edit();
        spe.putString("usuario", jsonObject.toString());
        spe.commit();
    }

    public static JSONObject getJsonUsuario(SharedPreferences sharedPreferences) throws JSONException {
        String s = sharedPreferences.getString("usaurio", "{}");
        return new JSONObject(s);
    }

    public static void clearSharedPreferencesUsuario(Context context) {
        SharedPreferences sharedPreferences = getSharedPreferencesUsuario(context);
        SharedPreferences.Editor spe = sharedPreferences.edit();
        spe.clear();
        spe.commit();
    }
    //<---------------------------------------------------------------------------------->
}