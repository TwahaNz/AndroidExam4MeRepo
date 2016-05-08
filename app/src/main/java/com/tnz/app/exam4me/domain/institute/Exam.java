package com.tnz.app.exam4me.domain.institute;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Admin on 2016/04/03.
 */
public class Exam implements Serializable{

    private static List<Map<Integer, int[]>>[] marks;
    private static Map<Integer, int[]> mark;
    private static Exam exams;

    private Exam(){}

    public static Exam getInstance(){
        if(exams == null){

            marks = new ArrayList[4];
            mark = new HashMap<>();

            marks[0] = new ArrayList<>();
            marks[1] = new ArrayList<>();
            marks[2] = new ArrayList<>();
            marks[3] = new ArrayList<>();

            exams = new Exam();
        }
        return exams;
    }

    public void writeExam(int studentNumber, int term){

        int[] studentTermMarks = {new Random().nextInt(100)+1,
                new Random().nextInt(100)+1,
                new Random().nextInt(100)+1,
                new Random().nextInt(100)+1};

        mark.put(studentNumber, studentTermMarks);

        if(term == 1)
            marks[0].add(mark);
        if(term == 2)
            marks[1].add(mark);
        if(term == 3)
            marks[2].add(mark);
        if(term == 4)
            marks[3].add(mark);
    }

    public int[] getTermResult(String studentNumber, int term){

        Integer studNum = Integer.parseInt(studentNumber.trim());

        if(term == 1 && marks[0].get(0).containsKey(studNum))
            return marks[0].get(0).get(studNum);

        if(term == 2 && marks[1].get(0).containsKey(studNum))
            return marks[1].get(0).get(studNum);

        if(term == 3 && marks[2].get(0).containsKey(studNum))
            return marks[2].get(0).get(studNum);

        if(term == 4 && marks[3].get(0).containsKey(studNum))
            return marks[3].get(0).get(studNum);

        return null;
    }
}
