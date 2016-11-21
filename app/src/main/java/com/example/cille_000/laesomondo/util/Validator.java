package com.example.cille_000.laesomondo.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Validator {
    private String[] illegalChars = new String[] {
            "!", "@", "#", "£", "$", "¤", "%", "&", "/", "(", ")", "=", "?",
            "+", "-", ",", ".", "-", "_", "§", "[", "]", "{", "}", "^", "~",
            "*", "'", "\"", "\\", "<", ">", "|"
    };

    public boolean checkUsername(String username) {
        if(username.length() < 3 || username.length() > 16)
            return false;

        for (String x: illegalChars) {
            if(username.contains(x))
                return false;
        }

        return true;
    }

    public boolean checkPassword(String password) {
        if(password.length() < 3 || password.length() > 20)
            return false;

        return true;
    }

    public boolean checkDate(String date) {
        if(date.length() == 10) {
            try {
                DateFormat format = new SimpleDateFormat("dd-mm-yyyy");
                Date result = format.parse(date);
                System.out.println("DATO: " + result);

                return true;
            }
            catch (Exception e) {
                return false;
            }
        }

        return false;
    }
}
