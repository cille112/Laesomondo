package com.example.cille_000.laesomondo.challengescreen;

import android.content.Intent;
import android.graphics.BlurMaskFilter;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.cille_000.laesomondo.R;
import com.example.cille_000.laesomondo.logic.TestLogic;


public class ShowTextActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView pdf;
    private TestLogic logic;
    private ImageButton pause;
    private Button stop;
    private boolean paused = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_text);

        Intent mIntent = getIntent();
        int intValue = mIntent.getIntExtra("intVariableName", 1);

        pdf = (TextView) findViewById(R.id.PDF);
        logic = new TestLogic(intValue, this);
        pause = (ImageButton) findViewById(R.id.pauseButton);
        stop = (Button) findViewById(R.id.stopButton);



        pdf.setText(logic.getText());

        pause.setOnClickListener(this);
        stop.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        float radius = pdf.getTextSize() / 3;
        BlurMaskFilter filter = new BlurMaskFilter(radius, BlurMaskFilter.Blur.NORMAL);
        if (v==pause){
            if (paused){
                paused=false;
                pdf.getPaint().setMaskFilter(null);
                logic.stopPause();
            }
            else{
                paused=true;
                pdf.getPaint().setMaskFilter(filter);
                logic.paused();
            }
        }
        if (v==stop){
            logic.stopTimer();
        }
    }


}
