package com.example.cille_000.laesomondo.firsttimescreen;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;

import com.example.cille_000.laesomondo.R;

public class StartActivity extends AppCompatActivity {

    RadioButton r1;
    RadioButton r2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));

        r1 = (RadioButton) findViewById(R.id.createuser_radio1);
        r2 = (RadioButton) findViewById(R.id.testinfo_radio2);
    }

    private class PagerAdapter extends FragmentPagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch(pos)
            {
                case 0:
                    r1.setChecked(false);
                    r2.setChecked(false);
                    return new CreateUserFragment();

                case 1:
                    r1.setChecked(false);
                    r2.setChecked(false);
                    return new TestInfoFragment();

                default:
                    r1.setChecked(false);
                    r2.setChecked(false);
                    return  new CreateUserFragment();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}

