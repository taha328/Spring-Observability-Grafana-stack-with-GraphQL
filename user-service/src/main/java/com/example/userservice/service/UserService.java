package com.example.userservice.service;

import com.example.userservice.entities.UserEntity;
import com.example.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity addUser(UserEntity user) {
        return userRepository.save(user); // Return the saved user entity
    }
    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
