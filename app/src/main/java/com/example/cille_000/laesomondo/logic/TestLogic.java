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
    private int correct = 0;
    private Context context;



    // Skal tage imod en user!!
    public TestLogic(Context context){
        this.context = context;
        readingTest = new ReadingTest(context);
    }

    public void setText(int textID, String category){
        readingTest.setTextID(textID, category);
    }

    public void beginTest(int id, String categoryID){
        startTime = System.currentTimeMillis();
        readingTest.setTextID(id, categoryID);
    }

    public String getText(){
        return readingTest.getText();
    }

    public String getWriter (){ return readingTest.getWriter(); }

    public String getName (){ return readingTest.getName(); }

    public String getInfo (){ return readingTest.getInfo();}

    public int getLix () { return readingTest.getLix();}

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
        System.out.println(totalTime);
        return totalTime;
    }

    private int calculateXP(long time){
        /*int seconds = (int) time/1000;
        int readingspeed = readingTest.getWordCount()/(seconds+30);
        int xp = (readingTest.getLix()*correct)*(readingspeed);
        if(xp <= 10){return 10;}*/
        int seconds = (int) time/1000;
        int readingspeed = readingTest.getWordCount()/(seconds+30);
        int i = 0;

        if (correct == 1)
            i = 4;
        if (correct == 2)
            i = 12;
        if (correct == 3)
            i = 32;

        int xp = (readingTest.getLix()*correct*3)+(readingspeed)*i;
        if(xp <= 10){return 10;}
        else {return xp;}

    }

    public boolean checkAnswer(int question, int answer){
        int correctAnswer = readingTest.getCorrectAnswer(question);
        if(answer == correctAnswer){
            correct++;

            return true;
        }

        return false;
    }

    // add xp to user
    public void getResult(int textID, Long time, String categoryID){
        int xp = calculateXP(time);
        Intent intent = new Intent(context, TestResultActivity.class);
        intent.putExtra("time", time);
        intent.putExtra("correct", correct);
        intent.putExtra("textID", textID);
        intent.putExtra("xp", xp);
        intent.putExtra("lix", readingTest.getLix());
        intent.putExtra("wordCount", readingTest.getWordCount());
        intent.putExtra("category", categoryID);
        context.startActivity(intent);
    }
}
