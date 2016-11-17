package com.example.cille_000.laesomondo.logic;


import android.content.Context;

import com.example.cille_000.laesomondo.entities.ReadingTest;

public class TestLogic {

    private ReadingTest readingTest;

    public TestLogic(int id, Context context){
        readingTest = new ReadingTest(id, context);
    }


    public String getText(){
        return readingTest.getText();
    }
}
