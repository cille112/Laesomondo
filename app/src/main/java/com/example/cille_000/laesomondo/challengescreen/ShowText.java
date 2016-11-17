package com.example.cille_000.laesomondo.challengescreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.cille_000.laesomondo.R;
import com.example.cille_000.laesomondo.logic.TestLogic;


public class ShowText extends AppCompatActivity {

    private TextView pdf;
    private TestLogic logic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_text);

        Intent mIntent = getIntent();
        int intValue = mIntent.getIntExtra("intVariableName", 1);

        pdf = (TextView) findViewById(R.id.PDF);
        logic = new TestLogic(intValue, this);

        pdf.setText(logic.getText());

    }


}
