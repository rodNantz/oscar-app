package oscar.go.com.oscarapp.filme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import oscar.go.com.oscarapp.R;

public class FilmeActivity extends AppCompatActivity {
    private TextView nomeFilme;
    private TextView genero;
    private ImageView poster;
    private Button votar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filme);
    }
}
