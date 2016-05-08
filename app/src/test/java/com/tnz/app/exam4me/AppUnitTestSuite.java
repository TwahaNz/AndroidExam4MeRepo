package com.tnz.app.exam4me;

import com.tnz.app.exam4me.factories.intitute.FacultyFactoryTest;
import com.tnz.app.exam4me.factories.intitute.LecturerFactoryTest;
import com.tnz.app.exam4me.factories.student.NonResidentStudentTypeFactoryTest;
import com.tnz.app.exam4me.factories.student.ResidentStudentFactoryTest;
import com.tnz.app.exam4me.factories.results.ResultsFactoryTest;

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
