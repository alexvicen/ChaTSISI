package com.fis.upm.chatsisi.dbhelper;

import android.app.Activity;
import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;

/**
 * 
 * @author Rodrigo</br>
 * Esta clase sirve para cargar el dbHelper, siempre que lo necesitemos simplemente debemos
 * extender de ella.
 * 
 */
public abstract class DBHelperMOS extends Activity {
	public static DBHelper mDBHelper;
	
	protected static DBHelper getHelper(Context context) {
		if (mDBHelper == null) {
			mDBHelper = OpenHelperManager.getHelper(context, DBHelper.class);
		}
		return mDBHelper;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mDBHelper != null) {
			OpenHelperManager.releaseHelper();
			mDBHelper = null;
		}
	}
}
