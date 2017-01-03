package com.example.cille_000.laesomondo.mainscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.cille_000.laesomondo.R;


public class UserProfileFragment extends Fragment implements View.OnClickListener {

    private TextView lixvalue, wordMinValue;
    private ImageButton achievement, stats;
    private AchievementFragment achievementscreen;
   // private StatsFragment Statsscreen;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_userprofile, container, false);
        achievementscreen = new AchievementFragment();
       // statsscreen = new StatsFragment();

        wordMinValue = (TextView) view.findViewById(R.id.userprofile_wordminvalue);
        lixvalue = (TextView) view.findViewById(R.id.userprofile_lixvalue);
        achievement = (ImageButton) view.findViewById(R.id.userprofile_achievement);
        stats = (ImageButton) view.findViewById(R.id.userprofile_stats);

        achievement.setOnClickListener(this);
        stats.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {
        if(v == achievement) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.main_framelayout, achievementscreen);
            transaction.commit();
        }

//        if(v == stats) {
//            FragmentTransaction transaction = getFragmentManager().beginTransaction();
//            transaction.replace(R.id.main_framelayout, statsscreen);
//            transaction.commit();
//        }
    }
}
