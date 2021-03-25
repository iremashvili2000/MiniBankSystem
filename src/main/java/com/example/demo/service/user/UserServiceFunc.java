package com.example.demo.service.user;

import com.example.demo.models.databaseModels.Address;
import com.example.demo.models.databaseModels.Balance;
import com.example.demo.models.databaseModels.User;
import com.example.demo.models.requests.user.ChangePassword;
import com.example.demo.models.requests.user.UpdateAddress;

import com.example.demo.models.requests.user.UpdateUser;

public interface UserServiceFunc {
    User updateUser(User user, UpdateUser updateUser);

    User changePassword(User user, ChangePassword changePassword);


    Balance showBalance(User user);

    Address updateAddress(User user, UpdateAddress updateAdress);
}
