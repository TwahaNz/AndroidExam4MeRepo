package com.tnz.app.exam4me.services;

import android.content.Intent;
import android.test.AndroidTestCase;

/**
 * Created by Admin on 2016/05/08.
 * Services allows the accesss to external browser for viewing content
 * The reason I chose to do this service is because the user will be reffered to a browser
 * that I don't need to know about
 *
 */

//Started Service

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
