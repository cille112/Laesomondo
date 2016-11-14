package logic;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.cille_000.laesomondo.R;

public class StartLogic {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    public StartLogic(SharedPreferences pref) {
        this.pref = pref;
    }

    public void saveUserInfo(String name, String birthdate, int avatar) {
        editor = pref.edit();
        editor.putString("Name", name);
        editor.putString("Date", birthdate);
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

//    private int convert(String avatar) {
//        if (avatar == "av1")
//            return R.drawable.av1;
//
//        if (avatar == "av2")
//            return R.drawable.av2;
//
//        if (avatar == "av3")
//            return R.drawable.av3;
//
//        if (avatar == "av4")
//            return R.drawable.av4;
//
//        if (avatar == "av5")
//            return R.drawable.av5;
//
//        if (avatar == "av6")
//            return R.drawable.av6;
//
//        if (avatar == "av7")
//            return R.drawable.av7;
//
//        if (avatar == "av8")
//            return R.drawable.av8;
//
//        if (avatar == "av9")
//            return R.drawable.av9;
//        return R.drawable.av1;
//    }
}
