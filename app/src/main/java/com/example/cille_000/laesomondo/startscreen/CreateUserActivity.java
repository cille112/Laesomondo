package com.example.cille_000.laesomondo.startscreen;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioButton;

import com.example.cille_000.laesomondo.R;
import com.example.cille_000.laesomondo.logic.StartLogic;
import com.example.cille_000.laesomondo.util.TouchListener;
import com.example.cille_000.laesomondo.util.Validator;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;


public class CreateUserActivity extends AppCompatActivity {

    private RadioButton r1, r2;
    private CreateUserFragment createUser;
    private TestInfoFragment testInfo;
    private StartLogic logic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createuser);

        logic =  new StartLogic();
        createUser = new CreateUserFragment();
        testInfo = new TestInfoFragment();

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new CreateUserActivity.PagerAdapter(getSupportFragmentManager()));

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

        private PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch(pos)
            {
                case 0: return createUser;

                case 1: return testInfo;

                default: return createUser;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    public void close() {
        finish();
    }
}