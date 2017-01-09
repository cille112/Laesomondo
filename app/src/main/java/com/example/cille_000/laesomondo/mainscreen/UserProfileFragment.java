package com.example.cille_000.laesomondo.mainscreen;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cille_000.laesomondo.R;
import com.example.cille_000.laesomondo.startscreen.StartActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class UserProfileFragment extends Fragment implements View.OnClickListener {

    private TextView lixValue, wordMinValue, XPValue;
    private ImageButton achievement, stats;
    private ImageView profilePicture;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference database;
    private String userId;
    private Drawable avatar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_userprofile, container, false);

        XPValue = (TextView) view.findViewById(R.id.userprofile_XPValue);
        wordMinValue = (TextView) view.findViewById(R.id.userprofile_wordminvalue);
        lixValue = (TextView) view.findViewById(R.id.userprofile_lixvalue);
        achievement = (ImageButton) view.findViewById(R.id.userprofile_achievement);
        stats = (ImageButton) view.findViewById(R.id.userprofile_stats);

        achievement.setOnClickListener(this);
        stats.setOnClickListener(this);


        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();

        if(firebaseAuth.getCurrentUser() == null){
            Intent intent1 = new Intent(getActivity(), StartActivity.class);
            startActivity(intent1);
        }
        else {
            userId = firebaseAuth.getCurrentUser().getUid();
        }

        profilePicture = (ImageView) view.findViewById(R.id.avatar);
        database.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot snap) {

                    //ProfilePicture
                if (snap.child("users").child(userId).hasChild("avatar")) {
                    if(isAdded()){
                        if(getActivity() !=null) {
                            avatar = getActivity().getDrawable(Integer.parseInt(snap.child("users").child(userId).child("avatar").getValue().toString()));
                            System.out.println(avatar.toString());
                            profilePicture.setImageDrawable(avatar);
                        }
                    }
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Der skete en fejl i indlæsning af profil", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(getActivity(), StartActivity.class);
                    startActivity(intent1);
                }
                    //LIX
                if (!snap.child("users").child(userId).child("lix").exists()){
                    lixValue.setText("0 Lix");
                }else{
                    lixValue.setText(snap.child("users").child(userId).child("lix").getValue().toString()+ " Lix");
                }
                    //XP
                if(!snap.child("users").child(userId).child("xp").exists()){
                    XPValue.setText("0 XP");
                }else{
                    XPValue.setText(snap.child("users").child(userId).child("xp").getValue().toString()+ " XP");
                }

                if(!snap.child("users").child(userId).child("speed").exists()){
                    wordMinValue.setText("0 Ord/Min");
                }else{
                    wordMinValue.setText(snap.child("users").child(userId).child("speed").getValue().toString()+ " Ord/Min");
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
