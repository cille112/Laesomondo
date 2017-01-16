package com.example.cille_000.laesomondo.challengescreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cille_000.laesomondo.R;
import com.example.cille_000.laesomondo.logic.TestLogic;
import com.example.cille_000.laesomondo.startscreen.StartActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ShowTextActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView pdf;
    private TestLogic logic;
    private ImageButton pause;
    private Button stop;
    private boolean paused = false;
    private ScrollView scrool;
    private int textID;
    private String category;
    private DatabaseReference database;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_text);

        Intent intent = getIntent();
        textID = intent.getIntExtra("textID", 1);
        category = intent.getStringExtra("category");

        logic = new TestLogic(this);
        logic.beginTest(textID, category);

        pdf = (TextView) findViewById(R.id.PDF);
        pause = (ImageButton) findViewById(R.id.pauseButton);
        stop = (Button) findViewById(R.id.stopButton);
        scrool = (ScrollView) findViewById(R.id.scrollView);

        pdf.setText(logic.getText());


        pause.setOnClickListener(this);
        stop.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()==null){
            finish();
            Intent intent1 = new Intent(this, StartActivity.class);
            startActivity(intent1);
        }

        database = FirebaseDatabase.getInstance().getReference();
        database.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot snap) {
                if (snap.child("users").child(firebaseAuth.getCurrentUser().getUid()).hasChild("textSize")) {
                    float size = Float.parseFloat(snap.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("textSize").getValue().toString());
                   pdf.setTextSize(size);
                } else {
                }


            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == pause){
            if (paused){
                paused = false;
                scrool.setVisibility(View.VISIBLE);
                pause.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_media_pause));
                logic.stopPause();
            }
            else{
                paused = true;
                scrool.setVisibility(View.INVISIBLE);
                pause.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_media_play));
                logic.beginPause();
            }
        }
        if (v == stop){
            logic.stopTimer();
            Intent intent = new Intent(this, QuizActivity.class);
            intent.putExtra("textID", textID);
            intent.putExtra("time", logic.getTime());
            intent.putExtra("category", category);
            startActivity(intent);
        }

    }

    @Override
    public void onBackPressed() { }


}
