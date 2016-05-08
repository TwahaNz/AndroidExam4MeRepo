package com.tnz.app.exam4me.factories.settings;

import com.tnz.app.exam4me.domain.settings.Registration;

/**
 * Created by Admin on 2016/05/08.
 */
public class RegistrationFactory {

    public static Registration getRegistrion (String studentNumber, String email, String secretCode) {
         return  new Registration.Builder()
                 .assignStudentNumber(studentNumber)
                 .assignStudentEmail(email)
                 .assignSecrectCode(secretCode)
                 .build();
    }
}
