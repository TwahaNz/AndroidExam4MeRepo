package com.tnz.app.exam4me.services.Implementations;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.view.View;

import com.tnz.app.exam4me.domain.settings.Registration;
import com.tnz.app.exam4me.domain.student.Student;
import com.tnz.app.exam4me.repository.registration.Implementation.RegistrationRepositoryImpl;
import com.tnz.app.exam4me.repository.registration.RegistrationRepository;
import com.tnz.app.exam4me.services.ViewProfileService;

/**
 * Created by Admin on 2016/05/08.
 * This service will allow the registered user to view their current profile
 * information which they could change at anytime
 */

//Bound Service

public class ViewProfileServiceImpl extends Service implements ViewProfileService {

    private IBinder localBinder = new ViewProfileServiceLocalBinder();
    private RegistrationRepository registrationRepository;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    @Override
    public String viewMyProfile(Context context, String studentNumber, String email) {
        registrationRepository = new RegistrationRepositoryImpl(context);
        Student regStudent = registrationRepository.findByDetails(studentNumber, email);
        return "Student Name: " + studentNumber + "\t Student Email: " + email;
    }

    public class ViewProfileServiceLocalBinder extends Binder {

        public ViewProfileServiceImpl getService() {
            return ViewProfileServiceImpl.this;
        }

    }
}
