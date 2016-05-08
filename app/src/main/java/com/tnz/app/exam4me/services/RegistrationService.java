package com.tnz.app.exam4me.services;

import android.content.Context;

/**
 * Created by Admin on 2016/05/08.
 */
public interface RegistrationService {

    String registerAccount(Context context, String studentNumber, String studentEmail, String secrectCode);
    boolean isAccountrRegistered();
    boolean deregisterAccount();

}
