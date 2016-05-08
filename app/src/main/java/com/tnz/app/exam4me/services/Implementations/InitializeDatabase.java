package com.tnz.app.exam4me.services.Implementations;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.tnz.app.exam4me.conf.databases.DatabaseCreate;

/**
 * Created by Admin on 2016/05/08.
 */
public class InitializeDatabase extends Service {

    private DatabaseCreate databaseCreate;
    private String databse_status;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        databaseCreate.createAllTables();
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
}
