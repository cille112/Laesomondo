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
        String info;
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

    public String getName(){ return  listInfo.get(0);}

    public String getinfo () { return listInfo.get(2);}

    public int getLix(){ return Integer.valueOf(listInfo.get(3));}

    public int getWordCount (){return Integer.valueOf(listInfo.get(4));}

    public List<String> getQuestion1(){return listInfo.subList(5,10);}
    public List<String> getQuestion2(){return listInfo.subList(10,15);}
    public List<String> getQuestion3(){return listInfo.subList(15,20);}

    public int correctanswer1 (){return Integer.valueOf(listInfo.get(20));}
    public int correctanswer2 (){return Integer.valueOf(listInfo.get(21));}
    public int correctanswer3 (){return Integer.valueOf(listInfo.get(22));}



}
