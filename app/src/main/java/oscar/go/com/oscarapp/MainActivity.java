package oscar.go.com.oscarapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import oscar.go.com.oscarapp.diretor.ListaDiretorActivity;
import oscar.go.com.oscarapp.filme.ListaFilmesActivity;
import oscar.go.com.oscarapp.voto.VotoActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                // invalidar a sessao antes de matar o programa ou levar a tela de login
                super.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
