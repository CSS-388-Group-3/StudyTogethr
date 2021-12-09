package com.codepath.cs388.studytogethr;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {
    private Button btnSignUp;
    private Button btnLogin;
    private EditText etUsername;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private RadioGroup rgRole;
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
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnLogin = findViewById(R.id.btnLogin);
        rgRole = findViewById(R.id.rgRole);
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
                    if(etPassword.length()!=0) {
                        if (etConfirmPassword.getText().toString().matches(etPassword.getText().toString())) {
                            user.setPassword(etPassword.getText().toString());
                        } else {
                            Toast.makeText(SignUpActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(SignUpActivity.this, "Please enter a password", Toast.LENGTH_SHORT).show();
                        return;
                    }

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
                                Toast.makeText(SignUpActivity.this, "User already exists.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            Log.i(TAG, "Sign up was successful");
                            etEmail.getText().clear();
                            etUsername.getText().clear();
                            etPassword.getText().clear();
                            etConfirmPassword.getText().clear();
                            rgRole.clearCheck();

                            AlertDialog alertDialog = new AlertDialog.Builder(SignUpActivity.this).create();
                            alertDialog.setTitle("Thanks for signing up!");
                            alertDialog.setMessage("Please verify your account via email before logging in. ");
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                            Intent i = new Intent("android.intent.action.VIEW",
                                                            Uri.parse("http://www.gmail.com/"));
                                            startActivity(i);
                                        }
                                    });
                            alertDialog.show();
                            ParseUser.logOut();
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