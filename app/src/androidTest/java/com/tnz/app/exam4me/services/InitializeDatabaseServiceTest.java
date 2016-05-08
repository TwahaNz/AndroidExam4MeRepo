package com.tnz.app.exam4me.services;

import android.content.Intent;
import android.test.AndroidTestCase;

import com.tnz.app.exam4me.services.Implementations.InitializeDatabase;

/**
 * Created by Admin on 2016/05/08.
 * Service allows the automatic creation of all database tables
 */
public class InitializeDatabaseServiceTest extends AndroidTestCase {

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(getContext(), InitializeDatabase.class);
        getContext().startService(intent);
    }

    public void testStopService() {
        getContext().stopService(new Intent(getContext(), InitializeDatabase.class));
    }
}
