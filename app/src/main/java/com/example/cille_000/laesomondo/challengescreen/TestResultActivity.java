package com.example.cille_000.laesomondo.challengescreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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
    private int oldSpeed;
    private double oldCorrectness;
    private String texts;
    private List<String> textReadArray = new ArrayList<>();
    private int seconds;
    private String oldTextRead;
    private Intent intent;
    private String category;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testresult);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()==null){
            finish();
            Intent intent1 = new Intent(this, StartActivity.class);
            startActivity(intent1);
        }

        database = FirebaseDatabase.getInstance().getReference();
        userId = firebaseAuth.getCurrentUser().getUid();

        intent = getIntent();
        category = intent.getStringExtra("category");
        textID = intent.getIntExtra("textID", 1);

        ok = (Button) findViewById(R.id.TestButton);

        ok.setOnClickListener(this);



        update();

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void update(){

        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snap) {
                if (!snap.child("users").child(userId).child("textRead").exists()) {
                    oldTextRead = "";
                }
                else
                    oldTextRead = snap.child("users").child(userId).child("textRead").getValue().toString();
                char c;
                char c2;
                String s = "";
                oldTextRead = oldTextRead + " ";
                int i = 0;
                while (i<oldTextRead.length()-1) {
                    c = oldTextRead.charAt(i);
                    c2 = oldTextRead.charAt(i + 1);

                    if (c2 == ' ') {
                        s = s + Character.toString(c);
                        textReadArray.add(s);
                        s = "";
                        i=i+2;
                    }

                    else {
                        s = s + c;
                        i++;
                    }
                }
                oldTextRead = oldTextRead.substring(0, oldTextRead.length()-1);
                String string = Integer.toString(textID)+category;
                if (!textReadArray.contains(string)){
                    updateDBStats();
                }
                else{
                    info = (TextView) findViewById(R.id.resultInfo);
                    info.setText("Du har allerede gennemført denne test");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }

    public void updateDBStats(){
        time = intent.getLongExtra("time", 0);
        correct = intent.getIntExtra("correct", 0);
        xp = intent.getIntExtra("xp", 0);
        logic = new TestLogic(this);
        logic.setText(textID, category);
        lix = intent.getIntExtra("lix", 0);
        wordCount = intent.getIntExtra("wordCount", 0);

        info = (TextView) findViewById(R.id.resultInfo);

        seconds = (int) (time / 1000);
        info.setText("Antal korrekte svar: " + correct + "\nDu læste teksten på " + seconds + " sekunder. \nDu får " + xp + " xp");



        database.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snap) {

                //texts that has been read
                if (!snap.child("users").child(userId).child("textRead").exists()) {
                    database.child("users").child(userId).child("textRead").setValue(textID+category);
                }
                else {
                    database.child("users").child(userId).child("textRead").setValue(oldTextRead + " " + textID+category);
                    if(snap.child("users").child(userId).child("Genre").exists()) {
                        String genre = snap.child("users").child(userId).child("Genre").getValue().toString();
                        if(!genre.contains("Roman")) {
                            database.child("users").child(userId).child("Genre").setValue(genre + " " + "Roman");
                            Toast.makeText(getApplicationContext(), "Du har fået en ny genre", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                //xp
                if (!snap.child("users").child(userId).child("xp").exists()) {
                    database.child("users").child(userId).child("xp").setValue(xp);
                }
                else{
                    oldXp = Integer.parseInt(snap.child("users").child(userId).child("xp").getValue().toString());
                    database.child("users").child(userId).child("xp").setValue(oldXp + xp);
                }
                //level
                if (!snap.child("users").child(userId).child("level").exists()) {
                    database.child("users").child(userId).child("level").setValue(1);
                }
                else{
                    if (snap.child("users").child(userId).child("xp").exists()) {
                        int totalXp = (Integer.parseInt(snap.child("users").child(userId).child("xp").getValue().toString())+xp);
                        for (int i = 2; i < 11; i++) {
                            if(totalXp > (int) (150 * (Math.pow(i,(1.5))))){
                                database.child("users").child(userId).child("level").setValue(i);
                            }
                        }
                    }
                }


                //lix
                if (!snap.child("users").child(userId).child("lix").exists()){
                    database.child("users").child(userId).child("lix").setValue(lix);
                }

                else {
                    oldLix = Integer.parseInt(snap.child("users").child(userId).child("lix").getValue().toString());
                    double temp1 = ((oldLix*(booksRead-1)) + lix)/booksRead;
                    int temp = (int) temp1;
                    database.child("users").child(userId).child("lix").setValue(temp);
                }
                //readingspeed words/min
                if (!snap.child("users").child(userId).child("speed").exists()){
                    double speed = wordCount/seconds*60;
                    int temp = (int) speed;
                    database.child("users").child(userId).child("speed").setValue(temp);
                }
                else {
                    oldSpeed = Integer.parseInt(snap.child("users").child(userId).child("speed").getValue().toString());
                    double speed = wordCount/seconds*60;
                    double temp1 = ((oldSpeed*(booksRead-1)) + speed)/booksRead;
                    int temp = (int) temp1;
                    database.child("users").child(userId).child("speed").setValue(temp);
                }
                //correctness
                if (!snap.child("users").child(userId).child("correctness").exists()){
                    double d = correct;
                    database.child("users").child(userId).child("correctness").setValue(d);
                }
                else {
                    oldCorrectness = Double.parseDouble(snap.child("users").child(userId).child("correctness").getValue().toString());
                    double bR = booksRead;
                    double crr = correct;
                    double temp1 = ((oldCorrectness*(bR-1)) + crr)/bR;
                    database.child("users").child(userId).child("correctness").setValue(temp1);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() { }

}
