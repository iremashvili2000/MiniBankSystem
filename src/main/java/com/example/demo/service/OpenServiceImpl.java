package com.example.demo.service;

import com.example.demo.exceptions.BadDataException;
import com.example.demo.exceptions.DontFoundException;
import com.example.demo.models.databaseModels.Balance;
import com.example.demo.models.databaseModels.User;
import com.example.demo.models.databaseModels.UserSecurity;
import com.example.demo.models.requests.OpenNewPassword;
import com.example.demo.models.requests.Registration;
import com.example.demo.repository.UserRepository;

import com.example.demo.repository.UserSecuritRepository;
import com.example.demo.util.Random;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class OpenServiceImpl implements OpenService{
    private final UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private UserSecuritRepository userSecuritRepository;
    private final SendEmailService sendEmailService;

    public OpenServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, UserSecuritRepository userSecuritRepository, SendEmailService sendEmailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userSecuritRepository = userSecuritRepository;
        this.sendEmailService = sendEmailService;
    }

    @Override
    public User registration(Registration registration) {
        if(!registration.getPassword().equals(registration.getRepassword())){
            throw new BadDataException("password must be equals");
        }
        if(userRepository.findByEmail(registration.getEmail())!=null){
            throw new BadDataException("email must be unique");
        }

        if(registration.getFullname()==null
        || registration.getUsername()==null || registration.getPassword()==null
                || registration.getEmail()==null
        ){
            throw new BadDataException("please write all line");
        }
        if(registration.getPassword().length()<6){
            throw new BadDataException("your password's length must be greater than 6");
        }
        User user=new User();

        user.setEmail(registration.getEmail());
        user.setFullname(registration.getFullname());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setRole("ADMIN");
        user.setUsername(registration.getUsername());
        user.setReferralLink(generateRandomReferral());
        if(registration.getOldrefferal()!=null){
            if(userRepository.findByReferralLink(registration.getOldrefferal())==null){

            }else {
                user.setReferrer(userRepository.findByReferralLink(registration.getOldrefferal()));
            }
        }
    System.out.println(user.getFullname());
        Balance balance=new Balance();
        balance.setBalance(0.0);
        balance.setUser(user);
        user.setBalance(balance);
       // balanceRepository.save(balance);
        userRepository.save(user);
        sendEmailService.sendEmail(registration.getEmail(),"congrats you succsesfull registration "+registration.getFullname(),"registration");
        return user;
    }

    @Override
    public void recoverPassword(User user) {
       UserSecurity userSecurity=user.getUserSecurity();
       if(userSecurity==null){
           userSecurity=new UserSecurity();

       }
       Date date=new Date();
       userSecurity.setLink_create_at(date);
       String link=linkganerate();
       System.out.println(link);
       userSecurity.setLink(link);
       userSecurity.setUser(user);
       user.setUserSecurity(userSecurity);
       String realLink="http://localhost:8080/api/v1/open/activate/"+link;
        userRepository.save(user);
        sendEmailService.sendEmail(user.getEmail()," <h1>welcome mail</h1>" +
        "<p style='color:red'>you're registered</p>"+link,"recover password");
        sendEmailService.sendHtmlEmail(user.getEmail()," <h1>welcome mail</h1>" +
                "<p style='color:red'>you're registered</p> <a href='"+realLink + "'>linkaq</a>"," recover password");
    }

    @Override
    public void updatePasword(OpenNewPassword openNewPassword, String link) {
        if(userSecuritRepository.findByLink(link)==null){
            throw new DontFoundException("not found this link");
        }
        if(!openNewPassword.getNewpassword().equals(openNewPassword.getRenewpassword())){
            throw new BadDataException("passwords must be equals");
        }
        if(openNewPassword.getNewpassword().length()<6){
            throw new BadDataException("password must be greater than 6");
        }
        if(openNewPassword.getNewpassword()==null){
            throw new BadDataException("you must need write all line");
        }
        UserSecurity userSecurity= userSecuritRepository.findByLink(link);
        User user=userSecurity.getUser();
        userSecurity.setOldpassword(user.getPassword());
        user.setPassword(passwordEncoder.encode(openNewPassword.getNewpassword()));
        userRepository.save(user);
    }


    private String generateRandomReferral(){
        String referralLink = Random.generateReferal();
        if (userRepository.findByReferralLink(referralLink)!=null){
            return generateRandomReferral();
        }
        return referralLink;
    }
    private String linkganerate(){
        String lin=Random.generatelink();
        System.out.println(lin);
        return lin;
    }

}
