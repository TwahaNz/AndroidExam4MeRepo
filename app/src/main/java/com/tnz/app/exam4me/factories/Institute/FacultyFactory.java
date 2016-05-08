package com.tnz.app.exam4me.factories.Institute;

import com.tnz.app.exam4me.domain.institute.Faculty;

public class FacultyFactory
{
    public static Faculty getFaculty(String facultyName, String facultyLocation){
        return new Faculty.Builder()
                .assignFacultyName(facultyName)
                .assignFacultyName(facultyLocation)
                .build();
    }
}
