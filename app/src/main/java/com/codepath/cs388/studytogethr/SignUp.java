package com.codepath.cs388.studytogethr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUp extends AppCompatActivity {
    private Button btnSignUp;
    private Button btnLogin;
    private EditText etUsername;
    private EditText etEmail;
    private EditText etPassword;
    public static final String TAG = "SignUpActivity ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btnSignUp = findViewById(R.id.btnSignUp);
        etEmail = findViewById(R.id.etEmail);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        Intent i = getIntent();
        etUsername.setText(i.getStringExtra("username"));

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@njit.edu";
                if (email.matches(emailPattern) && email.length() > 0) {
                    ParseUser user = new ParseUser();
                    user.setEmail(email);
                    user.setUsername(etUsername.getText().toString());
                    user.setPassword(etPassword.getText().toString());
                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e != null) {
                                Log.e(TAG, "Error signing up user", e);
                                return;
                            }
                            Log.i(TAG, "Sign up was successful");
                            goMainActivity();
                        }
                    });
                }
                else {
                    Log.e(TAG, "email doesn't match pattern: " + email);
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goLogin();
            }
        });
    }

    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private void goLogin() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }


}