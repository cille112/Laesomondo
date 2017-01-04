package com.example.cille_000.laesomondo.mainscreen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.cille_000.laesomondo.R;



public class AchievementFragment extends Fragment {

    private ImageButton achievementa, achievementb, achievementc, achievementd, achievemente, achievementf, achievementg, achievementh, achievementi;

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


        //    if(joiningTheTeam <= true)
        //    achievementa.setImageResource(R.drawable.fff); //der skal laves iconer til alle achievements

        //    if(userlevel <= 5)
        //    achievementb.setImageResource(R.drawable.fff);

        //    if(userlevel <= 10)
        //    achievementc.setImageResource(R.drawable.fff);

        //    if(userlevel <= 15)
        //    achievementd.setImageResource(R.drawable.fff);

        //    if(wordMin <= 20)
        //    achievemente.setImageResource(R.drawable.fff);

        //    if(wordMin <= 40)
        //    achievementf.setImageResource(R.drawable.fff);

        //    if(Lix <= 10)
        //    achievementg.setImageResource(R.drawable.fff);

        //    if(Lix <= 20)
        //    achievementh.setImageResource(R.drawable.fff);

        //    if(Lix <= 30)
        //    achievementi.setImageResource(R.drawable.fff);



        return view;
    }


}
