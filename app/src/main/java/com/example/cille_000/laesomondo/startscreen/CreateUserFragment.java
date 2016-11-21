package com.example.cille_000.laesomondo.startscreen;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.cille_000.laesomondo.R;

public class CreateUserFragment extends Fragment implements View.OnClickListener{

    private ImageButton avatar;
    private EditText username, password, age;
    private TextView login;
    private AvatarFragment avatarFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_createuser, container, false);

        avatarFragment = new AvatarFragment();

        avatarFragment.setOnDoneListener(new AvatarFragment.OnDoneListener() {
            @Override
            public void onDone() {
                setAvatar(avatarFragment.getCurrent());
            }
        });

        avatar = (ImageButton) view.findViewById(R.id.createuser_picturebtn);
        username = (EditText) view.findViewById(R.id.createuser_name);
        password = (EditText) view.findViewById(R.id.createuser_password);
        age = (EditText) view.findViewById(R.id.createuser_age);
        login = (TextView) view.findViewById(R.id.createuser_login);

        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        avatar.setOnClickListener(this);
        login.setOnClickListener(this);

        setAvatar(avatarFragment.getCurrent());

        return view;
    }

    public void setAvatar(int index) {
        avatar.setTag(index);
        avatar.setBackgroundResource(index);
    }

    @Override
    public void onClick(View v) {
        if(v == avatar) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.activity_start, avatarFragment );
            transaction.commit();
        }

        if(v == login) {
            ((CreateUserActivity)getActivity()).close();
        }
    }
}

