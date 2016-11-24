package com.example.cille_000.laesomondo.logic;


import android.content.Context;
import android.content.Intent;

import com.example.cille_000.laesomondo.challengescreen.TestResultActivity;
import com.example.cille_000.laesomondo.entities.ReadingTest;

import java.util.ArrayList;
import java.util.List;

public class TestLogic {

    private ReadingTest readingTest;
    private Long startTime, stopTime, startPause, stopPause;
    private ArrayList<Long> pauses = new ArrayList<>();
    private Long totalTime;
    private int standardReadingSpeed = 3;
    private int correct = 0;
    private Context context;


    public TestLogic(Context context){
        this.context = context;
        readingTest = new ReadingTest(context);
    }

    public void beginTest(int id){
        startTime = System.currentTimeMillis();
        readingTest.setTextID(id);
    }

    public void setText(int id){
        readingTest.setTextID(id);
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

    public int calculateXP(long time){
        int seconds = (int) time/1000%60;
        int readingspeed = readingTest.getWordCount()/standardReadingSpeed;
        int xp = (readingTest.getLix()*correct)+readingspeed-seconds+50;
        if(xp <= 10){return 10;}
        else {return xp;}
    }

    public void checkAnswer(int question, int answer){
        int correctAnswer = readingTest.getCorrectAnswer(question);
        if(answer == correctAnswer){
            correct++;
        }

    }

    public void getResult(int textID, Long time){
        Intent intent = new Intent(context, TestResultActivity.class);
        intent.putExtra("time", time);
        intent.putExtra("correct", correct);
        intent.putExtra("textID", textID);
        intent.putExtra("xp", calculateXP(time));
        context.startActivity(intent);
    }
}
