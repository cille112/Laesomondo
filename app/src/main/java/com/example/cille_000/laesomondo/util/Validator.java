package com.example.cille_000.laesomondo.util;


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
        return (password.length() < 3 || password.length() > 20);
    }

    public boolean checkDate(String date) {
        if(date.length() == 10) {
            int day = Integer.valueOf(date.substring(0, 2));
            int month = Integer.valueOf(date.substring(3, 5));
            int year = Integer.valueOf(date.substring(6, 10));

            if(day < 31 && month < 12 && year > 1900 && year < 2010)
                return true;
        }

        return false;
    }
}
