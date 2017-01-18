package com.example.cille_000.laesomondo.mainscreen;


import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.cille_000.laesomondo.R;
import com.example.cille_000.laesomondo.entities.User;
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
    private ImageView profilePicture;
    private TextView email;
    private UserProfileFragment userProfileFragment;
    private SettingsFragment settingsFragment;
    private HelpFragment helpFragment;
    private ContactFragment contactFragment;
    private AchievementFragment achievementFragment;
    private StatsFragment statsFragment;
    private String userId, genre;
    public static int current;
    private View icon;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();


        if(firebaseAuth.getCurrentUser() == null){
            close();
        } else {
            userId = firebaseAuth.getCurrentUser().getUid();
            email = (TextView) findViewById(R.id.menuprofilename);
            email.setText(firebaseAuth.getCurrentUser().getEmail().toString());
        }

        profilePicture = (ImageView) findViewById(R.id.menuprofilepic);


        userProfileFragment = new UserProfileFragment();
        settingsFragment = new SettingsFragment();
        helpFragment = new HelpFragment();
        contactFragment = new ContactFragment();
        achievementFragment = new AchievementFragment();
        statsFragment = new StatsFragment();

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        icon = findViewById(R.id.action_search);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        DrawerFragment drawerFragment = (DrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        // Det view der skal vises
//        displayView(current);

        new LoadViewTask().execute();
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

        if(id == R.id.action_search) {
            displayView(7);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    @Override
    public void onBackPressed() {
        if(current == 6 || current == 5){
            displayView(7);
        }else {
            displayView(0);
        }
    }

    public void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        Bundle bundle = new Bundle();
        switch (position) {
            case 0:
                fragment = new BookFragment();
                bundle.putString("genre", genre);
                fragment.setArguments(bundle);
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
                fragment = achievementFragment;
                title = getString(R.string.title_achievements);
                current = 5;
                break;
            case 6:
                fragment = statsFragment;
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


    private void collectData(){
        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snap) {
                if (snap.child("users").child(userId).hasChild("avatar")) {
                    profilePicture.setImageDrawable(getResources().getDrawable(Integer.parseInt(snap.child("users").child(userId).child("avatar").getValue().toString())));
                } else {
                    Toast.makeText(getApplicationContext(), "Der skete en fejl i indlæsning af billeder", Toast.LENGTH_SHORT).show();
                    close();
                }
                if (!snap.child("users").child(userId).hasChild("Genre")) {
                    String standardGenre = "Anbefalet Gyser Krimi Adventure";
                    database.child("users").child(userId).child("Genre").setValue(standardGenre);
                    genre = "Anbefalet Gyser Krimi Adventure";
                }
                else {
                    genre = snap.child("users").child(userId).child("Genre").getValue().toString();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }


    // Asynctask, loader mens bøgerne indlæses
    private class LoadViewTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(MainActivity.this, "Henter bøger",
                    "Vent venligst...", false, false);
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                synchronized (this) {
                    collectData();

                    if(!isCancelled()) {
                        int i = 0;
                        while (genre==null) {
                            this.wait(500);
                            System.out.println(i);
                            if(i++ == 40){
                                this.cancel(true);
                            }
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        // Efter ventetid
        @Override
        protected void onPostExecute(Void result) {
            progressDialog.dismiss();
            displayView(current);
        }
        @Override
        protected void onCancelled(){
            progressDialog.dismiss();
            logout();
        }
    }

}