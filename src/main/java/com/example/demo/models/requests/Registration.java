package com.example.demo.models.requests;

import com.sun.istack.NotNull;

import javax.validation.constraints.Email;

public class Registration {

    @NotNull
    private String fullname;
    @NotNull
    private String username;
    @NotNull
    @Email
    private String email;
    @NotNull
    private String password;
    @NotNull
    private boolean emailNotify;
    @NotNull
    private String repassword;
    private String refferal;
    private String oldrefferal;


    public Registration(String fullname,String oldrefferal,String username,Boolean emailNotify, String email, String password, String repassword) {
        this.fullname=fullname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.repassword = repassword;
        this.oldrefferal=oldrefferal;
        this.emailNotify=emailNotify;
    }

    public boolean isEmailNotify() {
        return emailNotify;
    }

    public void setEmailNotify(boolean emailNotify) {
        this.emailNotify = emailNotify;
    }

    public String getOldrefferal() {
        return oldrefferal;
    }

    public void setOldrefferal(String oldrefferal) {
        this.oldrefferal = oldrefferal;
    }

    public String getRefferal() {
        return refferal;
    }

    public void setRefferal(String refferal) {
        this.refferal = refferal;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }
}
