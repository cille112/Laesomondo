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
        return (password.length() > 5 && password.length() < 20);
    }

    public boolean checkEmail(String email) {
        return (email != null && email.contains("@") && email.contains("."));
    }

    public boolean checkDate(String date) {
        return (date.length() == 8);
    }
}
