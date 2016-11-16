package com.example.cille_000.laesomondo.startscreen;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import com.example.cille_000.laesomondo.R;

import com.example.cille_000.laesomondo.logic.StartLogic;

public class CreateUserFragment extends Fragment {

    private ImageButton avatar;
    private EditText t1, t2;
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
        t1 = (EditText) view.findViewById(R.id.createuser_name);
        t2 = (EditText) view.findViewById(R.id.createuser_age);

        avatar.setBackgroundResource(logic.getAvatar());
        t1.setText(logic.getName());
        t2.setText(logic.getDate());

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
        return t1.getText().toString();
    }

    public String getDate() {
        return t2.getText().toString();
    }

    public int getAvatar() {
        return Integer.valueOf(avatar.getTag().toString());
    }
}

