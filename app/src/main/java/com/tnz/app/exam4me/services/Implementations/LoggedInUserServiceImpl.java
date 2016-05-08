package com.tnz.app.exam4me.services.Implementations;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.tnz.app.exam4me.services.LoggedInUserService;

/**
 * Created by Admin on 2016/05/08.
 * A simple service that will keep record of the goings and logins of the
 * Student using the system
 *
 */

//Started Service

public class LoggedInUserServiceImpl extends Service implements LoggedInUserService {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String studentNumber = intent.getStringExtra("Student Number");
        keepLog(studentNumber);
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy (){
        super.onDestroy();
    }

    @Override
    public void keepLog(String studnetNumber) {
        //Write Student Login To Log
    }
}
