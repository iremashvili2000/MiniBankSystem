package com.example.demo.models.requests.user;

import com.sun.istack.NotNull;

public class UpdateUser {
    @NotNull
    private String fullname;
    @NotNull
    private String newUsername;
    @NotNull
    private String password;
    @NotNull
    private String newEmail;

    public UpdateUser(String newUsername, String password, String newEmail) {

        this.newUsername = newUsername;
        this.password = password;
        this.newEmail = newEmail;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getNewUsername() {
        return newUsername;
    }

    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }
}
