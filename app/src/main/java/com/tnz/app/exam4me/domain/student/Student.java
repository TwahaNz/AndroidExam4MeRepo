package com.tnz.app.exam4me.domain.student;

import com.tnz.app.exam4me.domain.institute.Exam;
import com.tnz.app.exam4me.domain.institute.Faculty;
import com.tnz.app.exam4me.domain.institute.RetrievedUploadedResults;
import com.tnz.app.exam4me.implementations.Impl.ResultsPrint;
import com.tnz.app.exam4me.implementations.Impl.ResultsView;
import com.tnz.app.exam4me.implementations.Impl.TuitionFee;

import java.io.Serializable;


public abstract class Student implements Serializable{

    private long id;
    private Faculty studentFaculty;
    private int[] retrievedResults;
    
    private String studentName;
    private String studentNumber;
    
    private float studentCurrentFee;
    private Exam exam = Exam.getInstance();
    private RetrievedUploadedResults retrievedUploadedResults = RetrievedUploadedResults.getInstance();

    private int term;
    protected Student() {}

    public Student(String studentName, String studentNumber){
        this.studentName = studentName;
        this.studentNumber = studentNumber;
    }

    public Student assignFaculty(String facultyName, String facultyLocation){
        studentFaculty = new Faculty.Builder()
                        .assignFacultyName(facultyName)
                        .assignFacultyLocation(facultyLocation)
                        .build();
        return this;
    }

    public Student assignStudentFee(String course){
        studentCurrentFee = TuitionFee.getInstance().retrieveStudentCosts(course);
        return this;
    }
    public Student assignId(long id){
        this.id = id;
        return this;
    }
    public String displayStudentDetails(){
        return "\n StudentFactory Name: " + studentName +
                "\n StudentFactory Number: " + studentNumber +
                "\n Course: " + studentFaculty.getFacultyName() +
                "\n Campus Location: " + studentFaculty.getFacultyLocation();
    }

    public void fetchResults(int term){
        this.term = term;
        retrievedResults = retrievedUploadedResults.fecthUploadedResults(studentNumber, term);
    }
    public void printResults(){

        if(term == 4 && getTotalFees() > 0) {
            System.out.println("\n Please pay your outstanding fees of R" + studentCurrentFee);
            return;
        }

        new ResultsPrint().displayResults(retrievedResults);
    }
    public void viewResults(){

        System.out.println("\n StudentFactory Name: " + studentName +
                          "\n StudentFactory Number: " + studentNumber +
                          "\n ================================");

        if(term == 4 && getTotalFees() > 0) {
            System.out.println("\n Please pay your outstanding fees of R" + studentCurrentFee);
            return;
        }

        new ResultsView().displayResults(retrievedResults);
    }

    public abstract void payStudentFee(float amountPaid, String typeOfFee);
    public abstract String displayCurrentFee();
    public abstract String displayAllFees();
    public abstract float getTotalFees();

    public void writeExam(int term) throws IndexOutOfBoundsException{

        if (term > 4 || term < 1)
                throw new IndexOutOfBoundsException();

        exam.writeExam(Integer.parseInt(studentNumber), term);
    }

    public long getId(){
        return id;
    }

    public String getStudentName() {return studentName;}

    public Integer getStudentNumber() {
        return Integer.parseInt(studentNumber);
    }
    public String getFacultyName(){
        return studentFaculty.getFacultyName();
    }
    public String getFacultyLocation(){
        return studentFaculty.getFacultyLocation();
    }

}
