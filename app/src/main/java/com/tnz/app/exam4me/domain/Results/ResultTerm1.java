package com.tnz.app.exam4me.domain.Results;


import com.tnz.app.exam4me.domain.Institute.Exam;

import java.io.Serializable;


/**
 * Write a description of class ResulTerm1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ResultTerm1 extends Results implements Serializable
{
    @Override
    public int[] handleNextTermRequest(String studentNumber, int termRequest){
        if (termRequest == 1) {
            return Exam.getInstance().getTermResult(studentNumber,termRequest);
        }
        else
            if (nextTermResults != null)
                return nextTermResults.handleNextTermRequest(studentNumber,termRequest);
            else
                return null;
    }
}
