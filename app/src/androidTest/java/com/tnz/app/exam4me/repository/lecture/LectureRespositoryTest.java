package com.tnz.app.exam4me.repository.lecture;

import android.test.AndroidTestCase;

import com.tnz.app.exam4me.conf.databases.DatabaseCreate;
import com.tnz.app.exam4me.domain.institute.Faculty;
import com.tnz.app.exam4me.domain.institute.Lecturer;
import com.tnz.app.exam4me.repository.lecturer.Implementation.LecturerRepositoryImpl;
import com.tnz.app.exam4me.repository.lecturer.LecturerRepository;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Admin on 2016/04/23.
 */
public class LectureRespositoryTest extends AndroidTestCase {

    private static final String TAG = "LECTURER TEST";
    private static long id;

    public void testCreateReadUpadateDelete() {

        //DROP DATABASE
        DatabaseCreate.getInstance(this.getContext()).deleteDatabase();

        //Create the Student Table And Create The Database File
        LecturerRepository lecturerRepository = new LecturerRepositoryImpl(this.getContext());

        //INSERT STUDENT
        Lecturer lecturer = new Lecturer.Builder()
                .assignName("Mr. Peter")
                .assignRoomNumber("1.14")
                .assignFaculty(new Faculty.Builder().assignFacultyName("Information Technology").build())
                .build();


        Lecturer insertedLecturer = lecturerRepository.insert(lecturer);
        id = insertedLecturer.getId();
        Assert.assertNotNull(TAG + " INSERT", insertedLecturer);

        //READ ALL
        Set<Lecturer> lecturers = lecturerRepository.findAll();
        Assert.assertTrue(TAG + " READ ALL", lecturers.size() > 0);


        //READ ENTITY
        Lecturer newlecturer = lecturerRepository.findById(id);
        Assert.assertNotNull(TAG + " READ ENTITY", newlecturer);

        //UPDATE ENTITY
        Lecturer updatedLecturer = new Lecturer.Builder()
                .copyLecturer(newlecturer)
                .assignRoomNumber("2.36")
                .build();

        updatedLecturer = lecturerRepository.update(updatedLecturer);
        updatedLecturer = lecturerRepository.findById(updatedLecturer.getId());

        Assert.assertEquals(TAG + " UPDATE ", "2.36", updatedLecturer.getRoomNumber());

        //DELETE ENTITY
        Lecturer deleteLecturer = lecturerRepository.delete(updatedLecturer);
        deleteLecturer = lecturerRepository.findById(deleteLecturer.getId());
        Assert.assertNull(TAG + " DELETE", deleteLecturer);

        //DELETE ALL
        int rowsDeleted = lecturerRepository.deleteAll();
        Assert.assertEquals(TAG + " DELETE ALL", rowsDeleted, 0);
    }
}
