package com.example.cille_000.laesomondo.util;


public class Validator {
    private String[] illegalChars = new String[] {
            "!", "@", "#", "£", "$", "¤", "%", "&", "/", "(", ")", "=", "?",
            "+", "-", ",", ".", "-", "_", "§", "[", "]", "{", "}", "^", "~",
            "*", "'", "\"", "\\", "<", ">", "|"
    };

    public boolean checkUsername(String username) {
        for (String x: illegalChars) {
            if(username.contains(x))
                return false;
        }

        return (username.length() > 3 && username.length() < 16);
    }

    public boolean checkPassword(String password) {
        return (password.length() > 3 && password.length() < 20);
    }

    public boolean checkEmail(String email) {
        return (email != null && email.contains("@"));
    }

    public boolean checkDate(String date) {
        if(date.length() == 8) {
            try{
                int day = Integer.valueOf(date.substring(0, 2));
                int month = Integer.valueOf(date.substring(2, 4));
                int year = Integer.valueOf(date.substring(4, 8));

                if(day < 32 && month < 13 && year > 1900 && year < 2010)
                    return true;

            }catch (Exception e) {
                return false;
            }
        }

        return false;
    }
}
