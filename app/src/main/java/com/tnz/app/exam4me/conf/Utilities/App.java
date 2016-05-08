package com.tnz.app.exam4me.conf.Utilities;

import android.app.Application;
import android.content.Context;

/**
 * Created by Admin on 2016/05/08.
 */
public class App extends Application {

    private static Context context;
    private static App singleton;

    @Override
    public void onCreate() {
        super.onCreate();
        App.context = this.getApplicationContext();
        singleton = this;
    }

    public static synchronized App getInstance() {
        return singleton;
    }

    public static Context getContext() {
        return App.context;
    }
}
