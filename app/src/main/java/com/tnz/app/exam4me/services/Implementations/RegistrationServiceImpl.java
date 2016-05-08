package com.tnz.app.exam4me.services.Implementations;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.tnz.app.exam4me.conf.Utilities.DomainState;
import com.tnz.app.exam4me.domain.settings.Registration;
import com.tnz.app.exam4me.factories.settings.RegistrationFactory;
import com.tnz.app.exam4me.repository.registration.Implementation.RegistrationRepositoryImpl;
import com.tnz.app.exam4me.repository.registration.RegistrationRepository;
import com.tnz.app.exam4me.services.RegistrationService;

/**
 * Created by Admin on 2016/05/08.
 * Service for the registration of users to use the system. In order for users to
 * use the system they will first be required to register.
 */

//Bound Service

public class RegistrationServiceImpl extends Service implements RegistrationService{

    private final IBinder localBinder = new RegistrationServiceLocalBinder();

    private RegistrationRepository regRepo;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    @Override
    public String registerAccount(Context context, String studentNumber, String studentEmail, String secrectCode) {

        Registration accountReg = RegistrationFactory.getRegistrion(studentNumber, studentEmail, secrectCode);
        createRegistration(context, accountReg);
        return DomainState.ACTIVATED.name();
    }

    @Override
    public boolean isAccountrRegistered() {
        return regRepo.findAll().size() > 0;
    }

    @Override
    public boolean deregisterAccount() {
        return (regRepo.deleteAll() > 0);
    }

    private Registration createRegistration(Context context, Registration registration){
        regRepo = new RegistrationRepositoryImpl(context);
        return regRepo.insert(registration);
    }

    public class RegistrationServiceLocalBinder extends Binder {
        public RegistrationServiceImpl getService() {return RegistrationServiceImpl.this;}
    }
}
