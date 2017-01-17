package com.example.cille_000.laesomondo.mainscreen;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.cille_000.laesomondo.R;
import com.example.cille_000.laesomondo.startscreen.StartActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//  Dette fragment viser alle bøgerne, hylderne osv. Det er 'startskærmen'
public class BookFragment extends Fragment {

    private String[] genres;
    private View view;

    public BookFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_book, container, false);


        String genre = getArguments().getString("genre");
        genres = genre.split("\\s+");

        ListView listView = (ListView) view.findViewById(R.id.ListView01);

        BookListAdapter adapter = new BookListAdapter(getActivity(), genres);
        listView.setAdapter(adapter);
        return view;
    }

}