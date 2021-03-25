package com.example.demo.service.admin;

import com.example.demo.models.databaseModels.Transaction;
import com.example.demo.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class AdminServiceImpl implements AdminService {
    private final TransactionRepository transactionRepository;

    public AdminServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> getAllTransaction() {
       return  transactionRepository.findAll();
    }




}
