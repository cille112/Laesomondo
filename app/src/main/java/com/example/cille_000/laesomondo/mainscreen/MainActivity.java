package com.example.cille_000.laesomondo.mainscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cille_000.laesomondo.R;
import com.example.cille_000.laesomondo.startscreen.StartActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends ActionBarActivity implements DrawerFragment.DrawerFragmentListener {

    private static String TAG = MainActivity.class.getSimpleName();
    private FirebaseAuth firebaseAuth;
    private ImageView profilePicture;
    private UserProfileFragment userProfileFragment;
    private SettingsFragment settingsFragment;
    private HelpFragment helpFragment;
    private ContactFragment contactFragment;
    private String userId;
    private static int current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null){
            close();
        } else {
            userId = firebaseAuth.getCurrentUser().getUid();
        }

        profilePicture = (ImageView) findViewById(R.id.menuprofilepic);
        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snap) {
                if (snap.child("users").child(userId).hasChild("avatar")) {
                    profilePicture.setImageDrawable(getResources().getDrawable(Integer.parseInt(snap.child("users").child(userId).child("avatar").getValue().toString())));
                } else {
                    Toast.makeText(getApplicationContext(), "Der skete en fejl i indlæsning af billeder", Toast.LENGTH_SHORT).show();
                    close();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        userProfileFragment = new UserProfileFragment();
        settingsFragment = new SettingsFragment();
        helpFragment = new HelpFragment();
        contactFragment = new ContactFragment();

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        DrawerFragment drawerFragment = (DrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        // Det første, der skal vises
        displayView(current);
        System.out.println(current);
    }

    private void close() {
        finish();
        Intent intent1 = new Intent(this, StartActivity.class);
        startActivity(intent1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_search){
            displayView(7);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    public void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new BookFragment();
                title = getString(R.string.title_books);
                current = 0;
                break;
            case 1:
                fragment = settingsFragment;
                title = getString(R.string.title_settings);
                current = 1;
                break;
            case 2:
                fragment = helpFragment;
                title = getString(R.string.title_help);
                current = 2;
                break;
            case 3:
                fragment = contactFragment;
                title = getString(R.string.title_contact);
                current = 3;
                break;
            case 4:
                logout();
                break;
            case 5:
                fragment = new AchievementFragment();
                title = getString(R.string.title_achievements);
                current = 5;
                break;
            case 6:
                fragment = new StatsFragment();
                title = getString(R.string.title_stats);
                current = 6;
                break;
            case 7:
                fragment = userProfileFragment;
                title = getString(R.string.title_userprofile);
                current = 7;
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // Toolbar title
            getSupportActionBar().setTitle(title);
        }
    }

    private void logout() {
        firebaseAuth.signOut();
        finish();
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        displayView(0);
    }
}