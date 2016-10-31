package com.example.cille_000.laesomondo.firsttimescreen;

import android.os.Bundle;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.cille_000.laesomondo.R;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.testFrameLayout, new CreateUserFragment());

        transaction.commit();
    }
}
