package com.example.demo.repository;

import com.example.demo.models.databaseModels.UserSecurity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSecuritRepository extends JpaRepository<UserSecurity,Long> {
    UserSecurity findByLink(String link);
}
