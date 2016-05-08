package com.tnz.app.exam4me.implementations.Impl;

import com.tnz.app.exam4me.implementations.FeesImpl;


/**
 * Write a description of class TuitionImpl here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TuitionFee implements FeesImpl
{
    private float tuitionFee = (float) 16000.00;
    
    private static TuitionFee tuitionInstance;
    
    private TuitionFee(){}
    
    public static TuitionFee getInstance(){  
        return new TuitionFee();
    }
   
    @Override
    public float retrieveStudentCosts(String course){

        if (course.trim().equals("Information Technology"))
            return  (float) 16000.00;
        if (course.trim().equals("Chemistry"))
            return (float) 22000.00;
        if (course.trim().equals("Accounting"))
            return (float) 14000.00;
        if (course.trim().equals("Design"))
            return (float) 18000.00;

        return (float) 0.0;
    }

    @Override
    public void payStudentFee(float amount) {
        tuitionFee -= amount;
    }

    @Override
    public float getStudentFee() {
        return tuitionFee;
    }
}
