package com.tnz.app.exam4me.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.tnz.app.exam4me.conf.Utilities.App;
import com.tnz.app.exam4me.conf.Utilities.DatabaseTables;
import com.tnz.app.exam4me.conf.databases.DatabaseCreate;
import com.tnz.app.exam4me.services.Implementations.RegistrationServiceImpl;

import junit.framework.Assert;

/**
 * Created by Admin on 2016/05/08.
 * Service for the registration of users to use the system. In order for users to
 * use the system they will first be required to register.
 */

//Bound Service

public class RegistrationServiceTest extends AndroidTestCase {

    private RegistrationServiceImpl registrationService;
    private boolean isBound;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            RegistrationServiceImpl.RegistrationServiceLocalBinder binder
                    = (RegistrationServiceImpl.RegistrationServiceLocalBinder) service;
            registrationService = binder.getService();
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
            Intent intent = new Intent(getContext(), RegistrationServiceImpl.class);
            getContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    public void testRegisteredAccount() {

        DatabaseCreate.getInstance(getContext()).createAllTables();

        registrationService = new RegistrationServiceImpl();
        String registrationStatus = registrationService.registerAccount(getContext(), "214", "pet@gmail", "12345");
        Assert.assertEquals("ACTIVATED", registrationStatus);
    }
}
