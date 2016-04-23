package com.tnz.app.exam4me.factories.Student;

import com.tnz.app.exam4me.domain.Student.ResidentStudent;

/**
 * Write a description of class ResidentStudentFactory here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ResidentStudentFactory
{
    public static ResidentStudent getResidentStudent(String studentName, String studentNumber){
        return new ResidentStudent.Builder()
                .studentName(studentName)
                .studentNumber(studentNumber)
                .build();
    }
}
