package com.example.Task.repository;

import com.example.Task.model.ImageModel;
import com.example.Task.model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UsersModel,Integer> {

    Optional<UsersModel> findByLoginAndPassword(String login ,String password);
    Optional<UsersModel> findFirstByLogin(String login);




}
