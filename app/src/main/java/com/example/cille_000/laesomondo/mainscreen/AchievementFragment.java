package com.example.cille_000.laesomondo.mainscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.cille_000.laesomondo.R;
import com.example.cille_000.laesomondo.startscreen.StartActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class AchievementFragment extends Fragment implements View.OnClickListener {

    private ImageButton achievementa, achievementb, achievementc, achievementd, achievemente, achievementf, achievementg, achievementh, achievementi;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference database;
    private int lixValue, XPValue, wordMinValue, userLevel;
    private String userId;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_achievement, container, false);

        achievementa = (ImageButton) view.findViewById(R.id.achievement_a);
        achievementb = (ImageButton) view.findViewById(R.id.achievement_b);
        achievementc = (ImageButton) view.findViewById(R.id.achievement_c);
        achievementd = (ImageButton) view.findViewById(R.id.achievement_d);
        achievemente = (ImageButton) view.findViewById(R.id.achievement_e);
        achievementf = (ImageButton) view.findViewById(R.id.achievement_f);
        achievementg = (ImageButton) view.findViewById(R.id.achievement_g);
        achievementh = (ImageButton) view.findViewById(R.id.achievement_h);
        achievementi = (ImageButton) view.findViewById(R.id.achievement_i);

        achievementa.setOnClickListener(this);
        achievementb.setOnClickListener(this);
        achievementc.setOnClickListener(this);
        achievementd.setOnClickListener(this);
        achievemente.setOnClickListener(this);
        achievementf.setOnClickListener(this);
        achievementg.setOnClickListener(this);
        achievementh.setOnClickListener(this);
        achievementi.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance().getReference();

        if(firebaseAuth.getCurrentUser() == null){
            Intent intent1 = new Intent(getActivity(), StartActivity.class);
            startActivity(intent1);
        }
        else {
            userId = firebaseAuth.getCurrentUser().getUid();
        }

        database.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot snap) {

                if (!snap.child("users").child(userId).child("lix").exists()){
                    lixValue = 0;
                }else{
                    lixValue = Integer.parseInt(snap.child("users").child(userId).child("lix").getValue().toString());
                }

                if(!snap.child("users").child(userId).child("xp").exists()){
                    XPValue = 0;
                }else{
                    XPValue = Integer.parseInt(snap.child("users").child(userId).child("xp").getValue().toString());
                }

                if(!snap.child("users").child(userId).child("speed").exists()){
                    wordMinValue = 0;
                }else{
                    wordMinValue = Integer.parseInt(snap.child("users").child(userId).child("speed").getValue().toString());
                }
                if(!snap.child("users").child(userId).child("level").exists()){
                    userLevel = 0;
                }else{
                    userLevel = Integer.parseInt(snap.child("users").child(userId).child("level").getValue().toString());
                }


                if(XPValue >= 1) { // first test taken
                    achievementa.setImageResource(R.drawable.achievement1);
                } else{
                    achievementa.setImageResource(R.drawable.achievementlocked);
                }
                if(userLevel >= 1) { //userlevel
                    achievementb.setImageResource(R.drawable.achievement2);
                } else{
                    achievementb.setImageResource(R.drawable.achievementlocked);
                }
                if(userLevel >= 5) { //userlevel
                    achievementc.setImageResource(R.drawable.achievement3);
                } else{
                    achievementc.setImageResource(R.drawable.achievementlocked);
                }
                if(userLevel >= 10) { //userlevel
                    achievementd.setImageResource(R.drawable.achievement4);
                } else{
                    achievementd.setImageResource(R.drawable.achievementlocked);
                }
                if(wordMinValue >= 150) { //wordMin
                    achievemente.setImageResource(R.drawable.achievement5);
                } else{
                    achievemente.setImageResource(R.drawable.achievementlocked);
                }
                if(wordMinValue >= 250) { //wordMin
                    achievementf.setImageResource(R.drawable.achievement6);
                } else{
                    achievementf.setImageResource(R.drawable.achievementlocked);
                }
                if(lixValue >= 25) { //LIX
                    achievementg.setImageResource(R.drawable.achievement7);
                } else{
                    achievementg.setImageResource(R.drawable.achievementlocked);
                }
                if(lixValue >= 30) { //LIX
                    achievementh.setImageResource(R.drawable.achievement8);
                } else{
                    achievementh.setImageResource(R.drawable.achievementlocked);
                }
                if(lixValue >= 35) { //LIX
                    achievementi.setImageResource(R.drawable.achievement9);
                } else{
                    achievementi.setImageResource(R.drawable.achievementlocked);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });



        return view;
    }


    @Override
    public void onClick(View v) {

        if(v == achievementa){
                if(XPValue >= 1) {
                    Toast.makeText(getActivity(), getString(R.string.AchievementFragmentAInfo),
                            Toast.LENGTH_LONG).show();
                }
        }

        if(v == achievementb) {
                if(userLevel >= 1){
                    Toast.makeText(getActivity(), getString(R.string.AchievementFragmentBInfo),
                            Toast.LENGTH_LONG).show();
                }
        }

        if(v == achievementc){
               if(userLevel >= 5){
                   Toast.makeText(getActivity(), getString(R.string.AchievementFragmentCInfo),
                           Toast.LENGTH_LONG).show();
               }
        }

        if(v == achievementd){
                if(userLevel >= 10){
                    Toast.makeText(getActivity(), getString(R.string.AchievementFragmentDInfo),
                            Toast.LENGTH_LONG).show();
                }
        }

        if(v == achievemente){
                if(wordMinValue >= 150){
                    Toast.makeText(getActivity(), getString(R.string.AchievementFragmentEInfo),
                            Toast.LENGTH_LONG).show();
                }
        }

        if(v == achievementf){
                if(wordMinValue >= 250){
                    Toast.makeText(getActivity(), getString(R.string.AchievementFragmentFInfo),
                            Toast.LENGTH_LONG).show();
                }
        }

        if(v == achievementg){
                if(lixValue >= 25){
                    Toast.makeText(getActivity(), getString(R.string.AchievementFragmentGInfo),
                            Toast.LENGTH_LONG).show();
                }
        }

        if(v == achievementh){
                if(lixValue >= 30){
                    Toast.makeText(getActivity(), getString(R.string.AchievementFragmentHInfo),
                            Toast.LENGTH_LONG).show();
                }
        }

        if(v == achievementi){
               if(lixValue >= 35){
                   Toast.makeText(getActivity(), getString(R.string.AchievementFragmentIInfo),
                           Toast.LENGTH_LONG).show();
               }
        }


    }
}
