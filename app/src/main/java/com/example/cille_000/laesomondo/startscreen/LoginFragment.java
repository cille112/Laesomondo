package com.example.cille_000.laesomondo.startscreen;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cille_000.laesomondo.R;

public class LoginFragment extends Fragment implements View.OnClickListener{

    private Button login;
    private EditText username, password;
    private TextView createuser;
    private Intent intent;
    private Activity context;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        context = getActivity();

        login = (Button) view.findViewById(R.id.login_btnlogin);
        username = (EditText) view.findViewById(R.id.login_username);
        password = (EditText) view.findViewById(R.id.login_password);
        createuser = (TextView) view.findViewById(R.id.login_createuser);

        login.setOnClickListener(this);
        createuser.setOnClickListener(this);

        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v == login) {
            username.setText("");
            password.setText("");
        }

        if(v == createuser) {
            intent = new Intent(context, CreateUserActivity.class);
            startActivity(intent);
        }

    }
}
