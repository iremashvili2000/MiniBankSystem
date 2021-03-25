package com.example.demo.repository;

import com.example.demo.models.databaseModels.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {

}
