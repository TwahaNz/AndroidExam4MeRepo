package com.tnz.app.exam4me.domain.institute;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.tnz.app.exam4me.domain.student.Student;
import com.tnz.app.exam4me.implementations.UploadMarksImpl;

//// TODO: 2016/04/16  remove the uploadMarksImpl
public class Lecturer implements UploadMarksImpl, Serializable
{
    private Long id;
    private Faculty lectureFaculty;
    private Map<Integer, Student> students;
    private String name;
    private String roomNumber;
    private String staffID;
    
    private Lecturer(){}

    public Lecturer(Builder builder){
        this.name = builder.name;
        this.roomNumber = builder.roomNumber;
        this.staffID = builder.staffID;
        this.lectureFaculty = builder.lectureFaculty;
        this.id = builder.id;
    }
    
    public void assignStudentMarks(Map<Integer, Student> studentsMarks){
        this.students = studentsMarks;
    }

    public long getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getStaffID() {
        return staffID;
    }

    public Faculty getFaculty(){
        return lectureFaculty;
    }

    @Override
    public List<Map<Integer, int[]>> uploadTermMarks(int term) {
        return null;
    }

    public static class Builder
    {
        private long id;
        private Faculty lectureFaculty;
        private String name;
        private String roomNumber;
        private String staffID;

        public Builder assignId(long id){
            this.id = id;
            return this;
        }

        public Builder assignName(String name){
            this.name = name;
            return this;
        }
        
        public Builder assignRoomNumber(String roomNumber){
            this.roomNumber = roomNumber;
            return this;
        }
        
        public Builder assignStaffID(String staffID){
            this.staffID = staffID;
            return this;
        }
        
        public Builder assignFaculty(Faculty faculty){

            lectureFaculty = faculty;
            return this;
        }

        public Lecturer build(){
            return new Lecturer(this);
        }

        public Builder copyLecturer(Lecturer lecturer) {

            this.name = lecturer.name;
            this.roomNumber = lecturer.roomNumber;
            this.staffID = lecturer.staffID;
            this.lectureFaculty = lecturer.lectureFaculty;
            this.id = lecturer.id;

            return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || this.getClass() != o.getClass())
            return false;

        Lecturer lecturer = (Lecturer) o;

        return id != null ? id.equals(lecturer.id): lecturer.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
