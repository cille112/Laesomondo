package com.example.cille_000.laesomondo.challengescreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.cille_000.laesomondo.R;
import com.example.cille_000.laesomondo.challengescreen.ShowTextActivity;
import com.example.cille_000.laesomondo.logic.TestLogic;

public class TextInfoActivity extends AppCompatActivity implements View.OnClickListener{

    private int textID;
    private Button button;
    private TestLogic logic;
    private TextView textInfo;
    private String info = "";
    private TextView textInfoname;
    private ImageView cover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textinfo);

        Intent intent = getIntent();
        textID = intent.getIntExtra("textID", 1);

        button = (Button) findViewById(R.id.button3);
        logic = new TestLogic(this);
        logic.setText(textID);
        textInfo = (TextView) findViewById(R.id.textInfo);
        textInfoname = (TextView) findViewById(R.id.textInfoName);
        cover = (ImageView) findViewById(R.id.textCover);

        cover.setImageResource(findDrawable());

        info =  getString(R.string.Second) + " " + logic.getWriter() + "\n\n" +
                getString(R.string.Third) + " " + logic.getInfo();
        textInfo.setText(info);
        textInfoname.setText(logic.getName());

        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ShowTextActivity.class);
        intent.putExtra("textID", 1);
        startActivity(intent);
    }

    private int findDrawable(){
        if (textID == 1){
            return R.drawable.text1cover;
        }
        if (textID == 2){
            return R.drawable.text2cover;
        }
        if (textID == 3){
            return R.drawable.text3cover;
        }
        if (textID == 4){
            return R.drawable.text4cover;
        }
        if (textID == 5){
            return R.drawable.text5cover;
        }
        return 0;
    }
}
