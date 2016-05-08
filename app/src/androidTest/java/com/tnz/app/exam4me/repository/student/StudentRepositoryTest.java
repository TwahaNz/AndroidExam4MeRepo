package com.tnz.app.exam4me.repository.student;

import android.test.AndroidTestCase;

import com.tnz.app.exam4me.conf.databases.DatabaseCreate;
import com.tnz.app.exam4me.domain.student.NonResidentStudent;
import com.tnz.app.exam4me.domain.student.Student;
import com.tnz.app.exam4me.repository.student.Implementations.StudentRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Admin on 2016/04/23.
 */

public class StudentRepositoryTest extends AndroidTestCase {

    private static final String TAG = "STUDENT TEST";
    private static long id;

    public void testCreateReadUpadateDelete(){

        //DROP DATABASE
        DatabaseCreate.getInstance(this.getContext()).deleteDatabase();

        //Create the Student Table And Create The Database File
        StudentRepository studentRepository = new StudentRepositoryImpl(this.getContext());

        //INSERT STUDENT
        Student student = new NonResidentStudent.Builder()
                .studentNumber("21430")
                .studentName("Twaha")
                .build()
                .assignFaculty("Information Technology", "Cape Town");



        Student insertStudent = studentRepository.insert(student);
        id = insertStudent.getId();
        Assert.assertNotNull(TAG + " CREATE", insertStudent);


        //READ ALL
        Set<Student> students = studentRepository.findAll();
        Assert.assertTrue(TAG + " READ ALL", students.size() > 0);


        //READ ENTITY
        Student entity = studentRepository.findById(id);
        Assert.assertNotNull(TAG + " READ ENTITY", entity);

        //UPDATE ENTITY
        Student updateStudent = new NonResidentStudent.Builder()
                .copyNonResidentStudent((NonResidentStudent) entity)
                .studentName("Twaha Nzeyimana")
                .build();

        studentRepository.update(updateStudent);

        Student updatedStudent = studentRepository.findById(updateStudent.getId());

        Assert.assertEquals(TAG + " UPDATE STUDENT", updatedStudent.getStudentName(), "Twaha Nzeyimana");

        //DELETE ENTITY
        studentRepository.delete(updatedStudent);
        Student deletedStudent = studentRepository.findById(id);
        Assert.assertNull(TAG + " DELETE ", deletedStudent);

        //DELETE ALL
        int rowsDeleted = studentRepository.deleteAll();
        Assert.assertEquals(TAG + " DELETE ALL", rowsDeleted, 0);

    }
}
