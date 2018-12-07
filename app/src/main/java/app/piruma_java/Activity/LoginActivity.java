package app.piruma_java.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import app.piruma_java.R;
import app.piruma_java.SessionManager;
import app.piruma_java.main.MainActivity;
import app.piruma_java.network.VolleyNetwork;

public class LoginActivity extends AppCompatActivity {

    private String TAG = LoginActivity.class.getSimpleName();
    EditText txtEmail,txtPassword;
    Button btnLogin;
    TextView txtSignUp;
    ProgressBar progressBar;
    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session = new SessionManager(getApplicationContext());
        txtEmail = findViewById(R.id.edittextEmail);
        txtPassword = findViewById(R.id.edittextPassword);
        btnLogin = findViewById(R.id.btnLogin);
        txtSignUp = findViewById(R.id.txtSignup);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "Login", Toast.LENGTH_SHORT).show();
                login();

            }
        });

        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(i);
            }
        });

    }

    void login(){

        String url = "https://piruma.au-syd.mybluemix.net/api/login";
        final String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();
        JSONObject id = new JSONObject();
        try {
            id.put("email",email);
            id.put("password",password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("body",id.toString());
        VolleyNetwork request = new VolleyNetwork(url,id,TAG);
        request.postRequest(new VolleyNetwork.VolleyCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                Toast.makeText(LoginActivity.this, "Berhasil Masuk", Toast.LENGTH_SHORT).show();
                try {
                    String username = result.getString("username");
                    String token = result.getString("token");
                    session.createLoginSession(email,username,token);
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(VolleyError error) {
                Toast.makeText(LoginActivity.this, "Gagal masuk", Toast.LENGTH_SHORT).show();
            }
        },this);
    }
}
