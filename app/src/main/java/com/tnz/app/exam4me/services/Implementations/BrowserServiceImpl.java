package com.tnz.app.exam4me.services.Implementations;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.tnz.app.exam4me.conf.databases.DatabaseCreate;
import com.tnz.app.exam4me.services.BrowserService;

/**
 * Created by Admin on 2016/05/08.
 * Services allows the accesss to external browser for viewing content
 * The reason I chose to do this service is because the user will be reffered to a browser
 * that I don't need to know about
 *
 */

//Started Service

public class BrowserServiceImpl extends Service implements BrowserService {

    private Intent intent;

    @Override
    public void openLink(String url) {
        this.intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String url = intent.getStringExtra("URL");
        openLink(url);
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
