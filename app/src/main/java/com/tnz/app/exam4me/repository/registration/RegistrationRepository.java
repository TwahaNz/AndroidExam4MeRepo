package com.tnz.app.exam4me.repository.registration;

import com.tnz.app.exam4me.domain.settings.Registration;
import com.tnz.app.exam4me.domain.student.Student;
import com.tnz.app.exam4me.repository.Repository;

/**
 * Created by Admin on 2016/05/08.
 */
public interface RegistrationRepository extends Repository<Registration,Long> {

    Student findByDetails(String studentNumber, String email);
}
