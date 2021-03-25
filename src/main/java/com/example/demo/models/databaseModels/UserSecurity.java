package com.example.demo.models.databaseModels;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "UserSecurity")
public class UserSecurity {
    @Column(name="pin_created_at")
    private Date pin_create_at;
    @Column(name="pin")
    private Long pin;
    @Column(name="linc_created_at")
    private Date link_create_at;
    @Column(name = "link")
    private String link;
    @Column(name="oldpassword")
    @JsonIgnore
    private String oldpassword;
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JsonIgnore
    protected User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public UserSecurity() {
    }


    public Date getPin_create_at() {
        return pin_create_at;
    }

    public void setPin_create_at(Date pin_create_at) {
        this.pin_create_at = pin_create_at;
    }

    public Date getLink_create_at() {
        return link_create_at;
    }

    public void setLink_create_at(Date link_create_at) {
        this.link_create_at = link_create_at;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Long getPin() {
        return pin;
    }

    public void setPin(Long pin) {
        this.pin = pin;
    }

    public String getOldpassword() {
        return oldpassword;
    }

    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
