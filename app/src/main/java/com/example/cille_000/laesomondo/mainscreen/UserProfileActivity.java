package com.example.cille_000.laesomondo.mainscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.cille_000.laesomondo.R;


public class UserProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView lixvalue, wordMinValue;
    private ImageButton achievement, stats, backArrow;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarUser);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        wordMinValue = (TextView) findViewById(R.id.userprofile_wordminvalue);
        lixvalue = (TextView) findViewById(R.id.userprofile_lixvalue);
        achievement = (ImageButton) findViewById(R.id.userprofile_achievement);
        stats = (ImageButton) findViewById(R.id.userprofile_stats);
        backArrow = (ImageButton) findViewById(R.id.backArrow);



        achievement.setOnClickListener(this);
        stats.setOnClickListener(this);
        backArrow.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
//        if(v == achievement) {
//        }
//
//        if(v == stats) {
//        }

        if(v == backArrow) {
            Intent mainscreen = new Intent(this, MainActivity.class);
            startActivity(mainscreen);
        }
    }
}
