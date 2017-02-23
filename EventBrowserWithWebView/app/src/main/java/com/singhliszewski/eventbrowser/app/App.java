package com.singhliszewski.eventbrowser.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by Ryanliszewski on 12/13/16.
 */

public class App extends Application {

    private static App instance;

    public static Context getContext(){
        return instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }

}
