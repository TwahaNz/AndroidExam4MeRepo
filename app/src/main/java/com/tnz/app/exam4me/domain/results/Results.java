package com.tnz.app.exam4me.domain.results;


import java.io.Serializable;

/**
 * Abstract class Results - write a description of the class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class Results implements Serializable
{
    Results nextTermResults;

    public void assignNextTermResults(Results nextTerm){
        this.nextTermResults = nextTerm;
    }

    public abstract int[] handleNextTermRequest(String studentNumber, int termRequest);

}