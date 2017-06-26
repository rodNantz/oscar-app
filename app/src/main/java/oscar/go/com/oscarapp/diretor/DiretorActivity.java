package oscar.go.com.oscarapp.diretor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import oscar.go.com.oscarapp.MainActivity;
import oscar.go.com.oscarapp.R;
import oscar.go.com.oscarapp.classes.Diretor;
import oscar.go.com.oscarapp.classes.Filme;
import oscar.go.com.oscarapp.classes.SessionManager;

public class DiretorActivity extends AppCompatActivity {
    private TextView nomeDiretor;
    private Button votar;
    private Diretor diretor;
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
        setContentView(R.layout.activity_diretor);

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

        nomeDiretor = (TextView) findViewById(R.id.nomeDTV);
        votar = (Button) findViewById(R.id.btnVotarDiretor);

        Intent intent = getIntent();
        diretor = (Diretor) intent.getSerializableExtra("diretor");

        nomeDiretor.setText(diretor.getNome());

        Log.i("votoDiretor", "codU: " + codU +
                " userName: " + userName +
                " filmeVoto: " + filmeVoto.getId()
                + " " + filmeVoto.getNome()
                + " diretor: " + diretor.getId()
                + " " + diretor.getId());

        votar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.updateVoto(voted, filmeVoto, diretor);
                Intent i = new Intent(DiretorActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
