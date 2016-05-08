package com.tnz.app.exam4me.factories.intitute;

import com.tnz.app.exam4me.domain.institute.Faculty;
import com.tnz.app.exam4me.domain.institute.Lecturer;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Admin on 2016/04/16.
 */
public class LecturerFactoryTest {

    @Test
    public void testCreate() throws Exception {

        Lecturer itLecturer = new Lecturer.Builder()
                .assignName("Prof. James")
                .assignStaffID("2154")
                .assignRoomNumber("1.13")
                .assignFaculty(new Faculty.Builder()
                               .assignFacultyName("Informatic and Design")
                               .assignFacultyLocation("Cape Town").build())
                .build();

        Assert.assertEquals("Prof. James",itLecturer.getName());
        Assert.assertEquals("Informatic and Design", itLecturer.getFaculty().getFacultyName());

    }

    @Test
    public void testUpdate() throws Exception {

        Lecturer accLecturer = new Lecturer.Builder()
                .assignName("Prof. James")
                .assignStaffID("2154")
                .assignRoomNumber("1.13")
                .assignFaculty(new Faculty.Builder()
                        .assignFacultyName("Informatic And Design")
                        .assignFacultyLocation("Cape Town").build())
                .build();

        Lecturer newAccLecturer = new Lecturer.Builder()
                .copyLecturer(accLecturer)
                .assignRoomNumber("2.35")
                .build();

        Assert.assertEquals("2.35",newAccLecturer.getRoomNumber());
    }
}
