package com.example.demo.controllers;

import com.example.demo.exceptions.BadDataException;
import com.example.demo.exceptions.DontFoundException;
import com.example.demo.models.databaseModels.*;
import com.example.demo.models.requests.user.*;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.deposit.DepositService;
import com.example.demo.service.user.UserService;
import com.example.demo.service.user.UserServiceFunc;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/private/")
public class PrivateController {
    private final UserRepository userRepository;
    private final UserService userService;
    private final UserServiceFunc userServiceFunc;
    private final DepositService depositService;

    public PrivateController(UserRepository userRepository, UserService userService, UserServiceFunc userServiceFunc, DepositService depositService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.userServiceFunc = userServiceFunc;
        this.depositService = depositService;
    }

    @RequestMapping(value = "user/me",method = RequestMethod.POST)
    public User getUser(@AuthenticationPrincipal UserDetails userDetails){
        User user=(User)userRepository.findByEmail(userDetails.getUsername());
        if(user==null){
            throw new DontFoundException("User dont found");
        }
        return user;
    }

    @RequestMapping(value = "/user/delete",method = RequestMethod.DELETE)
    public boolean deleteUser(@AuthenticationPrincipal UserDetails userDetails){
        User user=(User)userRepository.findByEmail(userDetails.getUsername());
        boolean delete=userService.deleteUser(user);
        return delete;
    }

    @RequestMapping(value = "/update/profile",method = RequestMethod.POST)
    public User getUpdateUser(@AuthenticationPrincipal UserDetails userDetails, @Valid @RequestBody UpdateUser updateUser){
        User user=(User)userRepository.findByEmail(userDetails.getUsername());
        return userServiceFunc.updateUser(user,updateUser);
    }

    @RequestMapping(value = "/user/changePassword",method = RequestMethod.POST)
    public User changePassword(@AuthenticationPrincipal UserDetails userDetails, @Valid @RequestBody ChangePassword changePassword){
       User user=(User)userRepository.findByEmail(userDetails.getUsername());

      return  userServiceFunc.changePassword(user,changePassword);
    }

    @RequestMapping(value = "/user/show/balance",method = RequestMethod.POST)
    public Balance showBalance(@AuthenticationPrincipal UserDetails userDetails){
        User user=(User)userRepository.findByEmail(userDetails.getUsername());
        if(user.getTransactions()==null){
            return user.getBalance();
        }else{
            return userServiceFunc.showBalance(user);
        }
    }

    @RequestMapping(value = "/user/createInvest",method = RequestMethod.POST)
    public Balance ShowInvest(@AuthenticationPrincipal UserDetails userDetails, @Valid @RequestBody CreateInvest createInvest){
        System.out.println(createInvest.getEnter());
        if(!createInvest.getEnter()==true){
            throw new BadDataException("okey");

        }
            User user=(User)userRepository.findByEmail(userDetails.getUsername());
            return depositService.createInvest(user,createInvest);

    }

    @RequestMapping(value = "/user/create/Deposit",method = RequestMethod.POST)
    public Transaction createDeposit(@AuthenticationPrincipal UserDetails userDetails, @Valid @RequestBody CreateDeposit createDeposit){
            User user=(User)userRepository.findByEmail(userDetails.getUsername());
            Transaction balance=depositService.createDeposit(user,createDeposit);
            return balance;
    }

    @RequestMapping(value = "/user/Transaction/list",method = RequestMethod.POST)
    public List<Transaction> getTransactions(@AuthenticationPrincipal UserDetails userDetails){
       User user=(User)userRepository.findByEmail(userDetails.getUsername());
        return depositService.getTransactions(user);
    }

    @RequestMapping(value = "/user/Investment/list",method = RequestMethod.POST)
    public List<Investment> getInvestList(@AuthenticationPrincipal UserDetails userDetails){
        User user=(User)userRepository.findByEmail(userDetails.getUsername());
        return depositService.getInvestList(user);
    }

    @RequestMapping(value = "/user/Investment/sum",method = RequestMethod.POST)
    public Double getSumInvests(@AuthenticationPrincipal UserDetails userDetails){
        User user=(User)userRepository.findByEmail(userDetails.getUsername());
        return  depositService.sumInvest(user);
    }

    @RequestMapping(value = "/user/address/update",method = RequestMethod.POST)
    public Address updateAddress(@AuthenticationPrincipal UserDetails userDetails, @Valid @RequestBody UpdateAddress updateAdress){
        User user=(User)userRepository.findByEmail(userDetails.getUsername());
       Address address=userServiceFunc.updateAddress(user,updateAdress);

       return address;
    }











}
