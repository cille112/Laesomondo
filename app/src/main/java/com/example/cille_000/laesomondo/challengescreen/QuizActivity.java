package com.example.cille_000.laesomondo.challengescreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.Handler;

import com.example.cille_000.laesomondo.R;
import com.example.cille_000.laesomondo.logic.TestLogic;
import com.example.cille_000.laesomondo.startscreen.StartActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    private int textID;
    private long time;
    private TestLogic logic;
    private List<String> firstQuestion;
    private List<String> secondQuestion;
    private List<String> thirdQuestion;
    private Button first, second, third, fourth;
    private TextView question;
    private int questionNumber = 1;
    private boolean busy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Intent intent = getIntent();
        textID = intent.getIntExtra("textID", 1);
        time = intent.getLongExtra("time", 0);
        logic = new TestLogic(this);
        logic.setText(textID);
        busy = false;

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

        if(firebaseAuth.getCurrentUser() == null){
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
        boolean answer = false;
        Button button = first;

        if(busy) {
            return;
        }

        if (questionNumber == 1){
            busy = true;

            if(v == first){
                answer = logic.checkAnswer(1, 1);
            }
            else if(v == second){
                answer = logic.checkAnswer(1, 2);
                button = second;
            }
            else if (v == third){
                answer = logic.checkAnswer(1, 3);
                button = third;
            }
            else if (v == fourth){
                answer = logic.checkAnswer(1, 4);
                button = fourth;
            }

            questionNumber++;
            questionDone(button, answer);
        }
        else if (questionNumber == 2){
            busy = true;

            if(v == first){
                answer = logic.checkAnswer(2, 1);
            }
            else if(v == second){
                answer = logic.checkAnswer(2, 2);
                button = second;
            }
            else if (v == third){
                answer = logic.checkAnswer(2, 3);
                button = third;
            }
            else if (v == fourth){
                answer = logic.checkAnswer(2, 4);
                button = fourth;
            }

            questionNumber++;
            questionDone(button, answer);
        } else {
            busy = true;

            if(v == first){
                answer = logic.checkAnswer(3, 1);
            }
            else if(v == second){
                answer = logic.checkAnswer(3, 2);
                button = second;
            }
            else if (v == third){
                answer = logic.checkAnswer(3, 3);
                button = third;
            }
            else if (v == fourth){
                answer = logic.checkAnswer(3, 4);
                button = fourth;
            }

            questionNumber++;
            questionDone(button, answer);
        }
    }

    private void questionDone(final Button button, final boolean answer) {
        if(answer) {
            button.setBackgroundColor(getResources().getColor(R.color.colorAnswerCorrect));
        } else {
            button.setBackgroundColor(getResources().getColor(R.color.colorAnswerIncorrect));
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                busy = false;
                button.setBackgroundColor(getResources().getColor(R.color.colorGrey));

                switch (questionNumber) {
                    case 2:
                        secondQuestion();
                        break;
                    case 3:
                        thirdQuestion();
                        break;
                    case 4:
                        logic.getResult(textID, time);
                        break;
                    default:
                        break;
                }
            }
        }, 2000);
    }
}
