package com.example.cille_000.laesomondo.startscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cille_000.laesomondo.R;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    private Button login;
    private EditText username, password;
    private TextView createuser;
    private Intent intent;

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
    }

    @Override
    public void onClick(View v) {
        if(v == login) {
            username.setText("");
            password.setText("");
        }

        if(v == createuser) {
            intent = new Intent(this, CreateUserActivity.class);
            startActivity(intent);
        }

    }
}

