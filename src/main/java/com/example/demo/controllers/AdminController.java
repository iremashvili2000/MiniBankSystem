package com.example.demo.controllers;

import com.example.demo.models.databaseModels.Transaction;
import com.example.demo.models.databaseModels.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.admin.AdminService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final UserRepository userRepository;
    private final AdminService adminService;


    public AdminController(UserRepository userRepository, AdminService adminService) {
        this.userRepository = userRepository;
        this.adminService = adminService;
    }

    @RequestMapping(value = "/users/list",method = RequestMethod.POST)
    public List<User> getUserList(@AuthenticationPrincipal UserDetails userDetails){
        return userRepository.findAll();
    }

    @RequestMapping(value = "/user/transaction/{username}",method = RequestMethod.POST)
    public List<Transaction> getUserTransaction(@AuthenticationPrincipal UserDetails userDetails, @PathVariable(name="username")String username){
        User user=userRepository.findByUsername(username);
        return user.getTransactions();
    }

    @RequestMapping(value = "/users/transactions/list",method = RequestMethod.POST)
    public List<Transaction> getAllTransaction(@AuthenticationPrincipal UserDetails userDetails){
       return adminService.getAllTransaction();
    }







}
