package com.example.cille_000.laesomondo.mainscreen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Observable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cille_000.laesomondo.R;
import com.example.cille_000.laesomondo.challengescreen.TextInfoActivity;
import com.example.cille_000.laesomondo.startscreen.ChallengeInfoActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import static com.example.cille_000.laesomondo.R.id.recyclerView;

public class bookListVerticalAdapter extends RecyclerView.Adapter<bookListVerticalAdapter.bookViewHolder> {
    private Activity context;
    private List<Integer[]> bookList;
    private int i;
    private String category;
    private Boolean testTaken;
    private FirebaseAuth firebaseAuth;
    private String userId;


    public bookListVerticalAdapter(Activity context, List<Integer[]> bookList, int i, String category)  {
        this.context = context;
        this.bookList = bookList;
        this.i = i;
        this.category = category;
    }


    @Override
    public bookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listbooks, parent, false);
        //bookViewHolder holder = new bookViewHolder(v);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            userId = firebaseAuth.getCurrentUser().getUid();
        }

        checkTest();

        return new bookViewHolder(v);
    }


    @Override
    public void onBindViewHolder(bookViewHolder holder, int position) {
        //bookImages = (list.get(position));
        holder.imageView.setImageResource(bookList.get(i)[position]);
        //holder.imageView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return bookList.get(i).length;
    }


    public class bookViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public bookViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageViewBooks);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (testTaken != null) {
                        if (testTaken == true) {
                            final Intent intent;

                            //int pos = getAdapterPosition();
                            intent = new Intent(context, TextInfoActivity.class);
                            intent.putExtra("textID", getPosition() + 1);
                            intent.putExtra("category", category);
                            context.startActivity(intent);

                        }
                        else {
                            Toast.makeText(context, "Du skal tage læsetesten først", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, ChallengeInfoActivity.class);
                            context.startActivity(intent);
                        }
                    }
                    else{
                        Toast.makeText(context, "Indlæser, prøv igen om lidt", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void checkTest(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(userId!=null) {
                    if (dataSnapshot.child("users").child(userId).child("textRead").exists()) {
                        testTaken = true;
                    } else {
                        testTaken = false;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
