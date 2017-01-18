package com.example.cille_000.laesomondo.mainscreen;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.cille_000.laesomondo.R;

//  Dette fragment viser alle bøgerne, hylderne osv. Det er 'startskærmen'
public class BookFragment extends Fragment {


    public BookFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book, container, false);


        String genre = getArguments().getString("genre");
        String textRead = getArguments().getString("textRead");
        String[] genres = genre.split("\\s+");
        //String[] textsRead = textRead.split("\\s+");

        ListView listView = (ListView) view.findViewById(R.id.ListView01);

        BookListAdapter adapter = new BookListAdapter(getActivity(), genres, textRead);
        listView.setAdapter(adapter);
        return view;
    }

}