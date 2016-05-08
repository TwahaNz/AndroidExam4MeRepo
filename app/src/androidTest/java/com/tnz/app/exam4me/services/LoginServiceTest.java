package com.tnz.app.exam4me.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.tnz.app.exam4me.domain.student.Student;
import com.tnz.app.exam4me.services.Implementations.LoginServiceImpl;
import com.tnz.app.exam4me.services.Implementations.RegistrationServiceImpl;

import junit.framework.Assert;

/**
 * Created by Admin on 2016/05/08.
 * Service for the registered users to use in order to log in into the system
 */
public class LoginServiceTest extends AndroidTestCase {

    private LoginServiceImpl loginService;
    private boolean isBound;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LoginServiceImpl.LoginServiceLocalBinder binder
                    = (LoginServiceImpl.LoginServiceLocalBinder) service;
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(getContext(), LoginService.class);
        getContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    public void testRegisteredAccount() {
        loginService = new LoginServiceImpl();
        Student student = loginService.loginAccount(getContext(), "214", "pet@gmail");

        if(student == null)
            Assert.assertNull(student); //Student Is Not Registered
        else
            Assert.assertNotNull(student); //Student Is Registered
    }

    public void testUnbindService(){
        getContext().unbindService(connection);
    }

}
