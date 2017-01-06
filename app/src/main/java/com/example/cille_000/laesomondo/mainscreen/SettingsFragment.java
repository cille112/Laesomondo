package com.example.cille_000.laesomondo.mainscreen;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cille_000.laesomondo.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class SettingsFragment extends Fragment implements View.OnClickListener, SeekBar.OnSeekBarChangeListener{

    private SeekBar textSize;
    private TextView currentText;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference database;
    private String userId;
    private float currentSize;
    private Button save;

    public SettingsFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        textSize = (SeekBar) view.findViewById(R.id.textsizebar);
        currentText = (TextView) view.findViewById(R.id.currenttextsize);
        textSize.setOnSeekBarChangeListener(this);
        save = (Button) view.findViewById(R.id.applySettings);

        save.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();

        userId = firebaseAuth.getCurrentUser().getUid();

        database = FirebaseDatabase.getInstance().getReference();

        database.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot snap) {
                if (snap.child("users").child(firebaseAuth.getCurrentUser().getUid()).hasChild("textSize")) {
                    currentSize = Float.parseFloat(snap.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("textSize").getValue().toString());
                    currentText.setTextSize(currentSize);
                    currentText.setText("" + currentSize);
                    textSize.refreshDrawableState();
                    textSize.setProgress((int) currentSize*100/36);
                } else {
                }


            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });



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
        if(v==save){
            if(isAdded())
            Toast.makeText(getActivity(), "Ændringer er gemt", Toast.LENGTH_SHORT).show();
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

        currentText.setTextSize(size);
        currentText.setText("" + size);
        database.child("users").child(userId).child("textSize").setValue(size);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
