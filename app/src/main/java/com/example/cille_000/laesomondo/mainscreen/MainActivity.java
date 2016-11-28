package com.example.cille_000.laesomondo.mainscreen;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import java.util.ArrayList;

import com.example.cille_000.laesomondo.R;
import com.example.cille_000.laesomondo.challengescreen.TextInfoActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton burgerButton, userButton;
    private ArrayList<ImageButton> coverList;



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

        coverList = new ArrayList<>();



        userButton = (ImageButton) findViewById(R.id.userbutton);
        burgerButton = (ImageButton) findViewById(R.id.burgerbutton);
        coverList.add((ImageButton) findViewById(R.id.imageButtonCover1));
        coverList.add((ImageButton) findViewById(R.id.imageButtonCover2));
        coverList.add((ImageButton) findViewById(R.id.imageButtonCover3));
        coverList.add((ImageButton) findViewById(R.id.imageButtonCover4));


        userButton.setOnClickListener(this);
        burgerButton.setOnClickListener(this);
        for (ImageButton i: coverList) {
            i.setOnClickListener(this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        for(int i = 0; i < coverList.size(); i++) {
            if(v == coverList.get(i)) {
                Intent intent = new Intent(this,TextInfoActivity.class);
                intent.putExtra("textID",++i);
                startActivity(intent);
            }
        }
//        if (v == burgerButton) {
//
//        }
        if (v == userButton) {
            Intent intent = new Intent(this, UserProfileActivity.class);
            startActivity(intent);
        }
    }
}
