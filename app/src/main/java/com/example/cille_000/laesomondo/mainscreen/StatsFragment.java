package com.example.cille_000.laesomondo.mainscreen;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cille_000.laesomondo.R;
import com.numetriclabz.numandroidcharts.ChartData;
import com.numetriclabz.numandroidcharts.RadarChart;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class StatsFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stats, container, false);

        RadarChart radarChart = (RadarChart) view.findViewById(R.id.radar_chart);


        List<ChartData> values = new ArrayList<>();

        ArrayList<String> label = new ArrayList();
        label.add("Speed");
        label.add("concentration");
        label.add("Words/min");
        label.add("Lix");
        label.add("Punctuality");
        label.add("Problem");

        ArrayList<Float> entries = new ArrayList<>();
        entries.add(2f);
        entries.add(2f);
        entries.add(2f);
        entries.add(2f);
        entries.add(4f);
        entries.add(1f);

        ArrayList<Float> entries2 = new ArrayList<>();
        entries2.add(1f);
        entries2.add(1f);
        entries2.add(1f);
        entries2.add(1f);
        entries2.add(4f);
        entries2.add(6f);


        try {
            JSONObject dataSet = new JSONObject();
            dataSet.put("labels", label.toString());

            JSONObject val = new JSONObject();
            val.put("You", entries.toString());
            val.put("Average", entries2.toString());

            dataSet.put("values", val.toString());

            values.add(new ChartData(dataSet));
            radarChart.setData(values);
        }
        catch (JSONException e){
            e.printStackTrace();
        }

        return view;
    }
}
