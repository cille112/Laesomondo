package startscreen;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;

import com.example.cille_000.laesomondo.R;

import logic.StartLogic;

public class StartActivity extends AppCompatActivity {

    private RadioButton r1, r2;
    private StartLogic logic;
    private SharedPreferences pref;
    private CreateUserFragment createUser;
    private TestInfoFragment testInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        createUser = new CreateUserFragment();
        testInfo = new TestInfoFragment();

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));

        r1 = (RadioButton) findViewById(R.id.createuser_radio1);
        r2 = (RadioButton) findViewById(R.id.testinfo_radio2);

        pref = getSharedPreferences("User", Context.MODE_PRIVATE);
        logic = new StartLogic(pref);

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
                        logic.saveUserInfo(createUser.getName(), createUser.getDate(), createUser.getAvatar());
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
}

