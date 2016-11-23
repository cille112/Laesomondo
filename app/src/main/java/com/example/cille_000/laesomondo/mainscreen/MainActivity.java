package com.example.cille_000.laesomondo.mainscreen;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.cille_000.laesomondo.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton imageButton, BurgerButton, Userbutton;
    private HorizontalScrollView scrollViewH01, scrollViewH02, scrollViewH03, scrollViewH04;
    private ScrollView scrollViewV01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //comment
        //this should remove the statusbar
        //  requestWindowFeature(Window.FEATURE_NO_TITLE);
        //  this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        imageButton = (ImageButton) findViewById(R.id.imageButton3);
        Userbutton = (ImageButton) findViewById(R.id.userbutton);
        BurgerButton = (ImageButton) findViewById(R.id.burgerbutton);
        scrollViewH01 = (HorizontalScrollView) findViewById(R.id.sch01);
        scrollViewH02 = (HorizontalScrollView) findViewById(R.id.sch02);
        scrollViewH03 = (HorizontalScrollView) findViewById(R.id.sch03);
        scrollViewH04 = (HorizontalScrollView) findViewById(R.id.sch04);
        scrollViewV01 = (ScrollView) findViewById(R.id.scv01);

        Userbutton.setOnClickListener(this);
        BurgerButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v == imageButton) {
        }

        if (v == BurgerButton) {

        }
        if (v == Userbutton) {

        }

    }
}
