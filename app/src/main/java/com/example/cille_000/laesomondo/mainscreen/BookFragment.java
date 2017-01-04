package com.example.cille_000.laesomondo.mainscreen;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.cille_000.laesomondo.R;
import com.example.cille_000.laesomondo.challengescreen.TextInfoActivity;

import java.util.ArrayList;

//  Dette fragment viser alle bøgerne, hylderne osv. Det er 'startskærmen'
public class BookFragment extends Fragment implements View.OnClickListener {

    private ArrayList<ImageButton> coverList, adventureList, krimiList;
    private Boolean bool;

    public BookFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book, container, false);

        coverList.add((ImageButton) view.findViewById(R.id.imageButtonCover1));
        coverList.add((ImageButton) view.findViewById(R.id.imageButtonCover2));
        coverList.add((ImageButton) view.findViewById(R.id.imageButtonCover3));
        coverList.add((ImageButton) view.findViewById(R.id.imageButtonCover4));
        coverList.add((ImageButton) view.findViewById(R.id.imageButtonCover5));

        for (ImageButton i: coverList) {
            i.setOnClickListener(this);
        }

        adventureList.add((ImageButton) view.findViewById(R.id.imageButtonAdventure01));
        krimiList.add((ImageButton) view.findViewById(R.id.imageButtonKrimi1));

        bool = false;
        if (bool){
            for (ImageButton i: adventureList) {
                i.setOnClickListener(this);
            }
            for (int i = 0; i < krimiList.size(); i++) {
                krimiList.get(i).setVisibility(View.GONE);
            }

        }
        if (!bool) {
            for (int i = 0; i < adventureList.size(); i++) {
                adventureList.get(i).setVisibility(View.GONE);
            }
            for (ImageButton i: krimiList) {
                i.setOnClickListener(this);
            }
        }

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        coverList = new ArrayList<>();
        adventureList = new ArrayList<>();
        krimiList = new ArrayList<>();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        for(int i = 0; i < coverList.size(); i++) {
            if(v == coverList.get(i)) {
                Intent intent = new Intent(getActivity(), TextInfoActivity.class);
                intent.putExtra("textID",++i);
                startActivity(intent);
            }
        }

        for(int i = 0; i < adventureList.size(); i++) {
            if(v == adventureList.get(i)) {
                Intent intent = new Intent(getActivity(), TextInfoActivity.class);
                intent.putExtra("textID",++i+5);
                startActivity(intent);
            }
        }

        for(int i = 0; i < krimiList.size(); i++) {
            if(v == krimiList.get(i)) {
                Intent intent = new Intent(getActivity(), TextInfoActivity.class);
                intent.putExtra("textID",++i+6);
                startActivity(intent);
            }
        }
    }
}