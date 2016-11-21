package com.example.cille_000.laesomondo.logic;


import android.content.Context;

import com.example.cille_000.laesomondo.entities.ReadingTest;

import java.util.ArrayList;
import java.util.List;

public class TestLogic {

    private ReadingTest readingTest;
    private Long startTime, stopTime, startPause, stopPause;
    private ArrayList<Long> paused = new ArrayList<>();
    private Long totalTime;

    public TestLogic(int id, Context context){
        readingTest = new ReadingTest(id, context);
        startTime = System.currentTimeMillis();
    }


    public String getText(){
        return readingTest.getText();
    }

    public String getWriter (){ return readingTest.getWriter(); }

    public String getName (){ return readingTest.getName(); }

    public String getInfo (){ return readingTest.getinfo();}

    public List<String> getQuestion1() {return readingTest.getQuestion1();}

    public List<String> getQuestion2() {return readingTest.getQuestion2();}

    public List<String> getQuestion3() {return readingTest.getQuestion3();}

    public int correctAnswer1 (){return readingTest.correctanswer1();}
    public int correctAnswer2 (){return readingTest.correctanswer2();}
    public int correctAnswer3 (){return readingTest.correctanswer3();}


    public void paused(){
        startPause = System.currentTimeMillis();
    }

    public void stopPause(){
        stopPause = System.currentTimeMillis();
        paused.add(stopPause-startPause);
    }

    public void stopTimer(){
        stopTime = System.currentTimeMillis();
        totalTime = stopTime-startTime;
        for (int i = 0; i<paused.size(); i++){
            totalTime = totalTime-paused.get(i);
        }
    }

    public long getTime(){
        return totalTime;
    }


}
