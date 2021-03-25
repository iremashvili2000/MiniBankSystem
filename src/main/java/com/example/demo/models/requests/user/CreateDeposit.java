package com.example.demo.models.requests.user;

import com.sun.istack.NotNull;

public class CreateDeposit {

    @NotNull
    private Double amount;

    @NotNull
    private Boolean enter;

    public CreateDeposit(Double amount, Boolean enter) {
        this.amount = amount;
        this.enter = enter;
    }

    public Boolean getEnter() {
        return enter;
    }

    public void setEnter(Boolean enter) {
        this.enter = enter;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
