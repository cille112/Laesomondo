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
                    correctness = (int)(correctnessD/3*100);
                }

                // Correctness
                if(!snap.child("users").child(userId).child("textRead").exists()){
                    booksRead = 0;
                }else{
                    String bar = snap.child("users").child(userId).child("textRead").getValue().toString();
                    String[] split = bar.split( " " );
                    booksRead = split.length;
                }


                ArrayList<Entry> entries1 = new ArrayList<>();
                entries1.add(new Entry(lixValue, 0));
                entries1.add(new Entry(wordMinValue, 1));
                entries1.add(new Entry(correctness, 2));
                entries1.add(new Entry(booksRead, 3));
                entries1.add(new Entry(10, 4));

                ArrayList<Entry> entries2 = new ArrayList<>();
                entries2.add(new Entry(30, 0));
                entries2.add(new Entry(60, 1));
                entries2.add(new Entry(70, 2));
                entries2.add(new Entry(4, 3));
                entries2.add(new Entry(7, 4));




                RadarDataSet dataset_data1 = new RadarDataSet(entries1, getString(R.string.StatsFragmentDataOne));
                RadarDataSet dataset_data2 = new RadarDataSet(entries2, getString(R.string.StatsFragmentDataTwo));


                dataset_data1.setColor(Color.RED);
                dataset_data1.setDrawFilled(true);
                dataset_data1.setValueTextSize(10);
                dataset_data1.setDrawHighlightIndicators(false);

                dataset_data2.setColor(Color.BLUE);
                dataset_data2.setDrawFilled(true);
                dataset_data2.setValueTextSize(10);
                dataset_data2.setDrawHighlightIndicators(false);


                ArrayList<RadarDataSet> dataSets = new ArrayList<RadarDataSet>();
                dataSets.add(dataset_data1);
                dataSets.add(dataset_data2);


                ArrayList<String> labels = new ArrayList<String>();
                labels.add(getString(R.string.StatsFragmentLabelOne));
                labels.add(getString(R.string.StatsFragmentLabelTwo));
                labels.add(getString(R.string.StatsFragmentLabelThree));
                labels.add(getString(R.string.StatsFragmentLabelFour));
                labels.add(getString(R.string.StatsFragmentLabelFive));


                RadarData data = new RadarData(labels, dataSets);
                chart.setData(data);

                String description = "";
                chart.setDescription(description);
                chart.setWebLineWidthInner(0.5f);
                chart.setDescriptionColor(Color.BLACK);
                chart.setDescriptionTextSize(10);
                chart.getYAxis().setDrawLabels(false);
                chart.getXAxis().setTextSize(15);
                chart.getLegend().setTextSize(15);
                chart.getLegend().setFormSize(12);
                chart.getLegend().setXEntrySpace(25);

                chart.invalidate();
                chart.setRotationEnabled(false);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        return view;
    }
}
