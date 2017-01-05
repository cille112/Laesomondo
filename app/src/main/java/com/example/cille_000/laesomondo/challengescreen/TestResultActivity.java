package com.example.cille_000.laesomondo.challengescreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.math.*;

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
    private int oldLix;
    private int lix;
    private int wordCount;
    private int booksRead = 1;
    private String s;


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
        lix = intent.getIntExtra("lix", 0);
        wordCount = intent.getIntExtra("wordCount", 0);

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
                if (snap.child("users").child(userId).child("xp").getValue() != null) {
                    oldXp = Integer.parseInt(snap.child("users").child(userId).child("xp").getValue().toString());
                    database.child("users").child(userId).child("xp").setValue(oldXp + xp);
                }
                else
                    database.child("users").child(userId).child("xp").setValue(xp);

                if (!snap.child("users").child(userId).child("textRead").exists()) {
                    database.child("users").child(userId).child("textRead").setValue(textID);
                }
                else {
                    String oldTextRead = snap.child("users").child(userId).child("textRead").getValue().toString();
                    database.child("users").child(userId).child("textRead").setValue(oldTextRead + " " + textID);
                    booksRead = (oldTextRead + " " + textID).length()/2+1;
                }

                if (!snap.child("users").child(userId).child("lix").exists()){
                    database.child("users").child(userId).child("lix").setValue(lix);
                }

                else {
                    oldLix = Integer.parseInt(snap.child("users").child(userId).child("lix").getValue().toString());
                    double temp1 = ((oldLix*(booksRead-1)) + lix)/booksRead;
                    int temp = (int) temp1;
                    database.child("users").child(userId).child("lix").setValue(temp);
                }
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


}
