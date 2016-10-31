package com.example.cille_000.laesomondo.firsttimescreen;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cille_000.laesomondo.R;

public class CreateUserFragment extends Fragment {

    TextView text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_createuser, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        text = (TextView) view.findViewById(R.id.test);
        text.setText("Hej");
    }
}

