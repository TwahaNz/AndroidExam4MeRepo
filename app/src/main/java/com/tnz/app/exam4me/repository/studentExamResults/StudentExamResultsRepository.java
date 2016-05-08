package com.tnz.app.exam4me.repository.studentExamResults;

import com.tnz.app.exam4me.domain.results.StudentExamResults;
import com.tnz.app.exam4me.repository.Repository;

/**
 * Created by Admin on 2016/04/24.
 */
public interface StudentExamResultsRepository extends Repository<StudentExamResults, Long> {

    public StudentExamResults findByDetails(String studentNumber);
}
