package com.example.cille_000.laesomondo.mainscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cille_000.laesomondo.R;
import com.example.cille_000.laesomondo.startscreen.StartActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class UserProfileFragment extends Fragment implements View.OnClickListener {

    private TextView lixvalue, wordMinValue;
    private ImageButton achievement, stats;
    private ImageView profilePicture;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_userprofile, container, false);

        wordMinValue = (TextView) view.findViewById(R.id.userprofile_wordminvalue);
        lixvalue = (TextView) view.findViewById(R.id.userprofile_lixvalue);
        achievement = (ImageButton) view.findViewById(R.id.userprofile_achievement);
        stats = (ImageButton) view.findViewById(R.id.userprofile_stats);

        achievement.setOnClickListener(this);
        stats.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance().getReference();

        profilePicture = (ImageView) view.findViewById(R.id.avatar);
        database.addListenerForSingleValueEvent(new ValueEventListener() {
            String userId = firebaseAuth.getCurrentUser().getUid();

            @Override
            public void onDataChange(DataSnapshot snap) {

                if(snap.child("users").child(firebaseAuth.getCurrentUser().getUid()) != null) {
                    profilePicture.setImageDrawable(getResources().getDrawable(Integer.parseInt(snap.child("users").child(userId).child("avatar").getValue().toString())));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        return view;
    }


    @Override
    public void onClick(View v) {
        if(v == achievement) {
            ((MainActivity) this.getActivity()).displayView(5);
        }

        if(v == stats) {
            ((MainActivity) this.getActivity()).displayView(6);
        }
    }
}
