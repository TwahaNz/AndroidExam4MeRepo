package com.tnz.app.exam4me.services;

import android.content.Context;

/**
 * Created by Admin on 2016/05/08.
 * This service will allow the registered user to view their current profile
 * information which they could change at anytime
 */

//Bound Service

public interface ViewProfileService {

    String viewMyProfile(Context context, String studNumber, String email);
}
