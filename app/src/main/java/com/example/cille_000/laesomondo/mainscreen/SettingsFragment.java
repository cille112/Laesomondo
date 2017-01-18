package com.example.cille_000.laesomondo.mainscreen;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cille_000.laesomondo.R;
import com.example.cille_000.laesomondo.startscreen.AvatarFragment;
import com.example.cille_000.laesomondo.util.Alarm;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class SettingsFragment extends Fragment implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, CompoundButton.OnCheckedChangeListener {

    private SeekBar seekBarTextSize;
    private TextView currentTextSize;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference database;
    private String userId;
    private float currentSize;
    private Button save;
    private ImageButton profilePicture;
    private AvatarFragment avatarFragment;
    private int currentPic;
    private CheckBox notification;
    private Boolean noti;
    private Alarm alarm;

    public SettingsFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        seekBarTextSize = (SeekBar) view.findViewById(R.id.settings_textsizebar);
        currentTextSize = (TextView) view.findViewById(R.id.settings_currenttextsize);
        seekBarTextSize.setOnSeekBarChangeListener(this);
        save = (Button) view.findViewById(R.id.settings_save);
        profilePicture = (ImageButton) view.findViewById(R.id.settings_picturebtn);
        notification = (CheckBox) view.findViewById(R.id.noti);

        notification.setOnCheckedChangeListener(this);

        alarm = new Alarm();

        save.setOnClickListener(this);
        profilePicture.setOnClickListener(this);

        avatarFragment = new AvatarFragment();
        avatarFragment.setOnDoneListener(new AvatarFragment.OnDoneListener() {
            @Override
            public void onDone() {
                setAvatar(avatarFragment.getCurrent());
            }
        });


        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null) {
            userId = firebaseAuth.getCurrentUser().getUid();
        }
        database = FirebaseDatabase.getInstance().getReference();
        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snap) {
                if (snap.child("users").child(userId).hasChild("textSize")) {
                    currentSize = Float.parseFloat(snap.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("textSize").getValue().toString());
                    currentTextSize.setTextSize(currentSize);
                    currentTextSize.setText("" + currentSize);
                    seekBarTextSize.refreshDrawableState();

                    seekBarTextSize.setProgress((int) (currentSize - 16) * 100/20);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snap) {
                if (snap.child("users").child(userId).hasChild("avatar")) {
                    int drawable = Integer.parseInt(snap.child("users").child(userId).child("avatar").getValue().toString());
                    profilePicture.setBackgroundResource(drawable);
                    currentPic = drawable;
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snap) {
                if (snap.child("users").child(userId).hasChild("Notification")) {
                    notification.setChecked(Boolean.parseBoolean(snap.child("users").child(userId).child("Notification").getValue().toString()));
                }
                else {
                    notification.setChecked(true);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        profilePicture.setBackgroundResource(currentPic);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        if(v == profilePicture) {
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_settings, avatarFragment);
            transaction.commit();
        }
        if(v == save){
            if(currentPic != 0 && currentSize != 0 && isAdded()) {
                database.child("users").child(userId).child("textSize").setValue(currentTextSize.getTextSize()/3);
                database.child("users").child(userId).child("avatar").setValue(currentPic);
                if(noti != null) {
                    if (noti) {
                        alarm.setAlarm(getContext());
                    } else {
                        alarm.cancelAlarm(getContext());
                    }
                    database.child("users").child(userId).child("Notification").setValue(noti);
                }
                Toast.makeText(getActivity(), "Ændringerne blev gemt", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        float size = 16;

        if(progress == 0) {
            size = 16;
        } else {
            size += progress / 5;
        }

        currentTextSize.setTextSize(size);
        currentTextSize.setText("" + size);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public void setAvatar(int index) {
        currentPic = index;
        profilePicture.setBackgroundResource(index);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        noti = isChecked;
    }
}
