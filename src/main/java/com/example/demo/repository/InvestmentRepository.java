package com.example.demo.repository;

import com.example.demo.models.databaseModels.Investment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestmentRepository extends JpaRepository<Investment,Long> {


}
