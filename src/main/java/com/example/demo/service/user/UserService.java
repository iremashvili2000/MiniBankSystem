package com.example.demo.service.user;

import com.example.demo.models.databaseModels.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    boolean deleteUser(User user);


}
