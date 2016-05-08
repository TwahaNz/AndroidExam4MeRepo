package com.tnz.app.exam4me.services;

import android.content.Intent;
import android.test.AndroidTestCase;

import com.tnz.app.exam4me.services.Implementations.LoggedInUserServiceImpl;
import com.tnz.app.exam4me.services.Implementations.LoginServiceImpl;

/**
 * Created by Admin on 2016/05/08.
 */
public class LoggedInUserServiceTest extends AndroidTestCase {

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(getContext(), LoginServiceImpl.class);
        getContext().startService(intent);
    }

    public void testStopService() {
        getContext().stopService(new Intent(getContext(), LoggedInUserServiceImpl.class));
    }
}
