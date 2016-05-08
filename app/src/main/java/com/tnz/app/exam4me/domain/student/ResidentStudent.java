package com.tnz.app.exam4me.domain.student;

import com.tnz.app.exam4me.implementations.FeesImpl;
import com.tnz.app.exam4me.implementations.Impl.MealFee;
import com.tnz.app.exam4me.implementations.Impl.ResidentFee;
import com.tnz.app.exam4me.implementations.Impl.TuitionFee;
import com.tnz.app.exam4me.implementations.MealFeeImpl;
import com.tnz.app.exam4me.implementations.ResidentFeeImpl;

import java.io.Serializable;

/**
 * Write a description of class ResidentStudentFactory here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ResidentStudent extends Student implements Serializable{

    private Long id;
    private MealFeeImpl mealFee;
    private FeesImpl tuitionFee;
    private ResidentFeeImpl residenceFee;
    private String studentName;
    private String studentNumber;

    public ResidentStudent(Builder builder) {
        super(builder.studentName, "" + builder.studentNumber);
        mealFee = builder.mealFee;
        tuitionFee = builder.tuitionFee;
        residenceFee = builder.residenceFee;
        this.id = builder.id;
    }

    public long getId(){
        return id;
    }

    @Override
    public void payStudentFee(float amountPaid, String typeOfFee) {

        if (typeOfFee.toLowerCase().equals("meal"))
            mealFee.payStudentResidentMealFee(amountPaid);
        else {
            if (typeOfFee.toLowerCase().equals("residence"))
                residenceFee.payStudentResidentFee(amountPaid);
            if (typeOfFee.toLowerCase().equals("tuition"))
                tuitionFee.payStudentFee(amountPaid);
        }
    }

    @Override
    public String displayCurrentFee() {
         return "\n Current Fee Owing: R" + (mealFee.getStudentMealFee() +
                 tuitionFee.getStudentFee() +
                 residenceFee.getStudentResidentFee()) +
                 "\n =================================================================";
    }

    @Override
    public String displayAllFees() {
        return "\n Meals Cost: R" + mealFee.retrieveStudentCosts() +
                "\n Tuition Cost: R" + tuitionFee.retrieveStudentCosts(super.getFacultyName()) +
                "\n Residence Cost: R" + residenceFee.retrieveStudentCosts() +
                "\n\n Meal Fee Owing: R" + mealFee.getStudentMealFee() +
                "\n Residence Fee Owing: R" + residenceFee.getStudentResidentFee() +
                "\n Tuition Fee Owing: R" + tuitionFee.getStudentFee() +
                "\n\n Total Owing: R" + (tuitionFee.getStudentFee() + mealFee.getStudentMealFee() + residenceFee.getStudentResidentFee()) +
                "\n ====================================================================";
    }

    @Override
    public float getTotalFees() {
        return tuitionFee.getStudentFee() + residenceFee.getStudentResidentFee() + mealFee.getStudentMealFee();
    }

    public static class Builder extends Student{

        private Long id;
        private MealFeeImpl mealFee;
        private FeesImpl tuitionFee;
        private ResidentFeeImpl residenceFee;
        private String studentName;
        private String studentNumber;

        public Builder () {
            mealFee = MealFee.getInstance();
            tuitionFee = TuitionFee.getInstance();
            residenceFee = ResidentFee.getInstance();
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

            if (typeOfFee.toLowerCase().equals("meal"))
                mealFee.payStudentResidentMealFee(amountPaid);
            else {
                if (typeOfFee.toLowerCase().equals("residence"))
                    residenceFee.payStudentResidentFee(amountPaid);
                if (typeOfFee.toLowerCase().equals("tuition"))
                    tuitionFee.payStudentFee(amountPaid);
            }
        }

        @Override
        public String displayCurrentFee() {
            return "\n Current Fee Owing: R" + (mealFee.getStudentMealFee() +
                    tuitionFee.getStudentFee() +
                    residenceFee.getStudentResidentFee()) +
                    "\n =================================================================";
        }

        @Override
        public String displayAllFees() {
            return "\n Meals Cost: R" + mealFee.retrieveStudentCosts() +
                    "\n Tuition Cost: R" + tuitionFee.retrieveStudentCosts(super.getFacultyName()) +
                    "\n Residence Cost: R" + residenceFee.retrieveStudentCosts() +
                    "\n\n Meal Fee Owing: R" + mealFee.getStudentMealFee() +
                    "\n Residence Fee Owing: R" + residenceFee.getStudentResidentFee() +
                    "\n Tuition Fee Owing: R" + tuitionFee.getStudentFee() +
                    "\n\n Total Owing: R" + (tuitionFee.getStudentFee() + mealFee.getStudentMealFee() + residenceFee.getStudentResidentFee()) +
                    "\n ====================================================================";
        }

        @Override
        public float getTotalFees() {
            return tuitionFee.getStudentFee() + residenceFee.getStudentResidentFee() + mealFee.getStudentMealFee();
        }

        public ResidentStudent build(){
            return new ResidentStudent(this);
        }

        public Builder copyResidentStudent(ResidentStudent student){

            mealFee = student.mealFee;
            tuitionFee = student.tuitionFee;
            residenceFee = student.residenceFee;
            studentName = student.studentName;
            studentNumber = student.studentNumber;

            return this;
        }

        @Override
        public boolean equals(Object o) {

            if (this == o)
                return true;

            if (o == null || this.getClass() != o.getClass())
                return false;

            ResidentStudent student = (ResidentStudent) o;

            return id != null ? id.equals(student.id):student.id == 0;
        }

        @Override
        public int hashCode() {
            return id != null ? id.hashCode():0;
        }
    }
}
