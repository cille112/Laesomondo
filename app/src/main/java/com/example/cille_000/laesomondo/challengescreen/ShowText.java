package com.example.cille_000.laesomondo.challengescreen;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cille_000.laesomondo.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ShowText extends AppCompatActivity {

    TextView pdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_text);

        pdf = (TextView) findViewById(R.id.PDF);

        String text = "";
        try {
            InputStream is = getAssets().open("text1.txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            text = new String(buffer);
        }catch (IOException e){
            e.printStackTrace();
        }
        pdf.setText(text);
    }


}
