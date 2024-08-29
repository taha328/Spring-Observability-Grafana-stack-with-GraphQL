package com.example.userservice.service;

import com.example.userservice.entities.UserEntity;
import com.example.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public UserEntity addUser(UserEntity user) {

        return userRepository.save(user);
    }
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
}
