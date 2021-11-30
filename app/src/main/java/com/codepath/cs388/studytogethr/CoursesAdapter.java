package com.codepath.cs388.studytogethr;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.ViewHolder> {

    private Context context;
    private List<String> courses;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.course_button, parent, false);
        return new ViewHolder(view);
    }

    public CoursesAdapter(Context context, List<String> courses) {
        this.context = context;
        this.courses = courses;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String course = courses.get(position);
        holder.bind(course);
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private Button btnCourse;
        private String thisCourse;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnCourse = itemView.findViewById(R.id.btnCourse);
            btnCourse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, PostsListActivity.class);
                    i.putExtra("course", thisCourse);
                    context.startActivity(i);
                }
            });
        }

        public void bind(String course) {
            btnCourse.setText(course);
            thisCourse = course;
        }
    }

    public void clear() {
        courses.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<String> list) {
        courses.addAll(list);
        notifyDataSetChanged();
    }
}
