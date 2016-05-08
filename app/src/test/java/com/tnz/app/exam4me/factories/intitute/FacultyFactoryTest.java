package com.tnz.app.exam4me.factories.intitute;

import com.tnz.app.exam4me.domain.institute.Faculty;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Admin on 2016/04/16.
 */
public class FacultyFactoryTest {

    @Test
    public void testCreate() throws Exception {

        Faculty itFaculty = new Faculty.Builder()
                .assignFacultyName("Informatic and Design")
                .assignFacultyLocation("Cape Town")
                .build();

        Assert.assertEquals("Informatic and Design",itFaculty.getFacultyName());
    }

    @Test
    public void testUpdate() throws Exception {

        Faculty itFaculty = new Faculty.Builder()
                .assignFacultyName("Informatic and Design")
                .assignFacultyLocation("Cape Town")
                .build();


        Faculty newItFaculty = new Faculty.Builder()
                .copyFaculty(itFaculty)
                .assignFacultyLocation("Belville")
                .build();


        Assert.assertEquals("Belville", newItFaculty.getFacultyLocation());
    }
}
