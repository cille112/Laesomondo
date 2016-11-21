package com.example.cille_000.laesomondo.entities;


import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadingTest {

    private String text = "";
    private List<String> listInfo;

    public ReadingTest(int id, Context context){
        String info = "";
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
        try {
            InputStream is = context.getAssets().open("text" + id + "Info.txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            info = new String(buffer);
            listInfo = new ArrayList<>(Arrays.asList(info.split(" / ")));

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public String getText() {
        return text;
    }

    public String getWriter(){ return listInfo.get(1);}

}
