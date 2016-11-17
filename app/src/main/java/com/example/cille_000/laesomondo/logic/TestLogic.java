package com.example.cille_000.laesomondo.logic;


import android.content.Context;

import com.example.cille_000.laesomondo.entities.ReadingTest;

import java.util.ArrayList;

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


}
