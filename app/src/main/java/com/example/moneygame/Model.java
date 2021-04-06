package com.example.moneygame;

import android.app.Application;
import android.view.Display;

public class Model {

    private static Model sInstance = null;

    public static Model getInstance(Application application) {
        if (sInstance == null) {
            sInstance = new Model(application);
        }
        return sInstance;
    }

    private final Application mApplication;

    private Model(Application application) {
        mApplication = application;
    }

    public Application getApplication() {
        return mApplication;
    }

    public void login(String email, String password) {

    }

}
