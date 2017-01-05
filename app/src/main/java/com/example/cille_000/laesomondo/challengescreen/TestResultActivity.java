package com.example.cille_000.laesomondo.challengescreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cille_000.laesomondo.R;
import com.example.cille_000.laesomondo.logic.TestLogic;
import com.example.cille_000.laesomondo.mainscreen.MainActivity;
import com.example.cille_000.laesomondo.startscreen.StartActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TestResultActivity extends AppCompatActivity implements View.OnClickListener{

    private long time;
    private int correct;
    private TestLogic logic;
    private int xp;
    private TextView info;
    private Button ok;
    private int textID;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference database;
    private String userId;
    private int oldXp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testresult);

        Intent intent = getIntent();
        time = intent.getLongExtra("time", 0);
        System.out.println("in on create testresult" + time);
        correct = intent.getIntExtra("correct", 0);
        textID = intent.getIntExtra("textID", 1);
        xp = intent.getIntExtra("xp", 0);
        logic = new TestLogic(this);
        logic.setText(textID);

        info = (TextView) findViewById(R.id.resultInfo);
        ok = (Button) findViewById(R.id.TestButton);

        ok.setOnClickListener(this);
        int seconds = (int) (time / 1000) % 60 ;
        info.setText("Antal korrekte svar: " + correct + "\nDu læste teksten på " + seconds + " sekunder. \nDu får " + xp + " xp");

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()==null){
            finish();
            Intent intent1 = new Intent(this, StartActivity.class);
            startActivity(intent1);
        }

        database = FirebaseDatabase.getInstance().getReference();
        userId = firebaseAuth.getCurrentUser().getUid();


        database.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snap) {
                System.out.println(snap.child("users").child(userId).child("xp").getValue());
                oldXp = Integer.parseInt(snap.child("users").child(userId).child("xp").getValue().toString());
                database.child("users").child(userId).child("xp").setValue(oldXp+xp);
                update();
                if (snap.child("users").child(userId).child("textRead").exists()) {
                    String oldTextRead = snap.child("users").child(userId).child("textRead").getValue().toString();
                    database.child("users").child(userId).child("textRead").setValue(oldTextRead + " " + textID);
                }
                else
                    database.child("users").child(userId).child("textRead").setValue(textID);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() { }

    private void update(){
        database.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snap) {
                System.out.println("efter " + snap.child("users").child(userId).child("xp").getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
