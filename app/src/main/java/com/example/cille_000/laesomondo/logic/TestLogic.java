package com.example.cille_000.laesomondo.logic;


import android.content.Context;

import com.example.cille_000.laesomondo.entities.ReadingTest;

import java.util.ArrayList;
import java.util.List;

public class TestLogic {

    private ReadingTest readingTest;
    private Long startTime, stopTime, startPause, stopPause;
    private ArrayList<Long> pauses = new ArrayList<>();
    private Long totalTime;
    private int standardReadingSpeed = 3;

    public TestLogic(int id, Context context){
        readingTest = new ReadingTest(id, context);
        startTime = System.currentTimeMillis();
    }


    public String getText(){
        return readingTest.getText();
    }

    public String getWriter (){ return readingTest.getWriter(); }

    public String getName (){ return readingTest.getName(); }

    public String getInfo (){ return readingTest.getInfo();}

    public List<String> getQuestion1() {return readingTest.getQuestion1();}

    public List<String> getQuestion2() {return readingTest.getQuestion2();}

    public List<String> getQuestion3() {return readingTest.getQuestion3();}

    public int correctAnswer1 (){return readingTest.getCorrectAnswer1();}
    public int correctAnswer2 (){return readingTest.getCorrectAnswer2();}
    public int correctAnswer3 (){return readingTest.getCorrectAnswer3();}


    public void beginPause(){
        startPause = System.currentTimeMillis();
    }

    public void stopPause(){
        stopPause = System.currentTimeMillis();
        pauses.add(stopPause-startPause);
    }

    public void stopTimer(){
        stopTime = System.currentTimeMillis();
        totalTime = stopTime-startTime;
        for (int i = 0; i< pauses.size(); i++){
            totalTime = totalTime- pauses.get(i);
        }
    }

    public long getTime(){
        return totalTime;
    }

    public int calculateXP(long time, int correct){
        int seconds = (int) time/1000%60;
        int readingspeed = readingTest.getWordCount()/standardReadingSpeed;
        int xp = (readingTest.getLix()*correct)+readingspeed-seconds+50;
        if(xp <= 10){return 10;}
        else {return xp;}
    }
}
