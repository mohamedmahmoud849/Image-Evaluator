package com.example.Task.service;

import com.example.Task.model.ImageModel;
import com.example.Task.model.UsersModel;
import com.example.Task.repository.ImageRepository;
import com.example.Task.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
public class UsersService {

    private final UserRepository userRepository;


    public UsersService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }


    public UsersModel registerUser(String login ,String password){

        if(login != null && password != null){

            if(userRepository.findFirstByLogin(login).isPresent()){
                return null;

            }else{
                UsersModel usersModel = new UsersModel();
                usersModel.setLogin(login);
                usersModel.setPassword(password);
                return userRepository.save(usersModel);


            }


        }else{
            return null;
        }


    }

    public UsersModel  authenticate(String login , String password ){
        return userRepository.findByLoginAndPassword(login,password).orElse(null);
    }


}
