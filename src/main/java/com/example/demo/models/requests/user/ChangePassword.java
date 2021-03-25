package com.example.demo.models.requests.user;

import com.sun.istack.NotNull;

public class ChangePassword {
    @NotNull
    private String oldPassword;
    @NotNull
    private String newPassword;
    @NotNull
    private String renewPassword;

    public ChangePassword(String oldPassword, String newPassword, String renewPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.renewPassword = renewPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getRenewPassword() {
        return renewPassword;
    }

    public void setRenewPassword(String renewPassword) {
        this.renewPassword = renewPassword;
    }
}
