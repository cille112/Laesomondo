package com.example.cille_000.laesomondo.startscreen;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import com.example.cille_000.laesomondo.R;

import com.example.cille_000.laesomondo.logic.StartLogic;

public class CreateUserFragment extends Fragment {

    private ImageButton avatar;
    private EditText username, password, age;
    private AvatarFragment avatarFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_createuser, container, false);
        Context context = getActivity();

        SharedPreferences sharedPref = context.getSharedPreferences("User", Context.MODE_PRIVATE);
        StartLogic logic = new StartLogic(sharedPref);
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

        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        username.setText(logic.getName());
        age.setText(logic.getDate());
        avatar.setBackgroundResource(logic.getAvatar());

        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.activity_start, avatarFragment );
                transaction.commit();
            }
        });

        setAvatar(avatarFragment.getCurrent());

        return view;
    }

    public void setAvatar(int index) {
        avatar.setTag(index);
        avatar.setBackgroundResource(index);
    }

    public String getName() {
        return username.getText().toString();
    }

    public String getDate() {
        return age.getText().toString();
    }

    public int getAvatar() {
        return Integer.valueOf(avatar.getTag().toString());
    }
}

