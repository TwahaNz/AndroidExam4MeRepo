package com.tnz.app.exam4me.factories.Student;


import com.tnz.app.exam4me.domain.Student.NonResidentStudent;
import com.tnz.app.exam4me.services.FeesImpl;
import com.tnz.app.exam4me.services.Impl.TuitionFee;


/**
 * Write a description of class NonResidentStudentFactory here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NonResidentStudentFactory
{
    public static NonResidentStudent getNonResidentStudent(String studentName, String studentNumber){
        return new NonResidentStudent.Builder()
                .studentName(studentName)
                .studentNumber(studentNumber)
                .build();
    }
}
