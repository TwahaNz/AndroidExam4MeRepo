package com.tnz.app.exam4me.factories.student;


import com.tnz.app.exam4me.domain.student.NonResidentStudent;


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
