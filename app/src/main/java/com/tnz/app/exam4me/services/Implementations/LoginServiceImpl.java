package com.tnz.app.exam4me.services.Implementations;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.tnz.app.exam4me.domain.settings.Registration;
import com.tnz.app.exam4me.domain.student.Student;
import com.tnz.app.exam4me.repository.registration.Implementation.RegistrationRepositoryImpl;
import com.tnz.app.exam4me.repository.registration.RegistrationRepository;
import com.tnz.app.exam4me.services.LoginService;

/**
 * Created by Admin on 2016/05/08.
 */
public class LoginServiceImpl extends Service implements LoginService {

    private final IBinder localBinder = new LoginServiceLocalBinder();

    private RegistrationRepository regRepo;

    @Override
    public Student loginAccount(Context context, String studentNumber, String email) {
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
