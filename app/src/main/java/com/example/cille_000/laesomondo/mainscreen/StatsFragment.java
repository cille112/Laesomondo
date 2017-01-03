package com.example.cille_000.laesomondo.mainscreen;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.cille_000.laesomondo.R;

public class StatsFragment extends Fragment {

    private ImageButton stats;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stats, container, false);


        stats = (ImageButton) view.findViewById(R.id.StatsTMP);

        return view;
    }
}
