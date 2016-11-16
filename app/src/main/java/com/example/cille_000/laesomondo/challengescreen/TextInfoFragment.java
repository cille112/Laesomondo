package com.example.cille_000.laesomondo.challengescreen;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cille_000.laesomondo.R;

import com.example.cille_000.laesomondo.entities.ReadingTest;

public class TextInfoFragment extends Fragment {

    private ReadingTest text;

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
        return inflater.inflate(R.layout.fragment_textinfo, container, false);
    }
}
