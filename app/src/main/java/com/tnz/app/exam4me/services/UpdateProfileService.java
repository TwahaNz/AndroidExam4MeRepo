package com.tnz.app.exam4me.services;

import android.content.Context;

import com.tnz.app.exam4me.domain.settings.Registration;

/**
 * Created by Admin on 2016/05/08.
 */
public interface UpdateProfileService {

    void updateDetails(Context context, Registration registration);
}
