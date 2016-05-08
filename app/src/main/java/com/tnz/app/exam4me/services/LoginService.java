package com.tnz.app.exam4me.services;

import android.content.Context;

import com.tnz.app.exam4me.domain.student.Student;

/**
 * Created by Admin on 2016/05/08.
 */
public interface LoginService {

    Student loginAccount(Context conext, String studentNumber, String email);
}
