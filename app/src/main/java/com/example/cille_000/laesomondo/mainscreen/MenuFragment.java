package com.example.cille_000.laesomondo.mainscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cille_000.laesomondo.R;
import com.example.cille_000.laesomondo.startscreen.StartActivity;
import com.google.firebase.auth.FirebaseAuth;

public class MenuFragment extends Fragment implements View.OnClickListener {

    private TextView settings, help, contact, logout;
    private FirebaseAuth firebaseAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null){
            startActivity(new Intent(getActivity(), StartActivity.class));
        }

        settings = (TextView) view.findViewById(R.id.menuSettings);
        help = (TextView) view.findViewById(R.id.menuHelp);
        contact = (TextView) view.findViewById(R.id.menuContact);
        logout = (TextView) view.findViewById(R.id.menulogout);

        settings.setOnClickListener(this);
        help.setOnClickListener(this);
        contact.setOnClickListener(this);
        logout.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v == settings) {
            Intent intent = new Intent(getActivity(), SettingsActivity.class);
            startActivity(intent);
        } else if(v == help) {
            Intent intent = new Intent(getActivity(), HelpActivity.class);
            startActivity(intent);
        } else if(v == contact) {
            Intent intent = new Intent(getActivity(), ContactActivity.class);
            startActivity(intent);
        } else if (v == logout){
            firebaseAuth.signOut();
            Intent intent = new Intent(getActivity(), StartActivity.class);
            startActivity(intent);
        }

    }
}
