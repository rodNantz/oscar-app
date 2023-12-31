package oscar.go.com.oscarapp.filme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import oscar.go.com.oscarapp.MainActivity;
import oscar.go.com.oscarapp.R;
import oscar.go.com.oscarapp.classes.Diretor;
import oscar.go.com.oscarapp.classes.Filme;
import oscar.go.com.oscarapp.classes.SessionManager;

public class FilmeActivity extends AppCompatActivity {
    private TextView nomeFilme;
    private TextView genero;
    private ImageView poster;
    private Button votar;
    private Filme filme;
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
        setContentView(R.layout.activity_filme);

        session = new SessionManager(this);
        session.checkLogin();

        HashMap<String, String> userLogged = session.getInfoUsuario();
        codU = Long.parseLong(userLogged.get(SessionManager.KEY_CODU));
        userName = userLogged.get(SessionManager.KEY_USER);
        filmeStr = userLogged.get(SessionManager.KEY_FILME);
        diretorStr = userLogged.get(SessionManager.KEY_DIRETOR);
        voted = Boolean.parseBoolean(userLogged.get(SessionManager.KEY_VOTED));
        filmeVoto = gson.fromJson(filmeStr, Filme.class);
        diretorVoto = gson.fromJson(diretorStr, Diretor.class);

        nomeFilme = (TextView) findViewById(R.id.nomeFTV);
        genero = (TextView) findViewById(R.id.generoFTV);
        poster = (ImageView) findViewById(R.id.posterFIV);
        votar = (Button) findViewById(R.id.btnVotarFilme);

        Intent intent = getIntent();
        filme = (Filme) intent.getSerializableExtra("filme");

        nomeFilme.setText(filme.getNome());
        genero.setText(filme.getGenero());
        Picasso.with(this).load(filme.getFoto()).into(poster);

        Log.i("votoFilme", "codU: " + codU +
                " userName: " + userName +
                " filme: " + filme.getId()
                    + " " + filme.getNome()
                + " diretorVoto: " + diretorVoto.getId()
                    + " " + diretorVoto.getId());

        votar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.updateVoto(voted, filme, diretorVoto);
                Intent i = new Intent(FilmeActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
