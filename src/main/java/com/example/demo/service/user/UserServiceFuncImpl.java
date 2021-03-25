package com.example.demo.service.user;

import com.example.demo.exceptions.BadDataException;
import com.example.demo.exceptions.ComputerException;
import com.example.demo.models.databaseModels.Address;
import com.example.demo.models.databaseModels.Balance;
import com.example.demo.models.databaseModels.Transaction;
import com.example.demo.models.databaseModels.User;
import com.example.demo.models.databaseModels.enums.Transaction_type;
import com.example.demo.models.requests.user.ChangePassword;
import com.example.demo.models.requests.user.UpdateAddress;
import com.example.demo.models.requests.user.UpdateUser;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceFuncImpl implements UserServiceFunc {
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private  final  AddressRepository addressRepository;

    public UserServiceFuncImpl(BCryptPasswordEncoder passwordEncoder, UserRepository userRepository, AddressRepository addressRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }



    @Override
    public User updateUser(User user, UpdateUser updateUser) {
        if(!passwordEncoder.matches(updateUser.getPassword(),user.getPassword())){
            throw new BadDataException("password is not correct");
        }
        if(userRepository.findByEmail(updateUser.getNewEmail())!=null){
            throw new BadDataException("email must be unique");
        }
        if(updateUser.getFullname()==null || updateUser.getNewUsername()==null || updateUser.getNewEmail()==null || updateUser.getPassword()==null){
            throw new BadDataException("please fill all the field");
        }
        if(updateUser.getPassword().length()<6){
            throw new BadDataException("password is small");
        }
        user.setUsername(updateUser.getNewUsername());
        user.setEmail(updateUser.getNewEmail());
       user.setFullname(updateUser.getFullname());
        userRepository.save(user);
        return user;
    }

    @Override
    public User changePassword(User user, ChangePassword changePassword) {
        if(!passwordEncoder.matches(changePassword.getOldPassword(), user.getPassword())){
            throw new BadDataException("password is not correct");
        }
        if(!changePassword.getNewPassword().equals(changePassword.getRenewPassword())){
            throw new BadDataException("new paswords is not equals");
        }
        if(changePassword.getNewPassword().length()<6){
            throw new BadDataException("password is small");
        }
        user.setPassword(passwordEncoder.encode(changePassword.getNewPassword()));
        userRepository.save(user);
        return user;
    }

    @Override
    public Balance showBalance(User user) {
        List<Transaction> transactionList=user.getTransactions();
        double money=0;
        for(int i=0;i<transactionList.size();i++){
            if(transactionList.get(i).getStatus()==1 && (transactionList.get(i).getType()== Transaction_type.deposit || transactionList.get(i).getType()== Transaction_type.profit)){
                money+=transactionList.get(i).getAmount();
            }

            if(transactionList.get(i).getStatus()==1 && (transactionList.get(i).getType()==Transaction_type.investment || transactionList.get(i).getType()==Transaction_type.withdrow)){
                money-=transactionList.get(i).getAmount();
            }
        }
        if(money<0){
            throw new ComputerException("money is less than 0 USD");
        }
       Balance balance= user.getBalance();
        balance.setBalance(money);
        user.setBalance(balance);
        userRepository.save(user);
        return balance;
    }

    @Override
    public Address updateAddress(User user, UpdateAddress updateAdress) {
       Address address= addressRepository.findByUser(user)
               .orElse( new Address());

        address.setTelephone(updateAdress.getPhone());
        address.setCity(updateAdress.getCity());
        address.setCountry(updateAdress.getCountry());
        address.setStreet(updateAdress.getStreet());
        address.setUser(user);
        addressRepository.save(address);
        return address;
    }


}
