package com.codepath.cs388.studytogethr;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("zUofGngT0QB4WQGKNLPQIKyRqyxn17k6wePyDkvi")
                .clientKey("yEU8jvJ4mHQ3M6lt8dRnEBkghXnDxnlw7o4KG0uP")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
