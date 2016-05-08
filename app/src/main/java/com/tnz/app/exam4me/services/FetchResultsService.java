package com.tnz.app.exam4me.services;

import android.content.Context;

import com.tnz.app.exam4me.domain.results.StudentExamResults;

/**
 * Created by Admin on 2016/05/08.
 */
public interface FetchResultsService {

    StudentExamResults getResults(Context context, String studentNumber);
}
