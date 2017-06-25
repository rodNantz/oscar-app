package oscar.go.com.oscarapp.filme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import oscar.go.com.oscarapp.R;
import oscar.go.com.oscarapp.classes.Filme;

public class FilmeActivity extends AppCompatActivity {
    private TextView nomeFilme;
    private TextView genero;
    private ImageView poster;
    private Button votar;
    private Filme filme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filme);

        nomeFilme = (TextView) findViewById(R.id.nomeFTV);
        genero = (TextView) findViewById(R.id.generoFTV);
        poster = (ImageView) findViewById(R.id.posterFIV);
        votar = (Button) findViewById(R.id.btnVotarFilme);

        Intent intent = getIntent();
        filme = (Filme) intent.getSerializableExtra("filme");

        nomeFilme.setText(filme.getNome());
        genero.setText(filme.getGenero());
        Picasso.with(this).load(filme.getFoto()).into(poster);

        votar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
