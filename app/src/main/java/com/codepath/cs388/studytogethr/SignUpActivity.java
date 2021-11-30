package com.codepath.cs388.studytogethr;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {
    private Button btnSignUp;
    private Button btnLogin;
    private EditText etUsername;
    private EditText etEmail;
    private EditText etPassword;
    private RadioButton rbStudent;
    private RadioButton rbProfessor;
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
        rbStudent = findViewById(R.id.rbStudent);
        rbProfessor = findViewById(R.id.rbProfessor);


        Intent i = getIntent();
        etUsername.setText(i.getStringExtra("username"));

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@njit.edu";
                Log.i(TAG, String.valueOf(rbProfessor.isChecked()));
                Log.i(TAG, String.valueOf(rbStudent.isChecked()));
                if (email.matches(emailPattern) && email.length() > 0) {
                    ParseUser user = new ParseUser();
                    user.setEmail(email);
                    user.setUsername(etUsername.getText().toString());
                    user.setPassword(etPassword.getText().toString());

                    if (rbStudent.isChecked()) {
                        user.put("role", "student");
                    } else if (rbProfessor.isChecked()){
                        user.put("role", "professor");
                    }
                    else {
                        Toast.makeText(SignUpActivity.this, "Please select a role", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e != null) {
                                Log.e(TAG, "Error signing up user", e);
                                Toast.makeText(SignUpActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            Log.i(TAG, "Sign up was successful");
                            Toast.makeText(SignUpActivity.this, "Signup Successful. Please login", Toast.LENGTH_SHORT).show();
                            etEmail.getText().clear();
                            etUsername.getText().clear();
                            etPassword.getText().clear();
                        }
                    });
                }
                else {
                    Log.e(TAG, "email doesn't match pattern: " + email);
                    Toast.makeText(SignUpActivity.this, "Invalid email", Toast.LENGTH_SHORT).show();
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