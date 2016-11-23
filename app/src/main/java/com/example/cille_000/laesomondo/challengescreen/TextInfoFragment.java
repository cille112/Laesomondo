package com.example.cille_000.laesomondo.challengescreen;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.example.cille_000.laesomondo.R;
import com.example.cille_000.laesomondo.logic.TestLogic;

public class TextInfoFragment extends Fragment {

    private int textID;
    private Button button;
    private TestLogic logic;
    private TextView textInfo;
    private String info = "";
    private TextView textInfoname;

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
        textInfo = (TextView) view.findViewById(R.id.textInfo);
        textInfoname = (TextView) view.findViewById(R.id.textInfoName);

        info =  getString(R.string.Second) + " " + logic.getWriter() + "\n" +
                getString(R.string.Third) + " " + logic.getInfo();
        textInfo.setText(info);
        textInfoname.setText(logic.getName());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ShowTextActivity.class);
                intent.putExtra("textID", 5);
                startActivity(intent);
            }
        });

        return view;
    }
}
