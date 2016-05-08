package com.tnz.app.exam4me.domain.institute;

import java.io.Serializable;

public class Faculty implements Serializable
{
    private String facultyName;
    private String facultyLocation;
    
    private Faculty(){}
    
    public Faculty(Builder builder){
        this.facultyName = builder.facultyName;
        this.facultyLocation = builder.facultyLocation;
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

}
