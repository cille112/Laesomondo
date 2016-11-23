package com.example.cille_000.laesomondo.mainscreen;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.cille_000.laesomondo.R;

public class MainActivity extends AppCompatActivity {

    private ImageButton imageButton;
    private TextView textView;
    private HorizontalScrollView scrollViewH01, scrollViewH02, scrollViewH03, scrollViewH04;
    private ScrollView scrollViewV01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this should remove the statusbar
      //  requestWindowFeature(Window.FEATURE_NO_TITLE);
      //  this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        imageButton = (ImageButton) findViewById(R.id.imageButton3);
        textView = (TextView) findViewById(R.id.textView);
        scrollViewH01 = (HorizontalScrollView) findViewById(R.id.sch01);
        scrollViewH02 = (HorizontalScrollView) findViewById(R.id.sch02);
        scrollViewH03 = (HorizontalScrollView) findViewById(R.id.sch03);
        scrollViewH04 = (HorizontalScrollView) findViewById(R.id.sch04);
        scrollViewV01 = (ScrollView) findViewById(R.id.scv01);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
