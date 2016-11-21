package com.example.cille_000.laesomondo.challengescreen;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.example.cille_000.laesomondo.R;
import com.example.cille_000.laesomondo.logic.TestLogic;

public class TextInfoFragment extends Fragment {

    private int textID;
    private Button button;
    private TestLogic logic;

    public static TextInfoFragment newInstance(int text) {
        TextInfoFragment fragment = new TextInfoFragment();
        fragment.setTextId(text);
        return fragment;
    }

    public void setTextId(int text) {
        this.textID = text;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_textinfo, container, false);

        button = (Button) view.findViewById(R.id.button3);
        logic = new TestLogic(textID, getActivity());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ShowTextActivity.class);
                intent.putExtra("textID", 1);
                startActivity(intent);
            }
        });

        return view;
    }
}
