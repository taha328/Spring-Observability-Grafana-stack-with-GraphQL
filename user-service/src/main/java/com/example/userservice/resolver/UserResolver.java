package com.example.userservice.resolver;

import com.example.userservice.entities.UserEntity;
import com.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserResolver {

    private final UserService userService;

    @Autowired
    public UserResolver(UserService userService) {
        this.userService = userService;
    }


    @QueryMapping
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }
    @QueryMapping
    public UserEntity getUserById(@Argument long id) {
        return userService.getUserById(id);
    }

    // Mutation to add a new user
    @MutationMapping
    public UserEntity addUser(@Argument String username) {
        UserEntity user = new UserEntity(username);
        return userService.addUser(user);
    }
}
