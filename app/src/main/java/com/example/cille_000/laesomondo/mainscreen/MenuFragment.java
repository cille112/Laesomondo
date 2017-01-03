package com.example.cille_000.laesomondo.mainscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cille_000.laesomondo.R;

public class MenuFragment extends Fragment implements View.OnClickListener {

    private TextView settings, help, contact;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        settings = (TextView) view.findViewById(R.id.menuSettings);
        help = (TextView) view.findViewById(R.id.menuHelp);
        contact = (TextView) view.findViewById(R.id.menuContact);

        settings.setOnClickListener(this);
        help.setOnClickListener(this);
        contact.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v == settings) {
            Intent intent = new Intent(getActivity(), SettingsActivity.class);
            startActivity(intent);
        } else if(v == help) {

        } else if(v == contact) {

        }

    }
}
