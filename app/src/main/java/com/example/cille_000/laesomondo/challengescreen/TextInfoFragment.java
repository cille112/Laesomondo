package com.example.cille_000.laesomondo.challengescreen;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.example.cille_000.laesomondo.R;

import com.example.cille_000.laesomondo.entities.ReadingTest;

public class TextInfoFragment extends Fragment {

    private ReadingTest text;
    private Button button;

    public static TextInfoFragment newInstance(ReadingTest text) {
        TextInfoFragment fragment = new TextInfoFragment();
        fragment.setReadingText(text);

        return fragment;
    }

    public void setReadingText(ReadingTest text) {
        this.text = text;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_textinfo, container, false);

        button = (Button) view.findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ShowText.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
