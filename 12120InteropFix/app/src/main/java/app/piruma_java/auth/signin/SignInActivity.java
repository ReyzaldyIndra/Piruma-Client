package app.piruma_java.auth.signin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import app.piruma_java.R;
import app.piruma_java.base.BaseActivity;

public class SignInActivity extends BaseActivity<SignInPresenter> implements SignInView {
    private TextInputLayout tilEmail, tilPassword;
    private EditText TxEmail, TxPassword;
    private Button btSignIn;
    private String email = "", password = "";

    @Override
    protected void setupLayout() {
        setContentView(R.layout.activity_sign_in);
        buildSignIn();
        presenter = new SignInPresenter();
        presenter.attachView(this);
    }

    private void buildSignIn(){

    }

    @Override
    protected void onViewReady() {
        tilEmail = findViewById(R.id.til_email);
        tilPassword = findViewById(R.id.til_password);
        btSignIn = findViewById(R.id.btn_login);
        TextView tvSignUp = findViewById(R.id.lktx_signup);
        TextView tvForgot = findViewById(R.id.lktx_forgotpwd);

        final TextInputEditText etEmail = findViewById(R.id.email_edittext);
        TextInputEditText etPassword = findViewById(R.id.pass_edittext);
//
//        tilEmail.setError(getString(R.string.valid_email));
//        tilPassword.setError(getString(R.string.password_minimum));

        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (Patterns.EMAIL_ADDRESS.matcher(s).matches()) {
                    email = s.toString();
                    tilEmail.setErrorEnabled(false);
                } else {
                    email = "";
                    tilEmail.setErrorEnabled(true);
                }
                dataCheck();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilPassword.setErrorEnabled(s.toString().length() > 5);
                if (s.toString().length() > 5) {
                    password = s.toString();
                } else {
                    password = "";
                }
                dataCheck();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.doSignIn(email, password);
            }
        });

//        tvSignUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
//                finish();
//            }
//        });

//        tvForgot.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(SignInActivity.this, ResetPasswordActivity.class));
//            }
//        });
    }

    private void dataCheck() {
        btSignIn.setEnabled(!email.isEmpty() && !password.isEmpty());
    }

    @Override
    protected View getContextView(){
    return findViewById(R.id.content);
    }

    @Override
    public void onSignInSuccess() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void showError(String message) {
//        Common.snackBar(view, message);
    }


}
