package com.example.cille_000.laesomondo.startscreen;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.cille_000.laesomondo.R;
import com.example.cille_000.laesomondo.logic.StartLogic;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        LoginFragment login = new LoginFragment();
        fragmentTransaction.add(R.id.startactivity, login);
        fragmentTransaction.commit();
    }
}

