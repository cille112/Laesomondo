package com.example.cille_000.laesomondo.startscreen;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.cille_000.laesomondo.R;
import com.example.cille_000.laesomondo.challengescreen.TextInfoActivity;
import com.example.cille_000.laesomondo.mainscreen.MainActivity;

public class TestInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn;
    private TextView notnow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        btn = (Button) findViewById(R.id.testinfo_button);
        btn.setOnClickListener(this);

        notnow = (TextView) findViewById(R.id.testinfo_later);
        notnow.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v == btn)
        {
            Intent intent = new Intent(this, TextInfoActivity.class);
            intent.putExtra("textID", 5);
            startActivity(intent);
        }
        else if(v == notnow)
        {
            Intent mainscreen = new Intent(this, MainActivity.class);
            startActivity(mainscreen);
        }
    }
}
