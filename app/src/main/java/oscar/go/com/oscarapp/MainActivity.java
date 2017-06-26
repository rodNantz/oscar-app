package oscar.go.com.oscarapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.HashMap;

import oscar.go.com.oscarapp.classes.Diretor;
import oscar.go.com.oscarapp.classes.Filme;
import oscar.go.com.oscarapp.classes.SessionManager;
import oscar.go.com.oscarapp.diretor.ListaDiretorActivity;
import oscar.go.com.oscarapp.filme.ListaFilmesActivity;
import oscar.go.com.oscarapp.voto.VotoActivity;

public class MainActivity extends AppCompatActivity {
    TextView tvUsuario;
    TextView tvVote;
    // session
    private SessionManager session;
    private long codU;
    private String userName;
    private String filmeStr;
    private String diretorStr;
    private boolean voted;
    private Filme filmeVoto;
    private Diretor diretorVoto;
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvUsuario = (TextView) findViewById(R.id.tvUsuario);
        tvVote = (TextView) findViewById(R.id.tvVote);

        session = new SessionManager(this);
        session.checkLogin();

        HashMap<String, String> userLogged = session.getInfoUsuario();
        userName = userLogged.get(SessionManager.KEY_USER);
        voted = Boolean.parseBoolean(userLogged.get(SessionManager.KEY_VOTED));

        tvUsuario.setText("Olá, " + userName + "!");
        if(voted){
            tvVote.setText("Você já votou!");
        } else {
            tvVote.setText("Vote no menu à direita!");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.menu_filme:
                intent = new Intent(MainActivity.this, ListaFilmesActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_diretor:
                intent = new Intent(MainActivity.this, ListaDiretorActivity.class);
                startActivity(intent);
                return true;
            case R.id.confirmaVoto:
                intent = new Intent(MainActivity.this, VotoActivity.class);
                startActivity(intent);
                return true;
            case R.id.sair:
                session.logoutUsuario();
                //super.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
