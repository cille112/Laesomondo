package com.example.cille_000.laesomondo.logic;

import android.content.SharedPreferences;

import com.example.cille_000.laesomondo.R;

public class StartLogic {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    public StartLogic(SharedPreferences pref) {
        this.pref = pref;
    }

    public void saveUserInfo(String name, String date, int avatar) {
        editor = pref.edit();
        editor.putString("Name", name);
        editor.putString("Date", date);
        editor.putInt("Avatar", avatar);
        editor.commit();
    }

    public String getName() {
        return pref.getString("Name", "");
    }

    public String getDate() {
        return pref.getString("Date", "");
    }

    public int getAvatar() {
        return pref.getInt("Avatar", R.drawable.av1);
    }
}
