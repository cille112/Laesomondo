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
    private DatabaseReference database;
    private Toolbar mToolbar;
    private DrawerFragment drawerFragment;
    private ImageView profilePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null){
            finish();
            Intent intent1 = new Intent(this, StartActivity.class);
            startActivity(intent1);
        }

        database = FirebaseDatabase.getInstance().getReference();

        profilePicture = (ImageView) findViewById(R.id.menuprofilepic);
        database.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snap) {
                if(snap.child("users").child(firebaseAuth.getCurrentUser().getUid()) != null) {
                    profilePicture.setImageDrawable(getDrawable(Integer.parseInt(snap.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("avatar").getValue().toString())));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (DrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        // Det første, der skal vises
        displayView(0);
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
                break;
            case 2:
                fragment = new HelpFragment();
                title = getString(R.string.title_help);
                break;
            case 3:
                fragment = new ContactFragment();
                title = getString(R.string.title_contact);
                break;
            case 4:
                logout();
                break;
            case 5:
                fragment = new AchievementFragment();
                title = "Achievements";
                break;
            case 6:
                fragment = new StatsFragment();
                title = "Stats";
                break;
            case 7:
                fragment = new UserProfileFragment();
                title = getString(R.string.title_userprofile);
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