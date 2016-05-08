package com.tnz.app.exam4me.repository.settings;

import android.test.AndroidTestCase;

import com.tnz.app.exam4me.conf.databases.DatabaseCreate;
import com.tnz.app.exam4me.domain.settings.Registration;
import com.tnz.app.exam4me.repository.registration.Implementation.RegistrationRepositoryImpl;
import com.tnz.app.exam4me.repository.registration.RegistrationRepository;


import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Admin on 2016/05/08.
 */
public class RegistrationRepositoryTest extends AndroidTestCase {

    private static final String TAG = "REGISTRATION TEST";
    private static long id;

    public void testCreateReadUpadateDelete(){

        //DROP DATABASE
        DatabaseCreate.getInstance(this.getContext()).deleteDatabase();

        //Create the Student Table And Create The Database File
        RegistrationRepository registrationRepository = new RegistrationRepositoryImpl(this.getContext());

        //INSERT STUDENT
        Registration reg = new Registration.Builder()
                .assignStudentNumber("21430")
                .assignStudentEmail("peter@gmail.com")
                .build();

        Registration insertStudent = registrationRepository.insert(reg);
        id = insertStudent.getId();
        Assert.assertNotNull(TAG + " CREATE", insertStudent);


        //READ ALL
        Set<Registration> registrations = registrationRepository.findAll();
        Assert.assertTrue(TAG + " READ ALL", registrations.size() > 0);


        //READ ENTITY
        Registration entity = registrationRepository.findById(id);
        Assert.assertNotNull(TAG + " READ ENTITY", entity);

        //UPDATE ENTITY
        Registration updateReg = new Registration.Builder()
                .copyRegistration(entity)
                .assignStudentEmail("peter2@gmail.com")
                .build();

        registrationRepository.update(updateReg);

        Registration updatedReg = registrationRepository.findById(updateReg.getId());

        Assert.assertEquals(TAG + " UPDATE STUDENT", updatedReg.getStudentEmail(), "peter2@gmail.com");

        //DELETE ENTITY
        registrationRepository.delete(updatedReg);
        Registration deletedStudent = registrationRepository.findById(id);
        Assert.assertNull(TAG + " DELETE ", deletedStudent);

        //DELETE ALL
        int rowsDeleted = registrationRepository.deleteAll();
        Assert.assertEquals(TAG + " DELETE ALL", rowsDeleted, 0);

    }
}
