package com.codepath.cs388.studytogethr.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepath.cs388.studytogethr.Post;
import com.codepath.cs388.studytogethr.CoursesAdapter;
import com.codepath.cs388.studytogethr.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class PostsFragment extends Fragment {
    public static final String TAG = "PostsFragment";
    private RecyclerView rvCourses;
    protected CoursesAdapter adapter;
    protected List<String> allCourses;


    public PostsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvCourses = view.findViewById(R.id.rvCourses);
        allCourses = new ArrayList<>();
        adapter = new CoursesAdapter(getContext(), allCourses);
        rvCourses.setAdapter(adapter);
        rvCourses.setLayoutManager(new LinearLayoutManager(getContext()));
        rvCourses.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        queryPosts();
    }

    protected void queryPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_COURSE);
        try {
            int count  =  query.count();
            query.setLimit(count);
        } catch (ParseException e) {
            e.printStackTrace();
        }// else default 100 for back4app
        query.addDescendingOrder(Post.KEY_COURSE);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null){
                    Log.e(TAG, "error getting posts", e);
                    return;
                }
                Log.i(TAG, "Got back: " + posts);
                for (Post post : posts){
                    Log.i(TAG, "Course: " + post.getCourse() + " user: " + post.getUser());
                    if ( !allCourses.contains( post.getCourse() ) ){
                        allCourses.add(post.getCourse());
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
    }
}