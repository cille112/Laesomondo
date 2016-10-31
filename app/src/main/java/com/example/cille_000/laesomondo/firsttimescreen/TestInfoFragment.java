package com.example.cille_000.laesomondo.firsttimescreen;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cille_000.laesomondo.R;

public class TestInfoFragment extends Fragment {

    TextView text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_testinfo, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        text = (TextView) view.findViewById(R.id.testinfo_title);
        text.setText("Test info");
    }
}
