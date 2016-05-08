package com.tnz.app.exam4me.domain.institute;


import com.tnz.app.exam4me.factories.results.ResultsFactory;

import java.io.Serializable;

public class RetrievedUploadedResults implements Serializable
{
    private static RetrievedUploadedResults retrievedUploadedResults;

    private RetrievedUploadedResults(){}

    public static RetrievedUploadedResults getInstance() {
        if (retrievedUploadedResults == null)
            return new RetrievedUploadedResults();
        return retrievedUploadedResults;
    }

    public int[] fecthUploadedResults(String studentNumber, int term){
        return ResultsFactory.getStudentTermResults(studentNumber,term);
    }
}
