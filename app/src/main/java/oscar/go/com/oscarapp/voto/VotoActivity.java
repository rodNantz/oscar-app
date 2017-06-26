package oscar.go.com.oscarapp.voto;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import oscar.go.com.oscarapp.R;
import oscar.go.com.oscarapp.classes.Diretor;
import oscar.go.com.oscarapp.classes.Filme;
import oscar.go.com.oscarapp.classes.SessionManager;
import oscar.go.com.oscarapp.classes.User;
import oscar.go.com.oscarapp.classes.Vote;
import oscar.go.com.oscarapp.utilities.MsgResponse;
import oscar.go.com.oscarapp.utilities.PathClass;

public class VotoActivity extends AppCompatActivity {
    private TextView nomeFilme;
    private TextView genero;
    private ImageView poster;
    private TextView nomeDiretor;
    private Button votar;
    private AlertDialog alertDialog;
    // session
    private SessionManager session;
    private long codU;
    private String userName;
    private String filmeStr;
    private String diretorStr;
    private String token;
    private boolean voted;
    private Filme filmeVoto;
    private Diretor diretorVoto;
    private Gson gson = new Gson();
    private User user;
    private Vote vote;
    // path
    private PathClass path;
    private String url = "";
    private JSONObject jsonObject;
    private MsgResponse msgResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voto);

        session = new SessionManager(this);
        session.checkLogin();

        HashMap<String, String> userLogged = session.getInfoUsuario();
        codU = Long.parseLong(userLogged.get(SessionManager.KEY_CODU));
        userName = userLogged.get(SessionManager.KEY_USER);
        token = userLogged.get(SessionManager.KEY_TOKEN);
        voted = Boolean.parseBoolean(userLogged.get(SessionManager.KEY_VOTED));
        filmeStr = userLogged.get(SessionManager.KEY_FILME);
        diretorStr = userLogged.get(SessionManager.KEY_DIRETOR);

        filmeVoto = gson.fromJson(filmeStr, Filme.class);
        diretorVoto = gson.fromJson(diretorStr, Diretor.class);

        nomeFilme = (TextView) findViewById(R.id.nomeFilmeVoto);
        genero = (TextView) findViewById(R.id.generoVoto);
        poster = (ImageView) findViewById(R.id.posterVoto);
        nomeDiretor = (TextView) findViewById(R.id.nomeDiretorVoto);
        votar = (Button) findViewById(R.id.btnConfirmaVoto);

        if (filmeVoto != null && diretorVoto != null){
            nomeFilme.setText(filmeVoto.getNome());
            genero.setText(filmeVoto.getGenero());
            Picasso.with(this).load(filmeVoto.getFoto()).into(poster);
            nomeDiretor.setText(diretorVoto.getNome());

            votar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!voted){
                        confirmaVoto();
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Voto não computado!",
                                Toast.LENGTH_LONG)
                                .show();
                        alertDialog = new AlertDialog.Builder(VotoActivity.this).create();
                        alertDialog.setTitle("Voto não realizado!");
                        alertDialog.setMessage("Não é possível votar mais de uma vez.");
                        alertDialog.setButton(Dialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(),
                                        "Voto computado!",
                                        Toast.LENGTH_LONG)
                                        .show();
                            }
                        });
                        alertDialog.show();
                    }
                }
            });
        }
    }

    private void confirmaVoto(){
        path = new PathClass(getApplicationContext());
        try {
            url = path.getServerPath() + "/vote";
            user = new User(codU, userName, "", token, true);
            vote = new Vote(user, filmeVoto.getId(), diretorVoto.getId());
            jsonObject = new JSONObject(gson.toJson(vote));
            JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST , url, jsonObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            msgResponse = gson.fromJson(response.toString(), MsgResponse.class);
                            if(msgResponse.isStatus()){
                                session.updateVoto(true, filmeVoto, diretorVoto);
                                alertDialog = new AlertDialog.Builder(VotoActivity.this).create();
                                alertDialog.setTitle("Voto Confirmado!");
                                alertDialog.setMessage(msgResponse.getMessage());
                                alertDialog.setButton(Dialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(getApplicationContext(),
                                                "Voto realizado!",
                                                Toast.LENGTH_LONG)
                                                .show();
                                    }
                                });
                                alertDialog.show();
                            } else {
                                alertDialog = new AlertDialog.Builder(getApplicationContext()).create();
                                alertDialog.setTitle("Voto não realizado!");
                                alertDialog.setMessage(msgResponse.getMessage());
                                alertDialog.setButton(Dialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(VotoActivity.this,
                                                "Voto não concluído!",
                                                Toast.LENGTH_LONG)
                                                .show();
                                    }
                                });
                                alertDialog.show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            try {
                                if (error.networkResponse.statusCode != 0) {
                                    Toast.makeText(VotoActivity.this,
                                            "Erro: " + error.networkResponse.statusCode,
                                            Toast.LENGTH_LONG)
                                            .show();
                                } else {
                                    Toast.makeText(VotoActivity.this,
                                            "Erro: " + error.getMessage(),
                                            Toast.LENGTH_LONG)
                                            .show();
                                }
                            } catch(Exception e){
                                Toast.makeText(VotoActivity.this,
                                        "Execeção: " + e.getMessage(),
                                        Toast.LENGTH_LONG)
                                        .show();
                            }
                        }
                    });
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(VotoActivity.this,
                    "JSONException: " + e.getMessage(),
                    Toast.LENGTH_LONG)
                    .show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(VotoActivity.this,
                    "IOException: " + e.getMessage(),
                    Toast.LENGTH_LONG)
                    .show();
        }
    }
}
