package com.example.cille_000.laesomondo.firsttimescreen;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import com.example.cille_000.laesomondo.R;


public class CreateUserFragment extends Fragment {

    static ImageButton avatar;
    EditText t1;
    EditText t2;


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_createuser, container, false);


        Context context = getActivity();
        SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.Prefrence_file_key),Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();

        avatar = (ImageButton) view.findViewById(R.id.createuser_picturebtn);
        t1 = (EditText) view.findViewById(R.id.createuser_name);
        t2 = (EditText) view.findViewById(R.id.createuser_age);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t1.setText("");
            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t2.setText("");
            }
        });
        t1.setText(sharedPref.getString("name", "Navn"));
        t2.setText(sharedPref.getString("alder", "Alder"));

        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("name", t1.getText().toString());
                editor.putString("alder", t2.getText().toString());
                editor.commit();
                Fragment avatarFragment = new AvatarFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.activity_start, avatarFragment );
                transaction.commit();
            }
        });

        return view;
    }
}

