package com.example.cille_000.laesomondo.startscreen;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cille_000.laesomondo.R;
import com.example.cille_000.laesomondo.challengescreen.TextInfoActivity;
import com.example.cille_000.laesomondo.mainscreen.MainActivity;

public class ChallengeInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private Button challenge;
    private TextView later;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challengeinfo);

        challenge = (Button) findViewById(R.id.challengeinfo_button);
        later = (TextView) findViewById(R.id.challengeinfo_later);

        challenge.setOnClickListener(this);
        later.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == challenge) {
            Intent intent = new Intent(this, TextInfoActivity.class);
            intent.putExtra("textID", 5);
            startActivity(intent);
        }
        else if(v == later) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }
}
