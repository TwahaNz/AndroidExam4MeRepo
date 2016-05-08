package com.tnz.app.exam4me.domain.settings;

/**
 * Created by Admin on 2016/05/08.
 */
public class Registration {

    private Long id;
    private String studentNumber;
    private String studentEmail;
    private String secretCode;

    public Registration (Builder builder){
        this.id = builder.id;
        this.studentNumber = builder.studentNumber;
        this.studentEmail = builder.studentEmail;
        this.secretCode = builder.secrectCode;
    }
    public Long getId() {
        return id;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public String getSecretCode() {
        return secretCode;
    }

    public static class Builder {

        private Long id;
        private String studentNumber;
        private String studentEmail;
        private String secrectCode;

        public Builder() {

        }

        public Builder assignId(long id) {
            this.id = id;
            return this;
        }

        public Builder assignStudentNumber(String studentNumber) {
            this.studentNumber = studentNumber;
            return this;
        }

        public Builder assignStudentEmail(String studentEmail) {
            this.studentEmail = studentEmail;
            return this;
        }

        public Builder assignSecrectCode(String secrectCode) {
            this.secrectCode = secrectCode;
            return this;
        }

        public Builder copyRegistration(Registration registration) {
            this.id = registration.id;
            this.studentNumber = registration.studentNumber;
            this.studentEmail = registration.studentEmail;
            this.secrectCode = registration.secretCode;

            return this;
        }

        public Registration build() {
            return new Registration(this);
        }
    }
}
