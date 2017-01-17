package com.example.cille_000.laesomondo.challengescreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.cille_000.laesomondo.R;
import com.example.cille_000.laesomondo.logic.TestLogic;
import com.example.cille_000.laesomondo.startscreen.StartActivity;
import com.google.firebase.auth.FirebaseAuth;

public class TextInfoActivity extends AppCompatActivity implements View.OnClickListener{

    private int textID;
    private Button button;
    private TestLogic logic;
    private TextView textInfo;
    private String info = "";
    private TextView textInfoname;
    private ImageView cover;
    private TextView cancel;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textinfo);

        Intent intent = getIntent();
        textID = intent.getIntExtra("textID", 1);
        category = intent.getStringExtra("category");

        button = (Button) findViewById(R.id.button3);
        logic = new TestLogic(this);
        logic.setText(textID, category);
        textInfo = (TextView) findViewById(R.id.textInfo);
        textInfoname = (TextView) findViewById(R.id.textInfoName);
        cover = (ImageView) findViewById(R.id.textCover);
        cancel = (TextView) findViewById(R.id.cancel_test);

        cover.setImageResource(findDrawable());

        info =  getString(R.string.Second) + " " + logic.getWriter() + "\n\n" +
                getString(R.string.Third) + " " + logic.getInfo();
        textInfo.setText(info);
        textInfoname.setText(logic.getName());

        button.setOnClickListener(this);
        cancel.setOnClickListener(this);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()==null){
            finish();
            Intent intent1 = new Intent(this, StartActivity.class);
            startActivity(intent1);
        }

    }

    @Override
    public void onClick(View v) {
        if(v==button) {
            Intent intent = new Intent(this, ShowTextActivity.class);
            intent.putExtra("textID", textID);
            intent.putExtra("category", category);
            startActivity(intent);
        }
        else if(v==cancel){
            finish();
        }
    }

    private int findDrawable(){
        if (textID == 1 && category.equals("Anbefalet")){
            return R.drawable.text1cover;
        }
        else  if (textID == 2 && category.equals("Anbefalet")){
            return R.drawable.text2cover;
        }
        else if (textID == 3 && category.equals("Anbefalet")){
            return R.drawable.text3cover;
        }
        else if (textID == 4 && category.equals("Anbefalet")){
            return R.drawable.text4cover;
        }
        else if (textID == 5 && category.equals("Anbefalet")){
            return R.drawable.text5cover;
        }
        else if (textID == 1 && category.equals("Adventure") ){
            return R.drawable.adventure01;
        }
        else if (textID == 2 && category.equals("Adventure") ){
            return R.drawable.adventure02;
        }
        else if (textID == 1 && category.equals("Krimi")){
            return R.drawable.text1krimi;
        }
        else if (textID == 2 && category.equals("Krimi")){
            return R.drawable.text2krimi;
        }
        else if (textID == 3 && category.equals("Krimi")){
            return R.drawable.text3krimi;
        }
        else if (textID == 4 && category.equals("Krimi")){
            return R.drawable.krimi04;
        }
        else if (textID == 5 && category.equals("Krimi")){
            return R.drawable.text5krimi;
        }
        else if (textID == 1 && category.equals("Gyser")){
            return R.drawable.text5krimi;
        }
        else if (textID == 2 && category.equals("Gyser")){
            return R.drawable.text5krimi;
        }

        return 0;
    }
}
