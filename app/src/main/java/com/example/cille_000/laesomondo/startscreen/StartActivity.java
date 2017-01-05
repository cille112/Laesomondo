package com.example.cille_000.laesomondo.startscreen;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.example.cille_000.laesomondo.R;
import com.example.cille_000.laesomondo.mainscreen.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.fabric.sdk.android.Fabric;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    private Button login;
    private EditText username, password;
    private TextView createuser;

    private static final String TAG = "EmailPassword";

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean EMULATOR = Build.PRODUCT.contains("sdk")|| Build.MODEL.contains("Emulator");
        if(!EMULATOR){
        Fabric.with(this, new Crashlytics());}
        setContentView(R.layout.activity_start);

        login = (Button) findViewById(R.id.login_btnlogin);
        username = (EditText) findViewById(R.id.login_username);
        password = (EditText) findViewById(R.id.login_password);
        createuser = (TextView) findViewById(R.id.login_createuser);

        login.setOnClickListener(this);
        createuser.setOnClickListener(this);

        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        firebaseAuth = FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
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
        firebaseAuth.addAuthStateListener(authStateListener);
    }
    // [END on_start_add_listener]

    // [START on_stop_remove_listener]
    @Override
    public void onStop() {
        super.onStop();
        if (authStateListener != null) {
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }
    // [END on_stop_remove_listener]



    private void signIn(String email, String passwordString) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }


        firebaseAuth.signInWithEmailAndPassword(email, passwordString)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(StartActivity.this, R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();

                            AlertDialog.Builder alert = new AlertDialog.Builder(StartActivity.this);

                            alert.setTitle("Forkerte login oplysninger");
                            alert.setMessage("Har du glemt dit password, så skriv den mail:");

                            // Set an EditText view to get user input
                            final EditText input = new EditText(StartActivity.this);
                            alert.setView(input);

                            alert.setPositiveButton("Send mail", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    if(input.getText().toString()!=""){
                                        firebaseAuth.sendPasswordResetEmail(input.getText().toString());
                                        Toast.makeText(StartActivity.this, "Mail sendt",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        input.setError("Skriv din email");
                                    }
                                }
                            });

                            alert.setNegativeButton("Annuller", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    // Canceled.
                                }
                            });

                            alert.show();
                        }
                   }
                });
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = username.getText().toString();
        if (TextUtils.isEmpty(email)) {
            username.setError("Required.");
            valid = false;
        } else {
            username.setError(null);
        }

        String passwordString = password.getText().toString();
        if (TextUtils.isEmpty(passwordString)) {
            password.setError("Required.");
            valid = false;
        } else {
            password.setError(null);
        }

        return valid;
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Intent mainscreen = new Intent(this, MainActivity.class);
            startActivity(mainscreen);
        } else {

        }
    }

        @Override
    public void onClick(View v) {
        if(v == login) {
            signIn(username.getText().toString(), password.getText().toString());
        }
        else if(v == createuser) {
            Intent intent = new Intent(this, CreateUserActivity.class);
            startActivity(intent);
        }
    }
}

