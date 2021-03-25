package com.example.demo.service.user;

import com.example.demo.models.databaseModels.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.user.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user= (User) userRepository.findByEmail(email);
        if (user == null){
            throw new UsernameNotFoundException("Dont Found "+email);
        }

        return (UserDetails) user;
    }


    @Override
    public boolean deleteUser(User user) {
        userRepository.deleteById(user.getId());
        return true;
    }




}
