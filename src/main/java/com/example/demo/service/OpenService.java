package com.example.demo.service;

import com.example.demo.models.databaseModels.User;
import com.example.demo.models.requests.OpenNewPassword;
import com.example.demo.models.requests.Registration;

public interface OpenService {
    User registration(Registration registration);

    void recoverPassword(User user);

    void updatePasword(OpenNewPassword openNewPassword, String link);
}
