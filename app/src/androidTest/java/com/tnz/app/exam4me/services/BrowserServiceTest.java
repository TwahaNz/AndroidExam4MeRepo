package com.tnz.app.exam4me.services;

import android.content.Intent;
import android.test.AndroidTestCase;

import com.tnz.app.exam4me.services.Implementations.InitializeDatabase;

/**
 * Created by Admin on 2016/05/08.
 * Services allows the accesss to external browser for viewing content
 */

public class BrowserServiceTest extends AndroidTestCase {

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(getContext(), BrowserService.class);
        getContext().startService(intent);
    }

    public void testStopService() {
        getContext().stopService(new Intent(getContext(), BrowserService.class));
    }
}
