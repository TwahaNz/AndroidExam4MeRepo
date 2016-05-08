package com.tnz.app.exam4me.services;

/**
 * Created by Admin on 2016/05/08.
 * Services allows the accesss to external browser for viewing content
 * The reason I chose to do this service is because the user will be reffered to a browser
 * that I don't need to know about
 *
 */

//Started Service

public interface BrowserService {

    void openLink(String url);
}
