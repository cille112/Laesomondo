package Entities;

import android.media.Image;

import java.util.Date;

public class  User {

    String name;
    String DOB;
    String avatar;
    int lix;
    int speed;
    int xp;
    String levelPic;
    //Trofæ//

    public User(String name, String DOB){
        this.name = name;
        this.DOB = DOB;
        this.avatar = "av1";
    }

    public void setAvatar (String avatar){
        this.avatar = avatar;
    }

    public void setName (String name){
        this.name = name;
    }

    public void setAge (String age){
        this.DOB = age;
    }

    public String getName() {
        return name;
    }

    public String getDOB() {
        return DOB;
    }

    public String getAvatar() {
        return avatar;
    }
}
