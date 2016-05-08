package com.tnz.app.exam4me.services;

import android.content.Intent;
import android.test.AndroidTestCase;

import com.tnz.app.exam4me.services.Implementations.InitializeDatabaseService;

/**
 * Created by Admin on 2016/05/08.
 * Service allows the automatic creation of all database tables
 * Instead of relying on classes to create the database tables individually
 * I decided to create all the tables in one go.
 */

//Started Service

public class InitializeDatabaseServiceServiceTest extends AndroidTestCase {

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(getContext(), InitializeDatabaseService.class);
        getContext().startService(intent);
    }

    public void testStopService() {
        getContext().stopService(new Intent(getContext(), InitializeDatabaseService.class));
    }
}
