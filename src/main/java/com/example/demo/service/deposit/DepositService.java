package com.example.demo.service.deposit;

import com.example.demo.models.databaseModels.Balance;
import com.example.demo.models.databaseModels.Investment;
import com.example.demo.models.databaseModels.Transaction;
import com.example.demo.models.databaseModels.User;
import com.example.demo.models.requests.user.CreateDeposit;
import com.example.demo.models.requests.user.CreateInvest;

import java.util.List;

public interface DepositService {
    Transaction createDeposit(User user, CreateDeposit createDeposit);

    List<Transaction> getTransactions(User user);

    List<Investment> getInvestList(User user);

    Balance createInvest(User user, CreateInvest createInvest);

    Double sumInvest(User user);
}
