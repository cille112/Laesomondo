package com.example.cille_000.laesomondo.startscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cille_000.laesomondo.R;
import com.example.cille_000.laesomondo.mainscreen.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "StartActivity";
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;
    private Button login;
    private EditText username, password;
    private TextView createuser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        login = (Button) findViewById(R.id.login_btnlogin);
        username = (EditText) findViewById(R.id.login_username);
        password = (EditText) findViewById(R.id.login_password);
        createuser = (TextView) findViewById(R.id.login_createuser);

        login.setOnClickListener(this);
        createuser.setOnClickListener(this);

        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        auth = FirebaseAuth.getInstance();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user != null) {
                    Log.d(TAG, "signed in: " + user.getUid());
                }
                else {
                    Log.d(TAG, "signed out");
                }
            }
        };
    }

    @Override
    public void onClick(View v) {
        if(v == login) {
            signIn(username.getText().toString().trim(), password.getText().toString().trim());
        }

        if(v == createuser) {
            Intent intent = new Intent(this, CreateUserActivity.class);
            startActivity(intent);
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if(authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }

    private void signIn(String email, String password) {
        // Tjek gyldig info er sendt

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d(TAG, "sign in with email complete: " + task.isSuccessful());

                if(!task.isSuccessful()) {
                    Log.w(TAG, "sign in with email failed", task.getException());

                }
            }
        });

        if(auth.getCurrentUser() != null) {
            toast(getResources().getString(R.string.LoginFragmentToastSuccess) + " " + auth.getCurrentUser());
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else {
            toast(getResources().getString(R.string.LoginFragmentToastNoSuccess));
        }
    }

    private void toast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}

