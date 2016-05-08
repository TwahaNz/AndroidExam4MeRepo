package com.tnz.app.exam4me.services;

import android.content.Context;

import com.tnz.app.exam4me.domain.results.StudentExamResults;

/**
 * Created by Admin on 2016/05/08.
 * Service responsible for fetching the students results fron the database
 * The reason I chose this service is because the Student can make inquries for their marks
 * Which will loaded from the service and return the request to the student
 */

//Bound Service

public interface FetchResultsService {

    StudentExamResults getResults(Context context, String studentNumber);
}
