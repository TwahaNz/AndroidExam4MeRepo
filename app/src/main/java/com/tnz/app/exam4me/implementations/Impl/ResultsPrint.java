package com.tnz.app.exam4me.implementations.Impl;

import com.tnz.app.exam4me.implementations.ResultsImpl;


/**
 * Write a description of class Print here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ResultsPrint implements ResultsImpl
{
    public ResultsPrint(){}
    
    @Override
    public void displayResults(int[] results) {
        try {
            System.out.println("\n Printing Results....");
            Thread.sleep(5000);
            System.out.println("\n Results Printed \n");
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
