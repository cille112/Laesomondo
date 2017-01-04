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
import com.example.cille_000.laesomondo.startscreen.StartActivity;
import com.google.firebase.auth.FirebaseAuth;


public class ShowTextActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView pdf;
    private TestLogic logic;
    private ImageButton pause;
    private Button stop;
    private boolean paused = false;
    private ScrollView scrool;
    private int textID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_text);

        Intent intent = getIntent();
        textID = intent.getIntExtra("textID", 1);

        logic = new TestLogic(this);
        logic.beginTest(textID);

        pdf = (TextView) findViewById(R.id.PDF);
        pause = (ImageButton) findViewById(R.id.pauseButton);
        stop = (Button) findViewById(R.id.stopButton);
        scrool = (ScrollView) findViewById(R.id.scrollView);

        pdf.setText(logic.getText());


        pause.setOnClickListener(this);
        stop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == pause){
            if (paused){
                paused = false;
                scrool.setVisibility(View.VISIBLE);
                pause.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_media_pause));
                logic.stopPause();
            }
            else{
                paused = true;
                scrool.setVisibility(View.INVISIBLE);
                pause.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_media_play));
                logic.beginPause();
            }
        }
        if (v == stop){
            logic.stopTimer();
            Intent intent = new Intent(this, QuizActivity.class);
            intent.putExtra("textID", textID);
            intent.putExtra("time", logic.getTime());
            startActivity(intent);
        }

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()==null){
            finish();
            Intent intent1 = new Intent(this, StartActivity.class);
            startActivity(intent1);
        }
    }

    @Override
    public void onBackPressed() { }

}
