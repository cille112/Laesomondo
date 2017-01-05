package com.example.cille_000.laesomondo.mainscreen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.cille_000.laesomondo.R;



public class AchievementFragment extends Fragment implements View.OnClickListener {

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

        achievementa.setOnClickListener(this);
        achievementb.setOnClickListener(this);
        achievementc.setOnClickListener(this);
        achievementd.setOnClickListener(this);
        achievemente.setOnClickListener(this);
        achievementf.setOnClickListener(this);
        achievementg.setOnClickListener(this);
        achievementh.setOnClickListener(this);
        achievementi.setOnClickListener(this);

            if(true == true) { // first test taken
                achievementa.setImageResource(R.drawable.achievement1);
            } else{
                achievementa.setImageResource(R.drawable.achievementlocked);
            }
            if(5 >= 5) { //userlevel
                achievementb.setImageResource(R.drawable.achievement2);
            } else{
                achievementb.setImageResource(R.drawable.achievementlocked);
            }
            if(10 >= 10) { //userlevel
                achievementc.setImageResource(R.drawable.achievement3);
            } else{
                achievementc.setImageResource(R.drawable.achievementlocked);
            }
            if(15 >= 15) { //userlevel
                achievementd.setImageResource(R.drawable.achievement4);
            } else{
                achievementd.setImageResource(R.drawable.achievementlocked);
            }
            if(0 >= 20) { //wordMin
                achievemente.setImageResource(R.drawable.achievement5);
            } else{
                achievemente.setImageResource(R.drawable.achievementlocked);
            }
            if(0 >= 40) { //wordMin
                achievementf.setImageResource(R.drawable.achievement6);
            } else{
                achievementf.setImageResource(R.drawable.achievementlocked);
            }
            if(0 >= 10) { //LIX
                achievementg.setImageResource(R.drawable.achievement7);
            } else{
                achievementg.setImageResource(R.drawable.achievementlocked);
            }
            if(0 >= 20) { //LIX
                achievementh.setImageResource(R.drawable.achievement8);
            } else{
                achievementh.setImageResource(R.drawable.achievementlocked);
            }
            if(0 >= 30) { //LIX
                achievementi.setImageResource(R.drawable.achievement9);
            } else{
                achievementi.setImageResource(R.drawable.achievementlocked);
            }

        return view;
    }


    @Override
    public void onClick(View v) {

        if(v == achievementa){
                if(true == true) {
                    Toast.makeText(getActivity(), "Hurra.. Du gennemførte din første test",
                            Toast.LENGTH_LONG).show();
                }
        }

        if(v == achievementb) {
                if(5 >= 5){
                    Toast.makeText(getActivity(), "Du er blevet level Fem",
                            Toast.LENGTH_LONG).show();
                }
        }

        if(v == achievementc){
               if(10 >= 10){
                   Toast.makeText(getActivity(), "Du er blevet level Ti",
                           Toast.LENGTH_LONG).show();
               }
        }

        if(v == achievementd){
                if(15 >= 15){
                    Toast.makeText(getActivity(), "Du er blevet level Femten",
                            Toast.LENGTH_LONG).show();
                }
        }

        if(v == achievemente){
                if(0 >= 20){
                    Toast.makeText(getActivity(), "20 ord i minuttet",
                            Toast.LENGTH_LONG).show();
                }
        }

        if(v == achievementf){
                if(0 >= 40){
                    Toast.makeText(getActivity(), "40 ord i minuttet",
                            Toast.LENGTH_LONG).show();
                }
        }

        if(v == achievementg){
                if(0 >= 10){
                    Toast.makeText(getActivity(), "Du læser med sværhedsgraden Lix 10",
                            Toast.LENGTH_LONG).show();
                }
        }

        if(v == achievementh){
                if(0 >= 20){
                    Toast.makeText(getActivity(), "Du læser med sværhedsgraden Lix 20",
                            Toast.LENGTH_LONG).show();
                }
        }

        if(v == achievementi){
               if(0 >= 30){
                   Toast.makeText(getActivity(), "Du læser med sværhedsgraden Lix 30",
                           Toast.LENGTH_LONG).show();
               }
        }


    }
}
