package com.tnz.app.exam4me.services;

import android.content.Context;

import com.tnz.app.exam4me.domain.settings.Registration;

/**
 * Created by Admin on 2016/05/08.
 * Service to allow registered users to update their profiles.
 * The reason this is a bound service is because the user need not
 * interact with the update
 */

//Bound Service

public interface UpdateProfileService {

    void updateDetails(Context context, Registration registration);
}
