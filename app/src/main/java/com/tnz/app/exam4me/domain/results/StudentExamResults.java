package com.tnz.app.exam4me.domain.results;

import com.tnz.app.exam4me.domain.institute.Exam;

/**
 * Created by Admin on 2016/04/24.
 */
public class StudentExamResults {

    private Long id;
    private String studentNumber;
    private Exam exam;
    private int examTerm;
    private int termOne, termTwo, termThree, termFour;

    private StudentExamResults(){}

    public StudentExamResults(Builder builder){
        this.id = builder.id;
        this.exam = builder.exam;
        this.studentNumber = builder.studentNumber;
        this.examTerm = builder.examTerm;
        this.termOne = builder.termOne;
        this.termTwo = builder.termTwo;
        this.termThree = builder.termThree;
        this.termFour = builder.termFour;
    }

    public void writeExam(int term){
        exam = Exam.getInstance();
        exam.writeExam(Integer.parseInt(studentNumber), term);
    }

    public void getResults(int term){
        exam = Exam.getInstance();
        int[] results = exam.getTermResult(studentNumber, term);
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public Long getId() {
        return id;
    }

    public Exam getExam() {
        return exam;
    }

    public int getExamTerm() {
        return examTerm;
    }

    public int getTermOne() {
        return termOne;
    }

    public int getTermTwo() {
        return termTwo;
    }

    public int getTermThree() {
        return termThree;
    }

    public int getTermFour() {
        return termFour;
    }

    public static class Builder {

        private Long id;
        private Exam exam;
        private String studentNumber;
        private int examTerm;
        private int termOne, termTwo, termThree, termFour;

        public Builder assignID(long id) {
            this.id = id;
            return this;
        }

        public Builder assignStudentNumber(String studentNumber){
            this.studentNumber = studentNumber;
            return this;
        }

        public Builder assignExam(Exam exam){
            this.exam = exam;
            return this;
        }

        public Builder assignTerm(int term){
            this.examTerm = term;
            return this;
        }

        public Builder assignTermOne(int termOne){
            this.termOne = termOne;
            return this;
        }

        public Builder assignTermTwo(int termTwo){
            this.termTwo = termTwo;
            return this;
        }

        public Builder assignTermThree(int termThree){
            this.termThree = termThree;
            return this;
        }

        public Builder assignTermFour(int termFour){
            this.termFour = termFour;
            return this;
        }

        public Builder copyStudentExamResults(StudentExamResults studentExamResults){
            this.id = studentExamResults.id;
            this.exam = studentExamResults.exam;
            this.examTerm = studentExamResults.examTerm;
            this.termOne = studentExamResults.termOne;
            this.termTwo = studentExamResults.termTwo;
            this.termThree = studentExamResults.termThree;
            this.termFour = studentExamResults.termFour;

            return this;
        }

        public StudentExamResults build(){
            return new StudentExamResults(this);
        }
    }
}
