package com.example.cille_000.laesomondo.firsttimescreen;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.cille_000.laesomondo.R;

import java.sql.Date;

import Entities.User;

public class StartActivity extends AppCompatActivity {



    RadioButton r1;
    RadioButton r2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        SharedPreferences sharedPred = getSharedPreferences(getString(R.string.Prefrence_file_key), MODE_PRIVATE);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));

        r1 = (RadioButton) findViewById(R.id.createuser_radio1);
        r2 = (RadioButton) findViewById(R.id.testinfo_radio2);


        ViewPager.OnPageChangeListener PageListener = new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch(position)
                {
                    case 0:
                        r1.setChecked(true);
                        break;

                    case 1:
                        r2.setChecked(true);
                        break;

                    default:
                        r1.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };

        viewPager.addOnPageChangeListener(PageListener);
    }

    private class PagerAdapter extends FragmentPagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch(pos)
            {
                case 0: return new CreateUserFragment();

                case 1: return new TestInfoFragment();

                default: return  new CreateUserFragment();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }




}

