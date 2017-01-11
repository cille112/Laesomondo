package com.example.cille_000.laesomondo.startscreen;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.example.cille_000.laesomondo.R;
import com.example.cille_000.laesomondo.mainscreen.MainActivity;
import com.example.cille_000.laesomondo.util.Alarm;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import io.fabric.sdk.android.Fabric;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    private Button login;
    private EditText username, password;
    private TextView createuser, forgotPassword;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private LoadViewTask loadViewTask;

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

        sharedPref = getSharedPreferences(getString(R.string.Prefrence_file_key), Context.MODE_PRIVATE);


        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);


        firebaseAuth = FirebaseAuth.getInstance();
        if (checkUser()) {
            startMain();
        }

        if(sharedPref.getString("email", "")!="" ){
            username.setText(sharedPref.getString("email", ""));
            password.setText(sharedPref.getString("password", ""));
        }


    }

    private void signIn() {
        firebaseAuth.signInWithEmailAndPassword(username.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            wrongInput();
                        }
                        else if(task.isSuccessful()){
                            editor = sharedPref.edit();
                            editor.putString("email", username.getText().toString()).commit();
                            editor.putString("password", password.getText().toString()).commit();
                            editor.commit();
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
            loadViewTask = new LoadViewTask();
            loadViewTask.execute();
        } else if(v == createuser) {
            Intent intent = new Intent(this, CreateUserActivity.class);
            startActivity(intent);
        } else if (v == forgotPassword){
            forgotYourPassword();
        }


    }

    private void wrongInput(){
        Toast.makeText(StartActivity.this, R.string.auth_failed,
                Toast.LENGTH_SHORT).show();

        AlertDialog.Builder alert = new AlertDialog.Builder(StartActivity.this);

        alert.setTitle("Forkerte login oplysninger");
        alert.setMessage("Har du glemt dit password, så skriv din mail:");

        final EditText input = new EditText(StartActivity.this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        alert.setView(input);

        alert.setPositiveButton("Send mail", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                loadViewTask.cancel(true);
                loadViewTask = null;
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
                loadViewTask.cancel(true);
                loadViewTask = null;
            }
        });

        alert.show();
    }

    private void forgotYourPassword(){
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

                    if(!isCancelled()) {
                        // Checker om brugeren er null
                        while (!checkUser()) {
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
        @Override
        protected void onCancelled(){
            progressDialog.dismiss();
        }
    }
}

