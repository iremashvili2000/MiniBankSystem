package com.example.demo.models.databaseModels;

import com.example.demo.models.databaseModels.enums.Transaction_type;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "transaction")
public class Transaction {
    @Column(name="amount")
    private Double amount;
    @Column(name="status")
    private Integer status;
    @Column(name = "created_at")
    private Date created_at;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "deposit")
    @Enumerated(EnumType.STRING)
    private Transaction_type type;


    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    @JsonIgnore
    private User user;

    public Transaction_type getType() {
        return type;
    }

    public void setType(Transaction_type type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
