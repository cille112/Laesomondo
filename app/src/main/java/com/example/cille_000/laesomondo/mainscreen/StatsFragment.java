package com.example.cille_000.laesomondo.mainscreen;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cille_000.laesomondo.R;
import com.example.cille_000.laesomondo.startscreen.StartActivity;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;



public class StatsFragment extends Fragment {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference database;
    private int lixValue, wordMinValue, correctness, booksRead;
    private String userId;
    private double correctnessD;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_stats, container, false);






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

            RadarChart chart = (RadarChart) view.findViewById(R.id.chart);

            @Override
            public void onDataChange(DataSnapshot snap) {

                //LIX
                if (!snap.child("users").child(userId).child("lix").exists()){
                    lixValue = 0;
                }else{
                    lixValue = Integer.parseInt(snap.child("users").child(userId).child("lix").getValue().toString());
                }

                // WORD/MIN
                if(!snap.child("users").child(userId).child("speed").exists()){
                    wordMinValue = 0;
                }else{
                    wordMinValue = Integer.parseInt(snap.child("users").child(userId).child("speed").getValue().toString());
                }

                // Correctness
                if(!snap.child("users").child(userId).child("correctness").exists()){
                    correctness = 0;
                }else{
                    correctnessD = Double.parseDouble(snap.child("users").child(userId).child("correctness").getValue().toString());
                    correctness = (int)(correctnessD);
                }

                // Correctness
                if(!snap.child("users").child(userId).child("textRead").exists()){
                    booksRead = 0;
                }else{
                    String bar = snap.child("users").child(userId).child("textRead").getValue().toString();
                    String[] split = bar.split( " " );
                    booksRead = split.length;
                }


                ArrayList<Entry> entries = new ArrayList<>();
                entries.add(new Entry(lixValue, 0));
                entries.add(new Entry(wordMinValue, 1));
                entries.add(new Entry(correctness, 2));
                entries.add(new Entry(booksRead, 3));
                entries.add(new Entry(10, 4));




                RadarDataSet dataset_data1 = new RadarDataSet(entries, "You");


                dataset_data1.setColor(Color.RED);
                dataset_data1.setDrawFilled(true);


                ArrayList<RadarDataSet> dataSets = new ArrayList<RadarDataSet>();
                dataSets.add(dataset_data1);

                ArrayList<String> labels = new ArrayList<String>();
                labels.add("Lix");
                labels.add("Ord/Min");
                labels.add("Korrekthed");
                labels.add("Læste Tekster");
                labels.add("Level");


                RadarData data = new RadarData(labels, dataSets);
                chart.setData(data);

                String description = "Her kan du se alt om dig";
                chart.setDescription(description);
                chart.setWebLineWidthInner(0.5f);
                chart.setDescriptionColor(Color.BLACK);

                chart.invalidate();
                chart.animate();

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });




        return view;
    }
}
