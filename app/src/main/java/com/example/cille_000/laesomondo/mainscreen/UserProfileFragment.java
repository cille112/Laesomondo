package com.example.cille_000.laesomondo.mainscreen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.cille_000.laesomondo.R;


public class UserProfileFragment extends Fragment implements View.OnClickListener {

    private EditText lixvalue, wordMinValue;
    private ImageButton achievement, stats, pilTilbage;

    public View onCreate(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_userprofile, container, false);


        wordMinValue = (EditText) view.findViewById(R.id.userprofile_wordminvalue);
        lixvalue = (EditText) view.findViewById(R.id.userprofile_lixvalue);
        achievement = (ImageButton) view.findViewById(R.id.userprofile_achievement);
        stats = (ImageButton) view.findViewById(R.id.userprofile_stats);
        pilTilbage = (ImageButton) view.findViewById(R.id.userprofile_piltilbage);

        achievement.setOnClickListener(this);
        stats.setOnClickListener(this);
        pilTilbage.setOnClickListener(this);

        return view;

    }

    @Override
    public void onClick(View v) {
        if(v == achievement) {
        }

        if(v == stats) {
        }

        if(v == pilTilbage) {
        }
    }
}
