package com.example.demo.service.admin;

import com.example.demo.models.databaseModels.Transaction;

import java.util.List;

public interface AdminService {
    List<Transaction> getAllTransaction();
}
