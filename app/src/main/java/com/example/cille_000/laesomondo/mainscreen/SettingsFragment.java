package com.example.cille_000.laesomondo.mainscreen;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.cille_000.laesomondo.R;


public class SettingsFragment extends Fragment implements View.OnClickListener, SeekBar.OnSeekBarChangeListener{

    private SeekBar textSize;
    private TextView currentText;

    public SettingsFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        textSize = (SeekBar) view.findViewById(R.id.textsizebar);
        currentText = (TextView) view.findViewById(R.id.currenttextsize);
        textSize.setOnSeekBarChangeListener(this);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        float size = 16;

        if(progress == 0) {
            size = 16;
        } else {
            size += progress / 5;
        }

        currentText.setTextSize(size);
        currentText.setText("" + size);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
