package oscar.go.com.oscarapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import oscar.go.com.oscarapp.classes.SessionManager;
import oscar.go.com.oscarapp.diretor.ListaDiretorActivity;
import oscar.go.com.oscarapp.filme.ListaFilmesActivity;
import oscar.go.com.oscarapp.voto.VotoActivity;

public class MainActivity extends AppCompatActivity {
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session = new SessionManager(this);
        session.checkLogin();
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
