package com.example.demo.controllers;

import com.example.demo.exceptions.BadDataException;
import com.example.demo.exceptions.DontFoundException;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.models.databaseModels.User;
import com.example.demo.models.databaseModels.UserSecurity;
import com.example.demo.models.requests.Login;
import com.example.demo.models.requests.OpenNewPassword;
import com.example.demo.models.requests.RecoverPassword;
import com.example.demo.models.requests.Registration;
import com.example.demo.models.response.Token;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.OpenService;
import com.example.demo.service.user.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class OpenController {
private final OpenService openService;
private final UserRepository userRepository;
private final AuthenticationManager authenticationManager;
private final JwtTokenProvider jwtTokenProvider;
private final UserService userService;

    public OpenController(OpenService openService, UserService userService, UserRepository userRepository, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.openService = openService;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }



    @RequestMapping(value = "/api/v1/open/registration",method = RequestMethod.POST)
    public User registration(@Valid @RequestBody Registration registration){
        User user=openService.registration(registration);
        return user;
    }



    @RequestMapping(value="/api/v1/open/login",method = RequestMethod.POST)
    public Token login(@Valid @RequestBody Login login){
        User user= (User) userService.loadUserByUsername(login.getEmail());
        if(user==null){
            throw new DontFoundException("email is not correct");
        }
        List<String> Roles = new ArrayList<>();
        Roles.add(user.getRole().toUpperCase());
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(),login.getPassword(),getGrantedAuthorities(Roles));

        authenticationManager.authenticate(authentication);
        String token = jwtTokenProvider.createToken(user);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Token token1=new Token(token);
        userRepository.save(user);
        return token1;
    }

    @RequestMapping(value = "/api/v1/open/recover/password",method = RequestMethod.POST)
    public String recoverPassword(@Valid @RequestBody RecoverPassword recoverPassword){
        System.out.println(recoverPassword.getEmail());
        User user=(User)userRepository.findByEmail(recoverPassword.getEmail());
        if(user==null){
            throw new BadDataException("dont find this email");
        }
        openService.recoverPassword(user);
        return "OK";
    }

    @RequestMapping(value = "/api/v1/open/activate/{link}",method = RequestMethod.POST)
    public String changOpenPas(@Valid @RequestBody OpenNewPassword openNewPassword, @PathVariable(name="link")String link){
        openService.updatePasword(openNewPassword,link);
        return "completed";
    }


    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }



}
