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

    public boolean chechPassword(String password) {
        if(password.length() < 3 || password.length() > 20)
            return false;

        return true;
    }
}
