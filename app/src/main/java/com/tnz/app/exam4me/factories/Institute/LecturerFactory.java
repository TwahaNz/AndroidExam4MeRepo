package com.tnz.app.exam4me.factories.Institute;

import com.tnz.app.exam4me.domain.institute.Faculty;
import com.tnz.app.exam4me.domain.institute.Lecturer;

public class LecturerFactory
{
    public static Lecturer getLecturer(String[] details, Faculty faculty){
        return new Lecturer.Builder()
                .assignName(details[0])
                .assignStaffID(details[1])
                .assignRoomNumber(details[2])
                .assignFaculty(faculty)
                .build();
    }
}
