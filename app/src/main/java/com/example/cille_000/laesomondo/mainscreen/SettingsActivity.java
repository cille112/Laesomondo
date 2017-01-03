package com.example.cille_000.laesomondo.mainscreen;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.cille_000.laesomondo.R;

public class SettingsActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private SeekBar textSize;
    private TextView currentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        textSize = (SeekBar) findViewById(R.id.textsizebar);
        currentText = (TextView) findViewById(R.id.currenttextsize);
        textSize.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        float size = 16;

        if(progress == 0) {
            size = 16;
        } else {
            size += progress / 5;
        }

        currentText.setTextSize(size);
        currentText.setText("" + size);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
