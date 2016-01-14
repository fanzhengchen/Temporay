package com.hcb.jingle;

import android.app.Application;

public class ThisApp extends Application{

    public static Application self;

    @Override
    public void onCreate() {
        super.onCreate();
        self = this;
    }
}
