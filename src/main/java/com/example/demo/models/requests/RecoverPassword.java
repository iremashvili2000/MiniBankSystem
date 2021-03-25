package com.example.demo.models.requests;

import com.sun.istack.NotNull;

import javax.validation.constraints.Email;


public class RecoverPassword {
    @Email
   private String email;

    public RecoverPassword() {
    }

    public RecoverPassword(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
