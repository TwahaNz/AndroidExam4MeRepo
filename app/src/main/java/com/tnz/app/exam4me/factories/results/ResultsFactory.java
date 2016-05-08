package com.tnz.app.exam4me.factories.results;

import com.tnz.app.exam4me.domain.results.ResultTerm1;
import com.tnz.app.exam4me.domain.results.ResultTerm2;
import com.tnz.app.exam4me.domain.results.ResultTerm3;
import com.tnz.app.exam4me.domain.results.ResultTerm4;
import com.tnz.app.exam4me.domain.results.Results;


public class ResultsFactory {

    private static ResultTerm1 term1;
    private static ResultTerm2 term2;
    private static ResultTerm3 term3;
    private static ResultTerm4 term4;
    private static Results resultChain;

    public static int[] getStudentTermResults(String studentNumber, int term) {
        resultChain = assignTermChain();
        return resultChain.handleNextTermRequest(studentNumber, term);
    }

    public static Results assignTermChain() {

        term1 = new ResultTerm1();
        term2 = new ResultTerm2();
        term3 = new ResultTerm3();
        term4 = new ResultTerm4();

        term1.assignNextTermResults(term2);
        term2.assignNextTermResults(term3);
        term3.assignNextTermResults(term4);

        return term1;
    }
}
