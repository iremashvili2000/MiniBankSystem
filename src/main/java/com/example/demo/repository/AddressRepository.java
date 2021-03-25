package com.example.demo.repository;

import com.example.demo.models.databaseModels.Address;
import com.example.demo.models.databaseModels.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address,Long> {


    Optional<Address> findByUser(User user);
}
