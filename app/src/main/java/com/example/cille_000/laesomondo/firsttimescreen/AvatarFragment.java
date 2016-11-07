package com.example.cille_000.laesomondo.firsttimescreen;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.cille_000.laesomondo.R;

import java.sql.Date;

import Entities.User;

public class AvatarFragment extends Fragment implements View.OnClickListener {

    ImageButton av1, av2, av3, av4, av5, av6, av7, av8, av9;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_avatar, container, false);



        av1 = (ImageButton) view.findViewById(R.id.imageButton1);
        av2 = (ImageButton) view.findViewById(R.id.imageButton2);
        av3 = (ImageButton) view.findViewById(R.id.imageButton3);
        av4 = (ImageButton) view.findViewById(R.id.imageButton4);
        av5 = (ImageButton) view.findViewById(R.id.imageButton5);
        av6 = (ImageButton) view.findViewById(R.id.imageButton6);
        av7 = (ImageButton) view.findViewById(R.id.imageButton7);
        av8 = (ImageButton) view.findViewById(R.id.imageButton8);
        av9 = (ImageButton) view.findViewById(R.id.imageButton9);


        av1.setOnClickListener(this);
        av2.setOnClickListener(this);
        av3.setOnClickListener(this);
        av4.setOnClickListener(this);
        av5.setOnClickListener(this);
        av6.setOnClickListener(this);
        av7.setOnClickListener(this);
        av8.setOnClickListener(this);
        av9.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        Context context = getActivity();
        SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.Prefrence_file_key),Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        if (v == av1) {
            editor.putString("image" , "av1");
            transaction.remove(this).commit();}
        if (v == av2) {
            editor.putString("image" , "av2");
            transaction.remove(this).commit();}
        if (v == av3) {
            editor.putString("image" , "av3");
            transaction.remove(this).commit();}
        if (v == av4) {
            editor.putString("image" , "av4");
            transaction.remove(this).commit();}
        if (v == av5) {
            editor.putString("image" , "av5");
            transaction.remove(this).commit();}
        if (v == av6) {
            editor.putString("image" , "av6");
            transaction.remove(this).commit();}
        if (v == av7) {
            editor.putString("image" , "av7");
            transaction.remove(this).commit();}
        if (v == av8) {
            editor.putString("image" , "av8");
            transaction.remove(this).commit();}
        if (v == av9) {
            editor.putString("image" , "av9");
            transaction.remove(this).commit();}
        editor.commit();
    }
}
