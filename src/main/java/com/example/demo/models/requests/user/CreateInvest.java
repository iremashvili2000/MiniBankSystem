package com.example.demo.models.requests.user;

import com.sun.istack.NotNull;

public class CreateInvest {
    @NotNull
    private Double invest;
    @NotNull
    private Boolean enter;

    public CreateInvest(Double invest, Boolean enter) {
        this.invest = invest;
        this.enter = enter;
    }

    public Boolean getEnter() {
        return enter;
    }

    public void setEnter(Boolean enter) {
        this.enter = enter;
    }

    public Double getInvest() {
        return invest;
    }

    public void setInvest(Double invest) {
        this.invest = invest;
    }
}
