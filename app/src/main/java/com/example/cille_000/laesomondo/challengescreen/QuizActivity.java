package com.example.cille_000.laesomondo.challengescreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.cille_000.laesomondo.R;
import com.example.cille_000.laesomondo.logic.TestLogic;

import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private int textID;
    private TestLogic logic;
    private List<String> firstQuestion;
    private List<String> secondQuestion;
    private List<String> thirdQuestion;
    private Button first, second, third, fourth;
    private TextView question;
    private int questionNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Intent intent = getIntent();
        textID = intent.getIntExtra("textID", 1);
        logic = new TestLogic(textID, this);

        first = (Button) findViewById(R.id.firstAnswer);
        second = (Button) findViewById(R.id.secondAnswer);
        third = (Button) findViewById(R.id.thirdAnswer);
        fourth = (Button) findViewById(R.id.fourthAnswer);
        question = (TextView) findViewById(R.id.Question);

        firstQuestion = logic.getQuestion1();
        secondQuestion = logic.getQuestion2();
        thirdQuestion = logic.getQuestion3();

        firstQuestion();

    }

    private void firstQuestion(){
        question.setText(firstQuestion.get(0));
        first.setText(firstQuestion.get(1));
        second.setText(firstQuestion.get(2));
        third.setText(firstQuestion.get(3));
        fourth.setText(firstQuestion.get(4));
    }
    private void secondQuestion(){
        question.setText(secondQuestion.get(0));
        first.setText(secondQuestion.get(1));
        second.setText(secondQuestion.get(2));
        third.setText(secondQuestion.get(3));
        fourth.setText(secondQuestion.get(4));
    }
    private void thridQuestion(){
        question.setText(thirdQuestion.get(0));
        first.setText(thirdQuestion.get(1));
        second.setText(thirdQuestion.get(2));
        third.setText(thirdQuestion.get(3));
        fourth.setText(thirdQuestion.get(4));
    }
}
