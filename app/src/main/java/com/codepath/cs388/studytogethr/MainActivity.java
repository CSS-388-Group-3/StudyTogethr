package com.codepath.cs388.studytogethr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.codepath.cs388.studytogethr.fragments.ComposeFragment;
import com.codepath.cs388.studytogethr.fragments.PostsFragment;
import com.codepath.cs388.studytogethr.fragments.ProfileProfessorFragment;
import com.codepath.cs388.studytogethr.fragments.ProfileStudentFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    final FragmentManager fragmentManager = getSupportFragmentManager();
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                ParseUser user = ParseUser.getCurrentUser();
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.action_classList:
                        fragment = new PostsFragment();
                        break;
                    case R.id.action_compose:
                        fragment = new ComposeFragment();
                        break;
                    case R.id.action_profile:
                    default:
                        String role = (String) user.get("role");
                        if(role.equalsIgnoreCase("professor")){
                            fragment = new ProfileProfessorFragment();
                        }
                        else{
                            fragment = new ProfileStudentFragment();
                        }
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });
        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.action_classList);
    }
}