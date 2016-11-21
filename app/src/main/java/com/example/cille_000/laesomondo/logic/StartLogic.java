package com.example.cille_000.laesomondo.logic;

import com.example.cille_000.laesomondo.util.Validator;

import java.util.Date;

public class StartLogic {

    private Validator validate;

    public StartLogic() {
        validate = new Validator();
    }

    public boolean checkUsername(String username) {
        // Tjek om brugernavnet er optaget
        return validate.checkUsername(username);
    }

    public boolean checkPassword(String password) {
        return validate.checkPassword(password);
    }

    public boolean checkAge(String s) {
        return validate.checkDate(s);
    }
}
