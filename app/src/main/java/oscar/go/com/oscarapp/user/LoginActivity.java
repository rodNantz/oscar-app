package oscar.go.com.oscarapp.user;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import oscar.go.com.oscarapp.MainActivity;
import oscar.go.com.oscarapp.R;
import oscar.go.com.oscarapp.classes.SessionManager;
import oscar.go.com.oscarapp.classes.User;
import oscar.go.com.oscarapp.utilities.MsgResponse;
import oscar.go.com.oscarapp.utilities.PathClass;

public class LoginActivity extends AppCompatActivity {
    private EditText user;
    private EditText password;
    private Button login;
    private Gson gson = new Gson();
    private ProgressDialog pDialog;
    private User usuario;
    private SessionManager session;
    private PathClass path;
    private String url = "";
    private JSONObject jsonObject;
    private MsgResponse msgResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session = new SessionManager(getApplicationContext());

        user = (EditText) findViewById(R.id.usuarioET);
        password = (EditText) findViewById(R.id.senhaET);
        login = (Button) findViewById(R.id.btnLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUsuario();
            }
        });
    }

    private void loginUsuario(){
        String usuarioLogin = user.getText().toString();
        String senhaLogin = password.getText().toString();
        if(validaCampos(usuarioLogin, senhaLogin)){

            usuario = new User(usuarioLogin, senhaLogin);

            pDialog = new ProgressDialog(this);
            pDialog.setMessage("Logando...");
            pDialog.show();

            path = new PathClass(this);
            try {
                url = path.getServerPath() + "/auth";
                jsonObject = new JSONObject(gson.toJson(usuario));

                JsonObjectRequest stringRequest = new JsonObjectRequest(url, jsonObject,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                msgResponse = gson.fromJson(response.toString(), MsgResponse.class);
                                User user = gson.fromJson(msgResponse.getExtra(), User.class);
                                if(msgResponse.isStatus()){
                                    long codU = user.getCodU();
                                    String userName = user.getUser();
                                    session.loginSession(codU, userName);
                                    pDialog.hide();
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                try {
                                    if (error.networkResponse.statusCode != 0) {
                                        Toast.makeText(LoginActivity.this,
                                                "Erro: " + error.networkResponse.statusCode,
                                                Toast.LENGTH_LONG)
                                                .show();
                                        pDialog.hide();
                                    } else {
                                        Toast.makeText(LoginActivity.this,
                                                "Erro: " + error.getMessage(),
                                                Toast.LENGTH_LONG)
                                                .show();
                                        pDialog.hide();
                                    }
                                } catch(Exception e){
                                    Toast.makeText(LoginActivity.this,
                                            "Execeção: " + e.getMessage(),
                                            Toast.LENGTH_LONG)
                                            .show();
                                    pDialog.hide();
                                }
                            }
                        });
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(stringRequest);
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(LoginActivity.this,
                        "JSONException: " + e,
                        Toast.LENGTH_LONG)
                        .show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean validaCampos(String emailU, String senhaU) {
        if (TextUtils.isEmpty(emailU)) {
            user.requestFocus();
            user.setError("Por favor digite seu usuário!");
            return true;
        } else if (TextUtils.isEmpty(senhaU)) {
            password.requestFocus();
            password.setError("Por favor digite sua senha!");
            return true;
        }
        return false;
    }
}
