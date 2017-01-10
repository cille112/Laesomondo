package com.example.cille_000.laesomondo.startscreen;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.example.cille_000.laesomondo.R;
import com.example.cille_000.laesomondo.entities.User;
import com.example.cille_000.laesomondo.logic.StartLogic;
import com.example.cille_000.laesomondo.mainscreen.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CreateUserActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    private static final String TAG = "EmailPassword";

    private ImageButton avatar;
    private Button signup;
    private EditText email, password, birthDate;
    private TextView login;
    private AvatarFragment avatarFragment;
    private StartLogic logic;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authListener;
    private DatabaseReference database;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createuser);

        avatar = (ImageButton) findViewById(R.id.createuser_picturebtn);
        email = (EditText) findViewById(R.id.createuser_email);
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

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(logic.checkEmail(s.toString())) {
                    Drawable icon = ResourcesCompat.getDrawable(getResources(), R.drawable.checkmark,null);
                    email.setError(null, icon);
                }
                else {
                    Drawable icon = ResourcesCompat.getDrawable(getResources(), R.drawable.errormark,null);
                    email.setError(null, icon);
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

        database = FirebaseDatabase.getInstance().getReference();

    }

    public void setAvatar(int index) {
        avatar.setTag(index);
        avatar.setBackgroundResource(index);
    }

    private boolean checkUser() {
        return firebaseAuth.getCurrentUser() != null;
    }

    @Override
    public void onClick(View v) {
        if(v == avatar) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.activity_createuser, avatarFragment);
            transaction.commit();
        } else if(v == signup) {
            if (validateForm()) {
                new LoadViewTask().execute();
            } else {
                Toast.makeText(getApplicationContext(), "Krav til brugeroplysninger er ikke overholdt. Tjek efter og prøv igen", Toast.LENGTH_LONG).show();
            }
        } else if(v == login) {
            this.finish();
        }
    }

    private void createAccount() {
        firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(CreateUserActivity.this, R.string.reg_failed, Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = this.email.getText().toString();
        if (TextUtils.isEmpty(email)) {
            valid = false;
        }

        String passwordString = password.getText().toString();
        if (TextUtils.isEmpty(passwordString)) {
            valid = false;
        }

        return valid;
    }

    private void nextActivity() {
        if (firebaseAuth.getCurrentUser() != null) {
            writeNewUser(email.getText().toString(), birthDate.getText().toString(), avatarFragment.getCurrent(), 16);
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

    private class LoadViewTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(CreateUserActivity.this, "Gemmer data",
                    "Vent venligst...", false, false);
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                synchronized (this) {
                    createAccount();

                    // Checker om brugeren er null
                    while(!checkUser()) {
                        this.wait(500);
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
            nextActivity();
        }
    }
}