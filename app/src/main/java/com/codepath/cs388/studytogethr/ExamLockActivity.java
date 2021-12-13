package com.codepath.cs388.studytogethr;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codepath.cs388.studytogethr.fragments.PostsFragment;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

public class ExamLockActivity extends AppCompatActivity {
    private String TAG = "ExamLockActivity";
    private EditText etClassName;
    private Button btnExamLock;
    private Button btnExamUnlock;
    protected CoursesAdapter adapter;
    protected List<String> allCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_lock);
        etClassName = findViewById(R.id.etClassName);
        btnExamLock = findViewById(R.id.btnExamLock);
        btnExamUnlock = findViewById(R.id.btnExamUnlock);

        btnExamLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick lock button");
                String course = etClassName.getText().toString();
                lockClass(course);
            }
        });

        btnExamUnlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick unlock button");
                String course = etClassName.getText().toString();
                unLockClass(course);
            }
        });

    }

    private void lockClass(String thisCourse) {
        //hide course listing on PostFragment
        etClassName.getText().clear();
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.whereEqualTo(Post.KEY_COURSE, thisCourse);
        try {
            int count  =  query.count();
            query.setLimit(count);
        } catch (ParseException e) {
            e.printStackTrace();
        }// else default 100 for back4app
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null){
                    Log.e(TAG, "error getting posts to lock", e);
                    return;
                }
                Log.i(TAG, "Lock posts: " + posts);
                for (Post post : posts) {
                    post.put("display",false);
                    post.saveInBackground();
                }
            }
        });
        AlertDialog alertDialog = new AlertDialog.Builder(ExamLockActivity.this).create();
        alertDialog.setTitle(thisCourse+" has been locked!");
        alertDialog.setMessage("Please remember to unlock the course upon examination ending.");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        goMainActivity();
                    }
                });
        alertDialog.show();
    }

    private void unLockClass(String thisCourse) {
        //unhide course listing on PostFragment
        etClassName.getText().clear();
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.whereEqualTo(Post.KEY_COURSE, thisCourse);
        try {
            int count  =  query.count();
            query.setLimit(count);
        } catch (ParseException e) {
            e.printStackTrace();
        }// else default 100 for back4app
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null){
                    Log.e(TAG, "error getting posts to lock", e);
                    return;
                }
                Log.i(TAG, "Lock posts: " + posts);
                for (Post post : posts) {
                    post.put("display",true);
                    post.saveInBackground();
                }
            }
        });
        AlertDialog alertDialog = new AlertDialog.Builder(ExamLockActivity.this).create();
        alertDialog.setTitle("Unlocked!");
        alertDialog.setMessage(thisCourse+" is now open for students to view.");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        goMainActivity();
                    }
                });
        alertDialog.show();
    }
    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}