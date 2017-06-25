package oscar.go.com.oscarapp.classes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashMap;

import oscar.go.com.oscarapp.LoginActivity;

/**
 * Created by Rebeca de Melo on 24/06/2017.
 */
public class SessionManager {

    SharedPreferences preferences;
    Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREFERENCE_NAME = "InfoUsuario";
    public static final String IS_LOGGED = "Logged";
    public static final String KEY_CODU = "codU";
    public static final String KEY_USER = "user";

    public SessionManager(Context context){
        this._context = context;
        preferences = _context.getSharedPreferences(PREFERENCE_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    public void loginSession(long codU, String user){
        editor.putBoolean(IS_LOGGED, true);
        editor.putLong(KEY_CODU, codU);
        editor.putString(KEY_USER, user);
        editor.commit();
    }

    public HashMap<String, String> getInfoUsuario(){
        HashMap<String, String> usuario = new HashMap<>();

        usuario.put(KEY_CODU, String.valueOf(preferences.getInt(KEY_CODU, 0)));
        usuario.put(KEY_USER, preferences.getString(KEY_USER, null));

        return usuario;
    }

    public void checkLogin(){
        if(!this.isLoggedIn()){
            Intent intent = new Intent(_context, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(intent);
        }
    }

    public boolean isLoggedIn(){
        return preferences.getBoolean(IS_LOGGED, false);
    }

    public void logoutUsuario(){
        editor.clear();
        editor.commit();

        Intent intent = new Intent(_context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(intent);
    }
}
