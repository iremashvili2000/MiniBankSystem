package com.example.demo.controllers;

import com.example.demo.models.databaseModels.*;
import com.example.demo.models.databaseModels.enums.Transaction_type;
import com.example.demo.repository.BalanceRepository;
import com.example.demo.repository.InvestmentRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.*;

@Configuration
@EnableScheduling
public class SpringConfig {
    private final UserRepository userRepository;
    private final BalanceRepository balanceRepository;
    private final InvestmentRepository investmentRepository;

    public SpringConfig(UserRepository userRepository, BalanceRepository balanceRepository, InvestmentRepository investmentRepository) {
        this.userRepository = userRepository;
        this.balanceRepository = balanceRepository;
        this.investmentRepository = investmentRepository;
    }
//drois shecvla ar dagvaviwydes LAZY da EAGER gamo davayene ese aseve after magivrad before unda 1 ciklshi

    @Scheduled(fixedDelay = 1000*60*5,initialDelay = 2000L)
    public void giveMoney(){
        List<Investment>investmentList=investmentRepository.findAll();
        List<Investment>realInvestments=new ArrayList<Investment>();
        Date date=new Date();
        Date newDate=subtractDays(date,10);
        for(int i=0;i<investmentList.size();i++){
            if(investmentList.get(i).getCreate_at().after(newDate)){
                realInvestments.add(investmentList.get(i));
            }
        }
        for(int i=0;i<realInvestments.size();i++){
          Profit profit=realInvestments.get(i).getProfit();
          Investment investment=realInvestments.get(i);
            Transaction transaction=new Transaction();
            transaction.setCreated_at(new Date());
            transaction.setUser(investment.getUser());
            transaction.setAmount((investment.getAmount()/100)*8);
            transaction.setStatus(1);
            transaction.setType(Transaction_type.profit);
            profit.setBalance((investment.getAmount()/100)*8);
            investment.setProfit(profit);
            User user=investment.getUser();
            List<Transaction>transactionList=user.getTransactions();
            transactionList.add(transaction);
            user.setTransactions(transactionList);
            userRepository.save(user);
          investmentRepository.save(investment);
        }

    }


    public static Date subtractDays(Date date,int days){
        GregorianCalendar cal=new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE,-days);
        return cal.getTime();
    }




}
