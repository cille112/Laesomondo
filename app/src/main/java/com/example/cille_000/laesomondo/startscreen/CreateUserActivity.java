package com.example.cille_000.laesomondo.startscreen;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cille_000.laesomondo.R;
import com.example.cille_000.laesomondo.logic.StartLogic;


public class CreateUserActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton avatar;
    private Button signup;
    private EditText username, password, age;
    private TextView login;
    private AvatarFragment avatarFragment;
    private StartLogic logic;

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
    }

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
            // Kald til login createuser()
        }
        else if(v == login) {
            this.finish();
        }
    }
}