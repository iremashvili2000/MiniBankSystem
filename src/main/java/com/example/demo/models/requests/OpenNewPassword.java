package com.example.demo.models.requests;

import com.sun.istack.NotNull;

public class OpenNewPassword {
    @NotNull
    private String newpassword;
    @NotNull
    private String renewpassword;

    public OpenNewPassword() {
    }

    public OpenNewPassword(String newpassword, String renewpassword) {
        this.newpassword = newpassword;
        this.renewpassword = renewpassword;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public String getRenewpassword() {
        return renewpassword;
    }

    public void setRenewpassword(String renewpassword) {
        this.renewpassword = renewpassword;
    }
}
