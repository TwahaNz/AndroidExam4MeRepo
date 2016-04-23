package com.tnz.app.exam4me.services;

/**
 * Created by Admin on 2016/04/03.
 */
public interface ResidentFeeImpl {

    public float retrieveStudentCosts();
    public void payStudentResidentFee(float amount);
    public float getStudentResidentFee();

}
