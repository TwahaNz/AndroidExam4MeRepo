package com.tnz.app.exam4me.Factory;

import com.tnz.app.exam4me.domain.Institute.Exam;
import com.tnz.app.exam4me.domain.Institute.Faculty;
import com.tnz.app.exam4me.domain.Student.NonResidentStudent;
import com.tnz.app.exam4me.domain.Student.Student;
import com.tnz.app.exam4me.factories.Results.ResultsFactory;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Admin on 2016/04/17.
 */
public class ResultsFactoryTest {


    @Test
    public void testCreate() throws Exception {

        //Student's marks have not been uploaded
        //so results should return back null

        int[] termResults = ResultsFactory.getStudentTermResults("21545151", 1);

        Assert.assertNull(termResults);
    }

    @Test
    public void testResults() throws Exception {

        Student itStudent = new NonResidentStudent.Builder()
                .studentNumber("21628189")
                .studentName("Peterson")
                .build()
                .assignStudentFee("Information Technology");

        itStudent.writeExam(1);

        int[] termResults = ResultsFactory.getStudentTermResults(String.valueOf(itStudent.getStudentNumber()), 1);

        System.out.print("\n Student Name: " + itStudent.getStudentName()
                          + "\t\t Term 1 Results \n---------------------------------------------------\n");

        for (int subjectMark : termResults)
            System.out.println(" " + subjectMark);
    }
}
