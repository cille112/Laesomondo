package com.example.cille_000.laesomondo.startscreen;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
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

import io.fabric.sdk.android.Fabric;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    private Button login;
    private EditText username, password;
    private TextView createuser, forgotPassword;
    private static final String TAG = "EmailPassword";
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean EMULATOR = Build.PRODUCT.contains("sdk") || Build.MODEL.contains("Emulator");
        if (!EMULATOR) {
            Fabric.with(this, new Crashlytics());
        }
        setContentView(R.layout.activity_start);

        login = (Button) findViewById(R.id.login_btnlogin);
        username = (EditText) findViewById(R.id.login_username);
        password = (EditText) findViewById(R.id.login_password);
        createuser = (TextView) findViewById(R.id.login_createuser);
        forgotPassword = (TextView) findViewById(R.id.login_forgotPassword);

        login.setOnClickListener(this);
        createuser.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);

        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null) {
            startMain();
        }
    }

    private void signIn() {
        firebaseAuth.signInWithEmailAndPassword(username.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(StartActivity.this, R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();

                            AlertDialog.Builder alert = new AlertDialog.Builder(StartActivity.this);

                            alert.setTitle("Forkerte login oplysninger");
                            alert.setMessage("Har du glemt dit password, så skriv din mail:");

                            final EditText input = new EditText(StartActivity.this);
                            alert.setView(input);

                            alert.setPositiveButton("Send mail", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    if(input.getText().toString()!=""){
                                        firebaseAuth.sendPasswordResetEmail(input.getText().toString());
                                        Toast.makeText(StartActivity.this, "Mail sendt",
                                                Toast.LENGTH_SHORT).show();
                                    } else {
                                        input.setError("Skriv din email");
                                    }
                                }
                            });

                            alert.setNegativeButton("Annuller", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                }
                            });

                            alert.show();
                        }
                   }
                });
    }

    private boolean checkUser() {
        return firebaseAuth.getCurrentUser() != null;
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

    private void startMain() {
        if (firebaseAuth.getCurrentUser() != null) {
            Intent mainscreen = new Intent(this, MainActivity.class);
            startActivity(mainscreen);
        }
    }

        @Override
    public void onClick(View v) {
        if(v == login && validateForm()) {
            new LoadViewTask().execute();
        } else if(v == createuser) {
            Intent intent = new Intent(this, CreateUserActivity.class);
            startActivity(intent);
        } else if (v == forgotPassword){
            AlertDialog.Builder alert = new AlertDialog.Builder(StartActivity.this);

            alert.setTitle("Glemt password");
            alert.setMessage("Har du glemt dit password, så skriv din mail:");

            // Set an EditText view to get user input
            final EditText input = new EditText(StartActivity.this);
            alert.setView(input);

            alert.setPositiveButton("Send mail", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    if(input.getText().toString()!="") {
                        firebaseAuth.sendPasswordResetEmail(input.getText().toString());
                        Toast.makeText(StartActivity.this, "Mail sendt", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        input.setError("Skriv din email");
                    }
                }
            });

            alert.setNegativeButton("Annuller", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                }
            });

            alert.show();
        }
    }

    // Asynctask, loader mens brugeren indlæses
    private class LoadViewTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(StartActivity.this, "Henter data",
                    "Vent venligst...", false, false);
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                synchronized (this) {
                    signIn();

                    // Checker om brugeren er null
                    while(!checkUser()) {
                        if(!isCancelled()){
                            this.wait(500);
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
            startMain();
        }
    }
}

