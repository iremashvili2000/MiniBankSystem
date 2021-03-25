package com.example.demo.service.deposit;

import com.example.demo.exceptions.BadDataException;
import com.example.demo.models.databaseModels.*;
import com.example.demo.models.databaseModels.enums.Transaction_type;
import com.example.demo.models.requests.user.CreateDeposit;
import com.example.demo.models.requests.user.CreateInvest;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.deposit.DepositService;
import com.example.demo.service.user.UserServiceFunc;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DepositServiceImpl implements DepositService {
    private UserRepository userRepository;
    private UserServiceFunc userServiceFunc;

    public DepositServiceImpl(UserRepository userRepository, UserServiceFunc userServiceFunc) {
        this.userRepository = userRepository;
        this.userServiceFunc = userServiceFunc;
    }

    @Override
    public Transaction createDeposit(User user, CreateDeposit createDeposit) {
        //create Transaction
        Transaction transaction=new Transaction();
        transaction.setStatus(1);
        transaction.setCreated_at(new Date());
        transaction.setAmount(createDeposit.getAmount());
        transaction.setType(Transaction_type.deposit);
        transaction.setUser(user);
        List<Transaction> transactionList=user.getTransactions();
        transactionList.add(transaction);
        user.setTransactions(transactionList);
        userRepository.save(user);
                if(user.getTransactions().get(user.getTransactions().size()-1).getStatus()==1){
                    return user.getTransactions().get(user.getTransactions().size()-1);
                }
                throw new BadDataException("try leter my friend");

        //
    }

    @Override
    public List<Transaction> getTransactions(User user) {
      return  user.getTransactions();
    }

    @Override
    public List<Investment> getInvestList(User user) {
        return user.getInvestments();
    }

    @Override
    public Balance createInvest(User user, CreateInvest createInvest) {
        Balance balance= userServiceFunc.showBalance(user);
        if(balance.getBalance()>createInvest.getInvest()){
            //create transaction
            Transaction transaction=new Transaction();
            transaction.setAmount(createInvest.getInvest());
            transaction.setStatus(1);
            transaction.setCreated_at(new Date());
            transaction.setUser(user);
            transaction.setType(Transaction_type.investment);
            List<Transaction> trs = user.getTransactions();
            trs.add(transaction);

            user.setTransactions(trs);

            //create investment
            Investment investment=new Investment();
            investment.setAmount(createInvest.getInvest());
            investment.setCreate_at(new Date());
            investment.setStatus(user.getTransactions().get(user.getTransactions().size()-1).getStatus());
            investment.setUser(user);

            Profit profit=new Profit();
            profit.setBalance(0.0);
            investment.setProfit(profit);
            List<Investment>investmentList=user.getInvestments();
            profit.setInvestment(investment);
            investmentList.add(investment);
            user.setInvestments(investmentList);

            userRepository.save(user);
            //checking
            if(user.getTransactions().get(user.getTransactions().size()-1).getStatus()==1){
                return userServiceFunc.showBalance(user);
            }
            throw new BadDataException("you cant this transaction");

        }
        throw new BadDataException("you have not nought money");
    }

    @Override
    public Double sumInvest(User user) {
       List<Investment>investmentList= user.getInvestments();
       double sum=0;
       for(int i=0;i<investmentList.size();i++){
           sum+=investmentList.get(i).getAmount();
       }
       return sum;
    }
}
