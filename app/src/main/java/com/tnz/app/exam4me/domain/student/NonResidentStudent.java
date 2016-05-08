package com.tnz.app.exam4me.domain.student;


import com.tnz.app.exam4me.implementations.Impl.TuitionFee;
import com.tnz.app.exam4me.implementations.FeesImpl;

import java.io.Serializable;


/**
 * Write a description of class NonResidentStudentFactory here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NonResidentStudent extends Student implements Serializable
{
    private Long id;
    private FeesImpl tuitionFee;

    public NonResidentStudent(Builder builder){
        super(builder.studentName, "" + builder.studentNumber);
        tuitionFee = builder.tuitionFee;
        this.id = builder.id;
    }

    public long getId(){
        return id;
    }

    @Override
    public void payStudentFee(float amountPaid, String typeOfFee) {
        if (typeOfFee.toLowerCase().equals("tuition"))
            tuitionFee.payStudentFee(amountPaid);
    }

    @Override
    public String displayCurrentFee() {
        return "\n Current Fee: R" + tuitionFee.getStudentFee() +
                "\n =================================================================";
    }

    @Override
    public String displayAllFees() {
        return "\n Course Fee: R" + tuitionFee.retrieveStudentCosts(getFacultyName()) +
                "\n Current Fee Owing: R" + tuitionFee.getStudentFee() +
                "\n =================================================================";
    }

    @Override
    public float getTotalFees() {
        return tuitionFee.getStudentFee();
    }

    public static class Builder extends Student
    {
        private Long id;
        private FeesImpl tuitionFee;
        private String studentName;
        private String studentNumber;

        public Builder(){
            tuitionFee = TuitionFee.getInstance();
        }

        public Builder id(long id){
            this.id = id;
            return this;
        }

        public Builder studentName(String studentName) {
            this.studentName = studentName;
            return this;
        }

        public Builder studentNumber(String studentNumber) {
            this.studentNumber = studentNumber;
            return this;
        }

        @Override
        public void payStudentFee(float amountPaid, String typeOfFee) {
            if (typeOfFee.toLowerCase().equals("tuition"))
                tuitionFee.payStudentFee(amountPaid);
        }

        @Override
        public String displayCurrentFee() {
            return "\n Current Fee: R" + tuitionFee.getStudentFee() +
                    "\n =================================================================";
        }

        @Override
        public String displayAllFees() {
            return "\n Course Fee: R" + tuitionFee.retrieveStudentCosts(getFacultyName()) +
                    "\n Current Fee Owing: R" + tuitionFee.getStudentFee() +
                    "\n =================================================================";
        }

        @Override
        public float getTotalFees() {
            return tuitionFee.getStudentFee();
        }

        public NonResidentStudent build(){
            return new NonResidentStudent(this);
        }

        public Builder copyNonResidentStudent(NonResidentStudent student){

            tuitionFee = student.tuitionFee;
            studentName = student.getStudentName();
            studentNumber = "" + student.getStudentNumber();
            id = student.id;

            return this;
        }

        @Override
        public int hashCode() {
            return id != null? id.hashCode():0;
        }

        @Override
        public boolean equals(Object o) {

            if(this == o)
                return true;

            if (o == null || this.getClass() != o.getClass())
                return false;

            NonResidentStudent student = (NonResidentStudent) o;

            return id != null ? id.equals(student.id):student.id == null;
        }
    }
}
