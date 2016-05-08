package com.tnz.app.exam4me.services;

import android.content.Intent;
import android.test.AndroidTestCase;

/**
 * Created by Admin on 2016/05/08.
 * Service to allow registered users to update their profiles.
 * The reason this is a bound service is because the user need not
 * interact with the update
 */

//Bound Service

public class UpdateProfileServiceTest extends AndroidTestCase {

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(getContext(), UpdateProfileService.class);
        intent.putExtra("Student Number", "214");
        intent.putExtra("Student Email", "peter");

        getContext().startService(intent);
    }

    public void testStopService() {
        getContext().stopService(new Intent(getContext(), UpdateProfileService.class));
    }
}
