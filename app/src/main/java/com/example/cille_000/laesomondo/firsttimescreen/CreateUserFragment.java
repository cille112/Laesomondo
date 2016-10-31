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

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.activity_start, container, false);

    }
}
