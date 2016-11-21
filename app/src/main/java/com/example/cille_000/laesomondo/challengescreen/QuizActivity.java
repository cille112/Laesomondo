package com.example.cille_000.laesomondo.challengescreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cille_000.laesomondo.R;
import com.example.cille_000.laesomondo.logic.TestLogic;

public class QuizActivity extends AppCompatActivity {

    private int textID;
    private TestLogic logic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        Intent intent = getIntent();
        textID = intent.getIntExtra("textID", 1);
        logic = new TestLogic(textID, this);


    }
}
