package com.tnz.app.exam4me;

import com.tnz.app.exam4me.factories.Intitute.FacultyFactoryTest;
import com.tnz.app.exam4me.factories.Intitute.LecturerFactoryTest;
import com.tnz.app.exam4me.factories.Student.NonResidentStudentTypeFactoryTest;
import com.tnz.app.exam4me.factories.Student.ResidentStudentFactoryTest;
import com.tnz.app.exam4me.factories.Results.ResultsFactoryTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        FacultyFactoryTest.class,
        LecturerFactoryTest.class,
        ResidentStudentFactoryTest.class,
        NonResidentStudentTypeFactoryTest.class,
        ResultsFactoryTest.class
})

public class AppUnitTestSuite {}
