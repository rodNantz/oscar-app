package oscar.go.com.oscarapp.filme;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import oscar.go.com.oscarapp.R;
import oscar.go.com.oscarapp.classes.CandidatosFilme;
import oscar.go.com.oscarapp.classes.Filme;
import oscar.go.com.oscarapp.classes.SessionManager;
import oscar.go.com.oscarapp.utilities.HttpHandler;

public class ListaFilmesActivity extends Activity {

    private SessionManager session;
    private long codU;
    private String userName;
    private ProgressDialog pDialog;
    private ListView list;
    private FilmeListViewAdapter adapter;
    private Gson gson = new Gson();
    private ArrayList<Filme> infoFilmes = new ArrayList<>();
    private final String url = "https://dl.dropboxusercontent.com/u/40990541/filme.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_filmes);

        session = new SessionManager(this);
        session.checkLogin();

        new GetFilmes().execute();
    }

    private class GetFilmes extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(ListaFilmesActivity.this);
            pDialog.setMessage("Por favor, aguarde...");
            pDialog.setCancelable(false);
            pDialog.show();

        }
        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            String jsonStr = sh.makeServiceCall(url, "GET", null);

            if(jsonStr != null){
                CandidatosFilme filmeObj = gson.fromJson(jsonStr, CandidatosFilme.class);
                infoFilmes = filmeObj.getFilme();
            }else{
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(
                                getApplicationContext(),
                                "Não foi possivel pegar o Json do servidor.",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing()) {
                pDialog.dismiss();
            }

            list = (ListView) findViewById(R.id.listFilme);
            adapter = new FilmeListViewAdapter(ListaFilmesActivity.this, infoFilmes);
            list.setAdapter(adapter);
        }
    }
}
