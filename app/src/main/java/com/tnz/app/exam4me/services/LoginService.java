package com.tnz.app.exam4me.services;

import android.content.Context;

import com.tnz.app.exam4me.domain.student.Student;

/**
 * Created by Admin on 2016/05/08.
 * This service will be responsible for allowing the Student to login into the
 * system. It will also. It is a bound service because the user has to be notifies
 * at all times during the login phase. Whether successful or not
 */

//Bound Service

public interface LoginService {

    Student loginAccount(Context conext, String studentNumber, String email);
}
