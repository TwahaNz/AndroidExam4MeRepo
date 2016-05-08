package com.tnz.app.exam4me.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.tnz.app.exam4me.conf.databases.DatabaseCreate;
import com.tnz.app.exam4me.domain.institute.Exam;
import com.tnz.app.exam4me.domain.results.StudentExamResults;
import com.tnz.app.exam4me.repository.studentExamResults.Implementation.StudentExamResultsRepositoryImpl;
import com.tnz.app.exam4me.repository.studentExamResults.StudentExamResultsRepository;
import com.tnz.app.exam4me.services.Implementations.FetchResultsServiceImpl;
import com.tnz.app.exam4me.services.Implementations.RegistrationServiceImpl;

import junit.framework.Assert;

/**
 * Created by Admin on 2016/05/08.
 * Service responsible for fetching the students results fron the database
 * The reason I chose this service is because the Student can make inquries for their marks
 * Which will loaded from the service and return the request to the student
 */

//Bound Service

public class FetchResultsServiceTest extends AndroidTestCase {

    private FetchResultsServiceImpl fetchResultsService = new FetchResultsServiceImpl();
    private StudentExamResultsRepository resultsRepo;
    private boolean isBound;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            FetchResultsServiceImpl.LocalBinderService binder =
                    (FetchResultsServiceImpl.LocalBinderService) service;
            fetchResultsService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(getContext(), FetchResultsServiceImpl.class);
        getContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    public void testFetchResults()
    {

        resultsRepo = new StudentExamResultsRepositoryImpl(getContext());
        Exam exam = Exam.getInstance();

        exam.writeExam(21430, 1);

        StudentExamResults studentExamResults = new StudentExamResults.Builder()
                .assignStudentNumber("21430")
                .assignExam(exam)
                .assignTerm(45)
                .assignTermOne(48)
                .assignTermThree(45)
                .assignTermFour(98)
                .build();

        resultsRepo.insert(studentExamResults);

        Assert.assertTrue(resultsRepo.findAll().size() > 0);

        studentExamResults = fetchResultsService.getResults(getContext(), "21430");

        Assert.assertNotNull(studentExamResults);
    }

}
