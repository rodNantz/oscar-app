package oscar.go.com.oscarapp.diretor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import oscar.go.com.oscarapp.R;
import oscar.go.com.oscarapp.classes.Diretor;

public class DiretorActivity extends AppCompatActivity {
    private TextView nomeDiretor;
    private Button votar;
    private Diretor diretor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diretor);

        nomeDiretor = (TextView) findViewById(R.id.nomeDTV);
        votar = (Button) findViewById(R.id.btnVotarDiretor);

        Intent intent = getIntent();
        diretor = (Diretor) intent.getSerializableExtra("diretor");

        nomeDiretor.setText(diretor.getNome());

        votar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
