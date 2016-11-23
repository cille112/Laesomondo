package com.example.cille_000.laesomondo.mainscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.cille_000.laesomondo.R;
import com.example.cille_000.laesomondo.startscreen.CreateUserActivity;


public class UserProfileFragment extends Fragment implements View.OnClickListener {

    private TextView lixvalue, wordMinValue;
    private ImageButton achievement, stats, pilTilbage;

    public View onCreate(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_userprofile, container, false);


        wordMinValue = (TextView) view.findViewById(R.id.userprofile_wordminvalue);
        lixvalue = (TextView) view.findViewById(R.id.userprofile_lixvalue);
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
            Intent mainscreen = new Intent(getActivity(), MainActivity.class);
            startActivity(mainscreen);
        }
    }
}
