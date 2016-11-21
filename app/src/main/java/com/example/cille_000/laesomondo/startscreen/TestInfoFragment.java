package com.example.cille_000.laesomondo.startscreen;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.cille_000.laesomondo.R;
import com.example.cille_000.laesomondo.challengescreen.TextInfoFragment;
import com.example.cille_000.laesomondo.mainscreen.MainActivity;

public class TestInfoFragment extends Fragment implements View.OnClickListener {

    private Button btn;
    private TextView notnow;
    private TextInfoFragment textinfo;
    private Intent mainscreen;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_testinfo, container, false);

        btn = (Button) view.findViewById(R.id.testinfo_btn);
        btn.setOnClickListener(this);
        textinfo = TextInfoFragment.newInstance(1);

        notnow = (TextView) view.findViewById(R.id.testinfo_notnow);
        notnow.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v == btn)
        {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.activity_start, textinfo);
            transaction.remove(this);
            transaction.commit();
        }

        if(v == notnow)
        {
            mainscreen = new Intent(getActivity(), MainActivity.class);
            startActivity(mainscreen);
        }
    }
}
