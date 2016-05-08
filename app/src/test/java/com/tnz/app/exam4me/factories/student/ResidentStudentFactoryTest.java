package com.tnz.app.exam4me.factories.student;

import com.tnz.app.exam4me.domain.student.ResidentStudent;
import com.tnz.app.exam4me.domain.student.Student;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Admin on 2016/04/17.
 */
public class ResidentStudentFactoryTest {

    @Test
    public void testCreate() throws Exception {

        Student itStudent = new ResidentStudent.Builder()
                .studentName("John")
                .studentNumber("212323546").build();

        Assert.assertEquals("John",itStudent.getStudentName());

    }

    @Test
    public void testUpdate() throws Exception {

        Student accStudent = new ResidentStudent
                .Builder().studentName("John")
                .studentNumber("215642316")
                .build();

        Student newAccStudent = new ResidentStudent.Builder()
                .copyResidentStudent((
                        ResidentStudent) accStudent)
                .studentName("Kevin Jr")
                .build();

        Assert.assertEquals("Kevin Jr",newAccStudent.getStudentName());
    }
}
