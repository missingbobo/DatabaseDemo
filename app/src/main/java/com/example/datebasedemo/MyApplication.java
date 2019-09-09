package com.example.datebasedemo;

import android.app.Application;

public class MyApplication extends Application {
    private static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }
}
