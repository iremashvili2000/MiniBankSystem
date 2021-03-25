package com.example.demo.repository;

import com.example.demo.models.databaseModels.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<Balance,Long> {


}
