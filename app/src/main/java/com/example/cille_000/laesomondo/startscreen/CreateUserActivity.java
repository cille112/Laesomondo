package com.example.cille_000.laesomondo.startscreen;

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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cille_000.laesomondo.R;
import com.example.cille_000.laesomondo.challengescreen.TextInfoActivity;
import com.example.cille_000.laesomondo.logic.StartLogic;
import com.example.cille_000.laesomondo.mainscreen.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class CreateUserActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "EmailPassword";

    private ImageButton avatar;
    private Button signup;
    private EditText username, password, age;
    private TextView login;
    private AvatarFragment avatarFragment;
    private StartLogic logic;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    // [START declare_auth_listener]
    private FirebaseAuth.AuthStateListener mAuthListener;
    // [END declare_auth_listener]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createuser);

        avatar = (ImageButton) findViewById(R.id.createuser_picturebtn);
        username = (EditText) findViewById(R.id.createuser_name);
        password = (EditText) findViewById(R.id.createuser_password);
        age = (EditText) findViewById(R.id.createuser_age);
        signup = (Button) findViewById(R.id.createuser_signup);
        login = (TextView) findViewById(R.id.createuser_login);

        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        avatarFragment = new AvatarFragment();
        logic = new StartLogic();

        avatarFragment.setOnDoneListener(new AvatarFragment.OnDoneListener() {
            @Override
            public void onDone() {
                setAvatar(avatarFragment.getCurrent());
            }
        });

        avatar.setOnClickListener(this);
        signup.setOnClickListener(this);
        login.setOnClickListener(this);

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


        age.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(logic.checkAge(s.toString())) {
                    Drawable icon = ResourcesCompat.getDrawable(getResources(), R.drawable.checkmark,null);
                    age.setError(null, icon);
                }
                else {
                    Drawable icon = ResourcesCompat.getDrawable(getResources(), R.drawable.errormark,null);
                    age.setError(null, icon);
                }
            }
        });

        setAvatar(avatarFragment.getCurrent());

        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

        if (mAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        // [START auth_state_listener]
        mAuthListener = new FirebaseAuth.AuthStateListener() {
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
                // [START_EXCLUDE]
                updateUI(user);
                // [END_EXCLUDE]
            }
        };
        // [END auth_state_listener]
    }

    // [START on_start_add_listener]
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    // [END on_start_add_listener]

    // [START on_stop_remove_listener]
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    // [END on_stop_remove_listener]


    public void setAvatar(int index) {
        avatar.setTag(index);
        avatar.setBackgroundResource(index);
    }

    @Override
    public void onClick(View v) {
        if(v == avatar) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.activity_createuser, avatarFragment );
            transaction.commit();
        }
        else if(v == signup) {
            createAccount(username.getText().toString(), password.getText().toString());
        }
        else if(v == login) {
            this.finish();
        }
    }

    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }


        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
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
                            System.out.println(task.getException().getMessage());
                        }

                    }
                });
        // [END create_user_with_email]
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = username.getText().toString();
        if (TextUtils.isEmpty(email)) {
            //username.setError("Required.");
            valid = false;
        } else {
            // username.setError(null);
        }

        String passwordString = password.getText().toString();
        if (TextUtils.isEmpty(passwordString)) {
            //password.setError("Required.");
            valid = false;
        } else {
           // password.setError(null);
        }

        return valid;
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            finish();
            Intent mainscreen = new Intent(this, ChallengeInfoActivity.class);
            startActivity(mainscreen);
        } else {

        }
    }



}