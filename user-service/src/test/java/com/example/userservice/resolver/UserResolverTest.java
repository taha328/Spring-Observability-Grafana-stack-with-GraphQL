package com.example.userservice.resolver;

import com.example.userservice.entities.UserEntity;
import com.example.userservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserResolverTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserResolver userResolver;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        // Create UserEntity instances with id, username
        UserEntity user1 = new UserEntity(1L, "testUser1");
        UserEntity user2 = new UserEntity(2L, "testUser2");

        when(userService.getAllUsers()).thenReturn(Arrays.asList(user1, user2));

        List<UserEntity> users = userResolver.getAllUsers();
        assertEquals(2, users.size());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void testGetUserById() {
        // Create a UserEntity instance with id
        UserEntity user = new UserEntity(1L, "testUser");
        when(userService.getUserById(1L)).thenReturn(user);

        UserEntity foundUser = userResolver.getUserById(1L);
        assertNotNull(foundUser);
        assertEquals("testUser", foundUser.getUsername());
        verify(userService, times(1)).getUserById(1L);
    }

    @Test
    void testAddUser() {
        // Create a UserEntity instance with id
        UserEntity user = new UserEntity(1L, "newUser");
        when(userService.addUser(any(UserEntity.class))).thenReturn(user);

        UserEntity createdUser = userResolver.addUser("newUser");
        assertNotNull(createdUser);
        assertEquals("newUser", createdUser.getUsername());
        verify(userService, times(1)).addUser(any(UserEntity.class));
    }
}
