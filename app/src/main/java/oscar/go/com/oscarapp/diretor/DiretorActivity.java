package oscar.go.com.oscarapp.diretor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import oscar.go.com.oscarapp.R;

public class DiretorActivity extends AppCompatActivity {
    private TextView nomeDiretor;
    private Button votar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diretor);
    }
}
