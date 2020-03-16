package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public boolean login(String email, String password){
        User user = userRepository.login(email, password);

        if (user != null){
            if(user.getEmail() != null)
            return true;
            else return false;
        }
        else return false;
    }
}
