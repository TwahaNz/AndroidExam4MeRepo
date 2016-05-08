package com.tnz.app.exam4me.services.Implementations;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.tnz.app.exam4me.conf.databases.DatabaseCreate;
import com.tnz.app.exam4me.domain.settings.Registration;
import com.tnz.app.exam4me.domain.student.Student;
import com.tnz.app.exam4me.repository.registration.Implementation.RegistrationRepositoryImpl;
import com.tnz.app.exam4me.repository.registration.RegistrationRepository;
import com.tnz.app.exam4me.services.LoginService;

/**
 * Created by Admin on 2016/05/08.
 * This service will be responsible for allowing the Student to login into the
 * system. It will also. It is a bound service because the user has to be notifies
 * at all times during the login phase. Whether successful or not
 */

//Bound Service

public class LoginServiceImpl extends Service implements LoginService {

    private final IBinder localBinder = new LoginServiceLocalBinder();

    private RegistrationRepository regRepo;

    @Override
    public Student loginAccount(Context context, String studentNumber, String email) {

        DatabaseCreate.getInstance(context).createAllTables();

        regRepo = new RegistrationRepositoryImpl(context);
        return regRepo.findByDetails(studentNumber, email);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class LoginServiceLocalBinder extends Binder {
        public LoginServiceImpl getService() {
            return LoginServiceImpl.this;
        }
    }
}
