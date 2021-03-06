package com.codepath.cs388.studytogethr.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.codepath.cs388.studytogethr.LoginActivity;
import com.codepath.cs388.studytogethr.R;
import com.parse.ParseUser;

public class ProfileStudentFragment extends Fragment {

    public static final String TAG = "ProfileStudentFragment";
    Button btnLogout;
    TextView tvUsername;
    TextView tvEmail;
    TextView tvRole;

    public ProfileStudentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profilestudent, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ParseUser user = ParseUser.getCurrentUser();
        tvUsername = view.findViewById(R.id.tvUsername);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvRole = view.findViewById(R.id.tvRole);
        btnLogout = view.findViewById(R.id.btnLogout);

        tvUsername.setText(user.getUsername());
        tvEmail.setText("Email: " + user.getEmail());
        String role = (String) user.get("role");
        tvRole.setText("Role: " + role);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser.logOut();
                Intent i = new Intent(getContext().getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });
    }
}