package com.example.demo.models.requests.user;

import com.sun.istack.NotNull;

public class UpdateAddress {
    @NotNull
    private String city;

    @NotNull
    private String country;
    @NotNull

    private String street;

    @NotNull
    private Long phone;

    public UpdateAddress(String city, String country, String street, Long phone) {
        this.city = city;
        this.country = country;
        this.street = street;
        this.phone = phone;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
