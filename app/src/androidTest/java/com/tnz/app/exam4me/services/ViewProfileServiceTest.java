package com.tnz.app.exam4me.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.tnz.app.exam4me.conf.databases.DatabaseCreate;
import com.tnz.app.exam4me.services.Implementations.RegistrationServiceImpl;
import com.tnz.app.exam4me.services.Implementations.ViewProfileServiceImpl;

import junit.framework.Assert;

/**
 * Created by Admin on 2016/05/08.
 * This service will allow the registered user to view their current profile
 * information which they could change at anytime
 */

//Bound Service

public class ViewProfileServiceTest extends AndroidTestCase {

    private ViewProfileServiceImpl viewProfileService;
    private boolean isBound;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ViewProfileServiceImpl.ViewProfileServiceLocalBinder binder
                    = (ViewProfileServiceImpl.ViewProfileServiceLocalBinder) service;
            viewProfileService = binder.getService();
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

        viewProfileService = new ViewProfileServiceImpl();
        String profileDetails = viewProfileService.viewMyProfile(getContext(), "214", "pet@gmail");
        Assert.assertNotNull(profileDetails);
    }

}
