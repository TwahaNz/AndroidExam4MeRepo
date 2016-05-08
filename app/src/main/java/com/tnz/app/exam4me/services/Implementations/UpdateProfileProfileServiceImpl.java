package com.tnz.app.exam4me.services.Implementations;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.tnz.app.exam4me.conf.databases.DatabaseCreate;
import com.tnz.app.exam4me.domain.settings.Registration;
import com.tnz.app.exam4me.factories.settings.RegistrationFactory;
import com.tnz.app.exam4me.repository.registration.Implementation.RegistrationRepositoryImpl;
import com.tnz.app.exam4me.repository.registration.RegistrationRepository;
import com.tnz.app.exam4me.services.UpdateProfileService;

import junit.framework.Assert;

/**
 * Created by Admin on 2016/05/08.
 */
public class UpdateProfileProfileServiceImpl extends Service implements UpdateProfileService {

    private DatabaseCreate databaseCreate;
    private String databse_status;
    private Registration registration;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void updateDetails(Context context, Registration registration) {
        RegistrationRepository regRepo = new RegistrationRepositoryImpl(context);
        registration = regRepo.update(registration);
        Assert.assertNotNull(registration);
        Toast.makeText(UpdateProfileProfileServiceImpl.this, "Profile Updated", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String studentNumber = intent.getStringExtra("Student Number");
        String studentEmail = intent.getStringExtra("Student Email");

        Registration registration = RegistrationFactory.getRegistrion(studentNumber,
                studentEmail, "");

        updateDetails(getApplicationContext(), registration);

        return START_STICKY;
    }

    @Override
    public void onDestroy (){
        super.onDestroy();
    }
}
