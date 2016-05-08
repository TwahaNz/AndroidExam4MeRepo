package com.tnz.app.exam4me.services.Implementations;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.tnz.app.exam4me.domain.results.StudentExamResults;
import com.tnz.app.exam4me.repository.studentExamResults.Implementation.StudentExamResultsRepositoryImpl;
import com.tnz.app.exam4me.repository.studentExamResults.StudentExamResultsRepository;
import com.tnz.app.exam4me.services.FetchResultsService;

/**
 * Created by Admin on 2016/05/08.
 * Service responsible for fetching the students results fron the database
 * The reason I chose this service is because the Student can make inquries for their marks
 * Which will loaded from the service and return the request to the student
 */

//Bound Service

public class FetchResultsServiceImpl extends Service implements FetchResultsService {

    private IBinder localBinder = new LocalBinderService();
    private StudentExamResultsRepository resultsRepo;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    @Override
    public StudentExamResults getResults(Context context, String studentNumber) {
        resultsRepo = new StudentExamResultsRepositoryImpl(context);
        return resultsRepo.findByDetails(studentNumber);
    }


    public class LocalBinderService extends Binder {

        public FetchResultsServiceImpl getService() {
            return FetchResultsServiceImpl.this;
        }
    }
}
