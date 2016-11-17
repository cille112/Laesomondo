package com.example.cille_000.laesomondo.startscreen;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.cille_000.laesomondo.R;

public class LoginFragment extends Fragment implements View.OnClickListener{

    private Button login;
    private TextView username, password, createuser;
    private Intent intent;
    private Activity context;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        context = getActivity();

        login = (Button) view.findViewById(R.id.login_btnlogin);
        username = (TextView) view.findViewById(R.id.login_username);
        password = (TextView) view.findViewById(R.id.login_password);
        createuser = (TextView) view.findViewById(R.id.login_createuser);

        login.setOnClickListener(this);
        createuser.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v == login) {
            // Så prøver man at logge ind
        }

        if(v == createuser) {
            intent = new Intent(context, CreateUserActivity.class);
            startActivity(intent);
        }

    }
}
