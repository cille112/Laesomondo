package com.example.cille_000.laesomondo.entities;


import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;

public class ReadingTest {

    private int id;
    private String text = "";
    private Context context;

    public String getText() {
        return text;
    }

    public ReadingTest(int id, Context context){
        this.id = id;
        this.context = context;

        try {
            InputStream is = context.getAssets().open("text" + id + ".txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            text = new String(buffer);
        }catch (IOException e){
            e.printStackTrace();
        }



    }





}
