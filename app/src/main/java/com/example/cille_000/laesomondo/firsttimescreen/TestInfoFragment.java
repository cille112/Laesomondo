package com.example.cille_000.laesomondo.firsttimescreen;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cille_000.laesomondo.R;

public class TestInfoFragment extends Fragment implements View.OnClickListener {

    private TextView notnow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_testinfo, container, false);

        notnow = (TextView) view.findViewById(R.id.testinfo_notnow);
        notnow.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v == notnow)
        {
            System.out.println("NOT NOW");
        }
    }
}
