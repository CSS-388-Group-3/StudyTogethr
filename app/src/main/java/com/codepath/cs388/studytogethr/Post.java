package com.codepath.cs388.studytogethr;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Post")
public class Post extends ParseObject {
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_USER = "user";
    public static final String KEY_CREATED_AT = "createdAt";
    public static final String KEY_COURSE = "class_professor";
    public static final String KEY_FOLDER = "class_folder";


    public String getDescription (){
        return getString(KEY_DESCRIPTION);
    }
    public void setDescription (String description) {
        put(KEY_DESCRIPTION, description);
    }

    public ParseFile getImage(){
        return getParseFile(KEY_IMAGE);
    }
    public void setImage(ParseFile parseFile) {
        put(KEY_IMAGE, parseFile);
    }

    public ParseUser getUser(){
        return getParseUser(KEY_USER);
    }
    public void setUser (ParseUser user){
        put(KEY_USER, user);
    }

    public String getCourse() {
        return getString(KEY_COURSE);
    }
    public void setCourse(String course_professor){put(KEY_COURSE, course_professor);}

    public String getFolder() {
        return getString(KEY_FOLDER);
    }
    public void setFolder(String course_folder){put(KEY_FOLDER, course_folder);}




}
