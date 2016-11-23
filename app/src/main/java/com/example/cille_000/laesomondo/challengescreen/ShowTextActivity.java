package com.example.cille_000.laesomondo.challengescreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.cille_000.laesomondo.R;
import com.example.cille_000.laesomondo.logic.TestLogic;


public class ShowTextActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView pdf;
    private TestLogic logic;
    private ImageButton pause;
    private Button stop;
    private boolean paused = false;
    private ScrollView scrol;
    private int textID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_text);

        Intent intent = getIntent();
        textID = intent.getIntExtra("textID", 1);

        logic = new TestLogic(textID, this);

        pdf = (TextView) findViewById(R.id.PDF);
        pause = (ImageButton) findViewById(R.id.pauseButton);
        stop = (Button) findViewById(R.id.stopButton);
        scrol = (ScrollView) findViewById(R.id.scrollView);

        pdf.setText(logic.getText());

        pause.setOnClickListener(this);
        stop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==pause){
            if (paused){
                paused=false;
                scrol.setVisibility(View.VISIBLE);
                logic.stopPause();
            }
            else{
                paused=true;
                scrol.setVisibility(View.INVISIBLE);
                logic.paused();
            }
        }
        if (v==stop){
            logic.stopTimer();
            Intent intent = new Intent(this, QuizActivity.class);
            intent.putExtra("textID", textID);
            intent.putExtra("time", logic.getTime());
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() { }
}
