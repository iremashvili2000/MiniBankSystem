package com.example.demo.repository;

import com.example.demo.models.databaseModels.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {


    Object findByEmail(String email);


    User findByReferralLink(String referralLink);

    User findByUsername(String username);
}
