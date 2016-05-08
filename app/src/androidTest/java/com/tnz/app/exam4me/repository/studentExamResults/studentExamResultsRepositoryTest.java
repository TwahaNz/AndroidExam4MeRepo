package com.tnz.app.exam4me.repository.studentExamResults;

import android.test.AndroidTestCase;

import com.tnz.app.exam4me.conf.databases.DatabaseCreate;
import com.tnz.app.exam4me.domain.results.StudentExamResults;
import com.tnz.app.exam4me.repository.studentExamResults.Implementation.StudentExamResultsRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Admin on 2016/04/24.
 */
public class studentExamResultsRepositoryTest extends AndroidTestCase {


    private static final String TAG = "STUDENTEXAMRESULTS TEST";
    private static long id;

    public void testCreateReadUpadateDelete() {

        //DROP DATABASE
        DatabaseCreate.getInstance(this.getContext()).deleteDatabase();

        //Create the Student Table And Create The Database File
        StudentExamResultsRepository studentRepository = new StudentExamResultsRepositoryImpl(this.getContext());

        //INSERT STUDENTs
        StudentExamResults studentResults = new StudentExamResults.Builder()
                .assignStudentNumber("21454545")
                .assignTerm(1)
                .assignTermOne(87)
                .assignTermTwo(88)
                .assignTermThree(65)
                .assignTermFour(96)
                .build();


        StudentExamResults insertStudentResults = studentRepository.insert(studentResults);
        id = insertStudentResults.getId();
        Assert.assertNotNull(TAG + " CREATE", insertStudentResults);


        //READ ALL
        Set<StudentExamResults> studentsResults = studentRepository.findAll();
        Assert.assertTrue(TAG + " READ ALL", studentsResults.size() > 0);


        //READ ENTITY
        StudentExamResults entity = studentRepository.findById(id);
        Assert.assertNotNull(TAG + " READ ENTITY", entity);

        //UPDATE ENTITY
        StudentExamResults updateStudentResults = new StudentExamResults.Builder()
                .copyStudentExamResults(entity)
                .assignTermOne(85)
                .build();

        studentRepository.update(updateStudentResults);

        StudentExamResults updatedStudent = studentRepository.findById(updateStudentResults.getId());

        Assert.assertEquals(TAG + " UPDATE STUDENT", updatedStudent.getTermOne(), 85);

        //DELETE ENTITY
        studentRepository.delete(updatedStudent);
        StudentExamResults deletedStudentResults = studentRepository.findById(id);
        Assert.assertNull(TAG + " DELETE ", deletedStudentResults);

        //DELETE ALL
        int rowsDeleted = studentRepository.deleteAll();
        Assert.assertEquals(TAG + " DELETE ALL", rowsDeleted, 0);
    }
}
