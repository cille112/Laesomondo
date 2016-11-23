package com.example.cille_000.laesomondo.logic;

import com.example.cille_000.laesomondo.util.Validator;


public class StartLogic {

    private Validator validate;
    private String username;
    private String password;
    private String email;
    private String date;

    public StartLogic() {
        validate = new Validator();
    }

    public boolean checkUsername(String s) {
        // Tjek om brugernavnet er optaget
        if(validate.checkUsername(s)) {
            username = s;

            return true;
        }

        return false;
    }

    public boolean checkPassword(String s) {
        if(validate.checkPassword(s)) {
            password = s;

            return true;
        }

        return false;
    }

    public boolean checkEmail(String s) {
        if(validate.checkEmail(s)) {
            email = s;

            return true;
        }

        return false;
    }

    public boolean checkAge(String s) {
        if(validate.checkDate(s)) {
            date = s;

            return true;
        }

        return false;
    }

    public boolean validateInfo() {
        return (username != null && password != null && email != null && date != null);
    }
}
