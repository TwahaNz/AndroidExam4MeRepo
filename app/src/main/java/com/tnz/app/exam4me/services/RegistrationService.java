package com.tnz.app.exam4me.services;

import android.content.Context;

/**
 * Created by Admin on 2016/05/08.
 * Service for the registration of users to use the system. In order for users to
 * use the system they will first be required to register.
 */

//Bound Service

public interface RegistrationService {

    String registerAccount(Context context, String studentNumber, String studentEmail, String secrectCode);
    boolean isAccountrRegistered();
    boolean deregisterAccount();

}
