package com.example.cille_000.laesomondo.challengescreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cille_000.laesomondo.R;
import com.example.cille_000.laesomondo.logic.TestLogic;

import java.util.List;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener{

    private int textID;
    private TestLogic logic;
    private List<String> firstQuestion;
    private List<String> secondQuestion;
    private List<String> thirdQuestion;
    private Button first, second, third, fourth;
    private TextView question;
    private int questionNumber = 1;
    private int correct = 0;

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

        first.setOnClickListener(this);
        second.setOnClickListener(this);
        third.setOnClickListener(this);
        fourth.setOnClickListener(this);

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
    private void thirdQuestion(){
        question.setText(thirdQuestion.get(0));
        first.setText(thirdQuestion.get(1));
        second.setText(thirdQuestion.get(2));
        third.setText(thirdQuestion.get(3));
        fourth.setText(thirdQuestion.get(4));
    }

    @Override
    public void onClick(View v) {
        if (questionNumber == 1){
            int correctanswer = logic.correctAnswer1();
            if(v==first && correctanswer==1){
                correct++;
            }
            if(v==second && correctanswer==2){
                correct++;
            }
            if(v==third && correctanswer==3){
                correct++;
            }
            if(v==fourth && correctanswer==4){
                correct++;
            }
            secondQuestion();
            questionNumber++;
        }
        else if (questionNumber==2){
            int correctanswer = logic.correctAnswer2();
            if(v==first && correctanswer==1){
                correct++;
            }
            if(v==second && correctanswer==2){
                correct++;
            }
            if(v==third && correctanswer==3){
                correct++;
            }
            if(v==fourth && correctanswer==4){
                correct++;
            }
            thirdQuestion();
            questionNumber++;
        }
        else{
            int correctanswer = logic.correctAnswer3();
            if(v==first && correctanswer==1){
                correct++;
            }
            if(v==second && correctanswer==2){
                correct++;
            }
            if(v==third && correctanswer==3){
                correct++;
            }
            if(v==fourth && correctanswer==4){
                correct++;
            }

        }
    }
}
