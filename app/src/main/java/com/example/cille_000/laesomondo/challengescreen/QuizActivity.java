package com.example.cille_000.laesomondo.challengescreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cille_000.laesomondo.R;
import com.example.cille_000.laesomondo.logic.TestLogic;
import com.example.cille_000.laesomondo.startscreen.CreateUserActivity;
import com.example.cille_000.laesomondo.startscreen.StartActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener{

    private int textID;
    private long time;
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
        time = intent.getLongExtra("time", 0);
        logic = new TestLogic(this);
        logic.setText(textID);

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

        firstQuestion();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()==null){
            finish();
            Intent intent1 = new Intent(this, StartActivity.class);
            startActivity(intent1);
        }

    }

    private void firstQuestion(){
        question.setText(firstQuestion.get(0));
        first.setText(firstQuestion.get(1));
        second.setText(firstQuestion.get(2));
        third.setText(firstQuestion.get(3));
        fourth.setText(firstQuestion.get(4));
    }
    private void secondQuestion(){
        secondQuestion = logic.getQuestion2();
        question.setText(secondQuestion.get(0));
        first.setText(secondQuestion.get(1));
        second.setText(secondQuestion.get(2));
        third.setText(secondQuestion.get(3));
        fourth.setText(secondQuestion.get(4));
    }
    private void thirdQuestion(){
        thirdQuestion = logic.getQuestion3();
        question.setText(thirdQuestion.get(0));
        first.setText(thirdQuestion.get(1));
        second.setText(thirdQuestion.get(2));
        third.setText(thirdQuestion.get(3));
        fourth.setText(thirdQuestion.get(4));
    }

    @Override
    public void onClick(View v) {
        if (questionNumber == 1){
            if(v==first){
            logic.checkAnswer(1,1);
            }
            if(v==second){
                logic.checkAnswer(1,2);
            }
            if (v==third){
                logic.checkAnswer(1,3);
            }
            if (v==fourth){
                logic.checkAnswer(1,4);
            }
            secondQuestion();
            questionNumber++;
        }
        else if (questionNumber==2){
            if(v==first){
                logic.checkAnswer(2,1);
            }
            if(v==second){
                logic.checkAnswer(2,2);
            }
            if (v==third){
                logic.checkAnswer(2,3);
            }
            if (v==fourth){
                logic.checkAnswer(2,4);
            }
            thirdQuestion();
            questionNumber++;
        }
        else{
            if(v==first){
                logic.checkAnswer(3,1);
            }
            if(v==second){
                logic.checkAnswer(3,2);
            }
            if (v==third){
                logic.checkAnswer(3,3);
            }
            if (v==fourth){
                logic.checkAnswer(3,4);
            }
            logic.getResult(textID, time);
        }
    }

    @Override
    public void onBackPressed() { }
}
