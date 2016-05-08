package com.tnz.app.exam4me.factories.student;

import com.tnz.app.exam4me.domain.student.NonResidentStudent;
import com.tnz.app.exam4me.domain.student.Student;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Admin on 2016/04/16.
 */
public class NonResidentStudentTypeFactoryTest {

    @Test
    public void testCreate() throws Exception {

        Student itStudent = new NonResidentStudent.Builder().studentName("Peter").studentNumber("214123546").build();

        itStudent.getStudentNumber();

        Assert.assertEquals("Peter",itStudent.getStudentName());

    }

    @Test
    public void testUpdate() throws Exception {

        Student accStudent = new NonResidentStudent.Builder().studentName("Kevin").studentNumber("21564876").build();

        Student newAccStudent = new NonResidentStudent.Builder()
                .copyNonResidentStudent((NonResidentStudent) accStudent)
                .studentName("Kevin Jr")
                .build();

        Assert.assertEquals("Kevin Jr",newAccStudent.getStudentName());
    }
}
