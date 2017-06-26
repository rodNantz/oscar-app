package oscar.go.com.oscarapp.classes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;

import java.util.HashMap;

import oscar.go.com.oscarapp.user.LoginActivity;

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
    public static final String KEY_TOKEN = "token";
    public static final String KEY_VOTED = "voted";
    public static final String KEY_FILME = "filme";
    public static final String KEY_DIRETOR = "diretor";
    private Gson gson = new Gson();

    public SessionManager(Context context){
        this._context = context;
        preferences = _context.getSharedPreferences(PREFERENCE_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    public void loginSession(long codU, String user, String token, boolean voted, Filme votoFilme, Diretor votoDiretor){
        editor.putBoolean(IS_LOGGED, true);
        editor.putLong(KEY_CODU, codU);
        editor.putString(KEY_USER, user);
        editor.putString(KEY_TOKEN, token);
        editor.putBoolean(KEY_VOTED, voted);
        editor.putString(KEY_FILME, gson.toJson(votoFilme));
        editor.putString(KEY_DIRETOR, gson.toJson(votoDiretor));
        editor.commit();
    }

    public HashMap<String, String> getInfoUsuario(){
        HashMap<String, String> usuario = new HashMap<>();

        usuario.put(KEY_CODU, String.valueOf(preferences.getInt(KEY_CODU, 0)));
        usuario.put(KEY_USER, preferences.getString(KEY_USER, null));
        usuario.put(KEY_TOKEN, preferences.getString(KEY_TOKEN, null));
        usuario.put(KEY_VOTED, preferences.getString(KEY_VOTED, null));
        usuario.put(KEY_FILME, preferences.getString(KEY_FILME, null));
        usuario.put(KEY_DIRETOR, preferences.getString(KEY_DIRETOR, null));

        return usuario;
    }

    public void updateVoto(boolean voted, Filme votoFilme, Diretor votoDiretor){
        editor.putBoolean(KEY_VOTED, voted);
        editor.putString(KEY_FILME, gson.toJson(votoFilme));
        editor.putString(KEY_DIRETOR, gson.toJson(votoDiretor));
        editor.commit();
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
