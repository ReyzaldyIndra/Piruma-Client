package app.piruma_java.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import app.piruma_java.R;
import app.piruma_java.network.VolleyNetwork;

public class SignupActivity extends AppCompatActivity {

    private String TAG = LoginActivity.class.getSimpleName();
    EditText username,email,nim,telepon,password,confirm_password;
    Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = findViewById(R.id.edittextUsername);
        email = findViewById(R.id.edittextEmail);
        nim = findViewById(R.id.edittextNim);
        telepon = findViewById(R.id.edittextTelepon);
        password = findViewById(R.id.edittextPassword);
        confirm_password = findViewById(R.id.edittextPasswordConfirm);
        signup = findViewById(R.id.btnSignUp);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });
    }

    void signUp(){
        String url = " https://piruma.au-syd.mybluemix.net/api/signup";
        JSONObject id = new JSONObject();


        try {
            id.put("username",username.getText().toString());
            id.put("email",email.getText().toString());
            id.put("nim",nim.getText().toString());
            id.put("telepon",telepon.getText().toString());
            id.put("password",password.getText().toString());
            id.put("confirm_password",confirm_password.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("body signup",id.toString());
        VolleyNetwork request = new VolleyNetwork(url,id,TAG);
        request.postRequest(new VolleyNetwork.VolleyCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                Toast.makeText(SignupActivity.this, "SignUp Sukses!", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onError(VolleyError error) {
                Toast.makeText(SignupActivity.this,"SignUp Gagal", Toast.LENGTH_SHORT).show();
            }
        },this);
    }
}
