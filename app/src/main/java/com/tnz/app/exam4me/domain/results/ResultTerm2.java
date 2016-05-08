package com.tnz.app.exam4me.domain.results;


import com.tnz.app.exam4me.domain.institute.Exam;

import java.io.Serializable;


/**
 * Write a description of class ResultTerm2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ResultTerm2 extends Results implements Serializable
{
    @Override
    public int[] handleNextTermRequest(String studentNumber, int termRequest){
        if (termRequest == 2)
            return Exam.getInstance().getTermResult(studentNumber, termRequest);
        else
        if (nextTermResults != null)
            return nextTermResults.handleNextTermRequest(studentNumber,termRequest);
        else
            return null;
    }
}
