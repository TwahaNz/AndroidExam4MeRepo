package com.tnz.app.exam4me.domain.Institute;

import java.io.Serializable;

public class Faculty implements Serializable
{
    private Long id;
    private String facultyName;
    private String facultyLocation;
    
    private Faculty(){}
    
    public Faculty(Builder builder){
        this.facultyName = builder.facultyName;
        this.facultyLocation = builder.facultyLocation;
        this.id = builder.id;
    }

    public long getId(){
        return id;
    }

    public String getFacultyName(){
        return this.facultyName;
    }
        
    public String getFacultyLocation(){
        return this.facultyLocation;
    }
    
    public static class Builder
    {
            private long id;
            private String facultyName;
            private String facultyLocation;

            public Builder assignFacultyName(String facultyName) {
                this.facultyName = facultyName;
                return this;
            }

            public Builder assignId(long id){
                this.id = id;
                return this;
            }

            public Builder assignFacultyLocation(String facultyLocation) {
                this.facultyLocation = facultyLocation;
                return this;
            }

            public Faculty build(){
                return new Faculty(this);
            }

            public Builder copyFaculty(Faculty toBeCopiedFaculty){
                this.facultyName = toBeCopiedFaculty.facultyName;
                this.facultyLocation = toBeCopiedFaculty.facultyLocation;
                return this;
            }
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Faculty faculty = (Faculty) o;

        return id != null ? id.equals(faculty.id) : faculty.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
