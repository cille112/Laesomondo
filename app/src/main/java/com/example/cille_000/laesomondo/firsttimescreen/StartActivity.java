package com.example.cille_000.laesomondo.firsttimescreen;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.cille_000.laesomondo.R;

public class StartActivity extends FragmentActivity {

    Fragment createUser;
    Fragment testInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

//        FragmentActivity activity = new FragmentActivity();
//        android.app.FragmentManager manager = activity.getFragmentManager();
//        manager.getFragment(createUser, );


    }
}
