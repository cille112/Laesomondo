package com.example.cille_000.laesomondo.startscreen;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;


import com.example.cille_000.laesomondo.R;
import com.example.cille_000.laesomondo.entities.User;
import com.example.cille_000.laesomondo.logic.StartLogic;
import com.example.cille_000.laesomondo.mainscreen.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CreateUserActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    private static final String TAG = "EmailPassword";

    private ImageButton avatar;
    private Button signup;
    private EditText username, password, birthDate;
    private TextView login;
    private AvatarFragment avatarFragment;
    private StartLogic logic;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authListener;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createuser);

        avatar = (ImageButton) findViewById(R.id.createuser_picturebtn);
        username = (EditText) findViewById(R.id.createuser_name);
        password = (EditText) findViewById(R.id.createuser_password);
        birthDate = (EditText) findViewById(R.id.createuser_age);
        signup = (Button) findViewById(R.id.createuser_signup);
        login = (TextView) findViewById(R.id.createuser_login);

        avatarFragment = new AvatarFragment();
        logic = new StartLogic();

        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        birthDate.setOnTouchListener(this);
        avatar.setOnClickListener(this);
        signup.setOnClickListener(this);
        login.setOnClickListener(this);

        avatarFragment.setOnDoneListener(new AvatarFragment.OnDoneListener() {
            @Override
            public void onDone() {
                setAvatar(avatarFragment.getCurrent());
            }
        });

        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(logic.checkUsername(s.toString())) {
                    Drawable icon = ResourcesCompat.getDrawable(getResources(), R.drawable.checkmark,null);
                    username.setError(null, icon);
                }
                else {
                    Drawable icon = ResourcesCompat.getDrawable(getResources(), R.drawable.errormark,null);
                    username.setError(null, icon);
                }
            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(logic.checkPassword(s.toString())) {
                    Drawable icon = ResourcesCompat.getDrawable(getResources(), R.drawable.checkmark,null);
                    password.setError(null, icon);
                }
                else {
                    Drawable icon = ResourcesCompat.getDrawable(getResources(), R.drawable.errormark,null);
                    password.setError(null, icon);
                }
            }
        });

        setAvatar(avatarFragment.getCurrent());

        //Get firebase authentication
        firebaseAuth = FirebaseAuth.getInstance();

        //Check if there already is a user logged in
        if (firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        // Authentication listener, listen when something changes is authentication
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                //update screen after autehntication
                updateUI(user);
            }
        };

        database = FirebaseDatabase.getInstance().getReference();

    }

    @Override
    public void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            firebaseAuth.removeAuthStateListener(authListener);
        }
    }

    public void setAvatar(int index) {
        avatar.setTag(index);
        avatar.setBackgroundResource(index);
    }

    @Override
    public void onClick(View v) {
        if(v == avatar) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.activity_createuser, avatarFragment);
            transaction.commit();
        } else if(v == signup) {
            if (!validateForm()) {
                Toast.makeText(getApplicationContext(), "Krav til brugeroplysninger er ikke overholdt. Tjek efter og prøv igen", Toast.LENGTH_LONG).show();
            } else {
                createAccount(username.getText().toString(), password.getText().toString());
            }
        } else if(v == login) {
            this.finish();
        }
    }

    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);

        // [START create_user_with_email]
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(CreateUserActivity.this, R.string.reg_failed,
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
        // [END create_user_with_email]
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = username.getText().toString();
        if (TextUtils.isEmpty(email)) {
            valid = false;
        }

        String passwordString = password.getText().toString();
        if (TextUtils.isEmpty(passwordString)) {
            valid = false;
        }

        return valid;
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            writeNewUser(username.getText().toString(), birthDate.getText().toString(), avatarFragment.getCurrent(), 16);
            finish();
            Intent mainscreen = new Intent(this, ChallengeInfoActivity.class);
            startActivity(mainscreen);
        }
    }

    private void writeNewUser(String email, String date, int avatar, int textsize) {
        User user = new User(email, date, avatar, textsize);
        if(firebaseAuth.getCurrentUser()!=null) {
            database.child("users").child(firebaseAuth.getCurrentUser().getUid()).setValue(user);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
            if(event.getAction() != MotionEvent.ACTION_DOWN)
                return false;

            DatePickerFragment datePicker = new DatePickerFragment();

            datePicker.setCallback(new DatePickerFragment.OnSubmitListener() {
                @Override
                public void onSubmitted(int day, int month, int year) {
                    System.out.println("dag: " + day + " måned: " + month + " år: " + year);
                    birthDate.setText(day + "/" + month + "/" + year);
                }
            });

            datePicker.show(getSupportFragmentManager(), "datePicker");
            return true;
    }
}