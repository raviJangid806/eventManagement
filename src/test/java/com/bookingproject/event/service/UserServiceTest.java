package com.bookingproject.event.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.bookingproject.event.entity.User;
import com.bookingproject.event.repository.UserRepository;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;

import java.util.Optional;


public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private User testUser;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        testUser = new User("John Doe", "john.doe@example.com", "securePassword123");
        testUser.setId(1);
    }

    @Test
    public void createUser_ShouldSaveUser_WhenUserIsValid() {
        // Arrange
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        // Act
        User createdUser = userService.createUser(testUser);

        // Assert
        assertNotNull(createdUser);
        assertEquals("John Doe", createdUser.getName());
        assertEquals("john.doe@example.com", createdUser.getEmail());
        verify(userRepository, times(1)).save(testUser);
    }

    @Test
    public void getUserById_ShouldReturnUser_WhenUserExists() {
        // Arrange
        when(userRepository.findById(1)).thenReturn(Optional.of(testUser));

        // Act
        User foundUser = userService.getUserById(1);

        // Assert
        assertNotNull(foundUser);
        assertEquals(testUser.getName(), foundUser.getName());
        assertEquals(testUser.getEmail(), foundUser.getEmail());
    }

    @Test
    public void getUserById_ShouldThrowException_WhenUserDoesNotExist() {
        // Arrange
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> userService.getUserById(1));
    }

    @Test
    public void updateUser_ShouldUpdateUser_WhenUserExists() {
        // Arrange
        when(userRepository.findById(1)).thenReturn(Optional.of(testUser));
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        User updatedUser = new User("Jane Doe", "jane.doe@example.com", "newSecurePassword");

        // Act
        User result = userService.updateUser(1, updatedUser);

        // Assert
        assertNotNull(result);
        assertEquals("Jane Doe", result.getName());
        assertEquals("jane.doe@example.com", result.getEmail());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void updateUser_ShouldThrowException_WhenUserDoesNotExist() {
        // Arrange
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        User updatedUser = new User("Jane Doe", "jane.doe@example.com", "newSecurePassword");
        assertThrows(RuntimeException.class, () -> userService.updateUser(1, updatedUser));
    }

//    @Test
//    public void deleteUser_ShouldDeleteUser_WhenUserExists() {
//        // Arrange
//        when(userRepository.findById(1)).thenReturn(Optional.of(testUser));
//
//        // Act
//        userService.deleteUser(1);
//
//        // Assert
//        verify(userRepository, times(1)).delete(testUser);
//    }

//    @Test
//    public void deleteUser_ShouldThrowException_WhenUserDoesNotExist() {
//        // Arrange
//        when(userRepository.findById(1)).thenReturn(Optional.empty());
//
//        // Act & Assert
//        assertThrows(RuntimeException.class, () -> userService.deleteUser(1));
//    }
}
