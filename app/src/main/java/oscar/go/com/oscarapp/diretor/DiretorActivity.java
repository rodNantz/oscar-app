package oscar.go.com.oscarapp.diretor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    private int codU;
    private String userName;
    private String filmeStr;
    private String diretorStr;
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
        codU = Integer.parseInt(userLogged.get(SessionManager.KEY_CODU));
        userName = userLogged.get(SessionManager.KEY_USER);
        filmeStr = userLogged.get(SessionManager.KEY_FILME);
        diretorStr = userLogged.get(SessionManager.KEY_DIRETOR);

        filmeVoto = gson.fromJson(filmeStr, Filme.class);
        diretorVoto = gson.fromJson(diretorStr, Diretor.class);

        nomeDiretor = (TextView) findViewById(R.id.nomeDTV);
        votar = (Button) findViewById(R.id.btnVotarDiretor);

        Intent intent = getIntent();
        diretor = (Diretor) intent.getSerializableExtra("diretor");

        nomeDiretor.setText(diretor.getNome());

        votar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.updateVoto(filmeVoto, diretor);
                // pegar usuario
                // a id
                // salvar na sessao
                // ir pra main activity
                Intent i = new Intent(DiretorActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
