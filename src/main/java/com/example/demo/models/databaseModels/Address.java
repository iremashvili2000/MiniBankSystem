package com.example.demo.models.databaseModels;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name="user_address")
public class Address {
    @NotNull
    @Column(name = "city")
    private String city;
    @NotNull
    @Column(name="telephone")
    private Long telephone;
    @NotNull
    @Column(name="country")
    private String country;
    @NotNull
    @Column(name="street")
    private String street;


    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    protected User user;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Address() {
    }

    public Address(String city, Long telephone, String country, String street) {
        this.city = city;
        this.telephone = telephone;
        this.country = country;
        this.street = street;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getTelephone() {
        return telephone;
    }

    public void setTelephone(Long telephone) {
        this.telephone = telephone;
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
