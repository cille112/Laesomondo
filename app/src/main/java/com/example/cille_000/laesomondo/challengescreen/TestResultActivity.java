package com.example.cille_000.laesomondo.challengescreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cille_000.laesomondo.R;
import com.example.cille_000.laesomondo.logic.TestLogic;
import com.example.cille_000.laesomondo.mainscreen.MainActivityOld;

public class TestResultActivity extends AppCompatActivity implements View.OnClickListener{

    private long time;
    private int correct;
    private TestLogic logic;
    private int xp;
    private TextView info;
    private Button ok;
    private int textID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testresult);

        Intent intent = getIntent();
        time = intent.getLongExtra("time", 0);
        System.out.println("in on create testresult" + time);
        correct = intent.getIntExtra("correct", 0);
        textID = intent.getIntExtra("textID", 1);
        xp = intent.getIntExtra("xp", 0);
        logic = new TestLogic(this);
        logic.setText(textID);

        info = (TextView) findViewById(R.id.resultInfo);
        ok = (Button) findViewById(R.id.TestButton);

        ok.setOnClickListener(this);
        int seconds = (int) (time / 1000) % 60 ;
        info.setText("Antal korrekte svar: " + correct + "\nDu læste teksten på " + seconds + " sekunder. \nDu får " + xp + " xp");
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MainActivityOld.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() { }
}
