package oscar.go.com.oscarapp.diretor;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import oscar.go.com.oscarapp.R;
import oscar.go.com.oscarapp.classes.CandidatosDiretor;
import oscar.go.com.oscarapp.classes.Diretor;
import oscar.go.com.oscarapp.classes.SessionManager;
import oscar.go.com.oscarapp.utilities.HttpHandler;

public class ListaDiretorActivity extends AppCompatActivity {
    private SessionManager session;
    private ProgressDialog pDialog;
    private ListView list;
    private DiretorListViewAdapter adapter;
    private int codU;
    private String userName;
    private Gson gson = new Gson();
    private ArrayList<Diretor> infoDiretor = new ArrayList<>();
    private final String url = "https://dl.dropboxusercontent.com/u/40990541/diretor.json";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_diretor);

        /*session = new SessionManager(this);
        session.checkLogin();

        HashMap<String, String> userLogged = session.getInfoUsuario();
        codU = Integer.parseInt(userLogged.get(SessionManager.KEY_CODU));
        userName = userLogged.get(SessionManager.KEY_USER);*/

        new GetDiretores().execute();
    }

    private class GetDiretores extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(ListaDiretorActivity.this);
            pDialog.setMessage("Por favor, aguarde...");
            pDialog.setCancelable(false);
            pDialog.show();

        }
        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            String jsonStr = sh.makeServiceCall(url, "GET", null);

            if(jsonStr != null){
                CandidatosDiretor diretorObj = gson.fromJson(jsonStr, CandidatosDiretor.class);
                infoDiretor = diretorObj.getDiretores();
                /*for(Diretor d : diretorObj.getDiretores()){
                    long id = d.getId();
                    String nome = d.getNome();
                }*/

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
            adapter = new DiretorListViewAdapter(ListaDiretorActivity.this, infoDiretor);
            list.setAdapter(adapter);
        }
    }
}
