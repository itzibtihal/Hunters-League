package org.youcode.hunterleague.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.youcode.hunterleague.domain.User;
import org.youcode.hunterleague.exception.user.DuplicateUserException;
import org.youcode.hunterleague.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegisterServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private RegisterServiceImpl registerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUser_ShouldSaveUser_WhenUsernameAndEmailAreUnique() {
        // Arrange
        User newUser = new User();
        newUser.setUsername("uniqueUsername");
        newUser.setEmail("uniqueEmail@example.com");

        when(userRepository.findByUsernameOrEmail(newUser.getUsername(), newUser.getEmail()))
                .thenReturn(Optional.empty());
        when(userRepository.save(newUser)).thenReturn(newUser);

        // Act
        User savedUser = registerService.registerUser(newUser);

        // Assert
        assertNotNull(savedUser);
        assertEquals("uniqueUsername", savedUser.getUsername());
        assertEquals("uniqueEmail@example.com", savedUser.getEmail());
        verify(userRepository, times(1)).save(newUser);
    }

    @Test
    void registerUser_ShouldThrowDuplicateUserException_WhenUsernameOrEmailExists() {
        // Arrange
        User existingUser = new User();
        existingUser.setUsername("existingUsername");
        existingUser.setEmail("existingEmail@example.com");

        when(userRepository.findByUsernameOrEmail(existingUser.getUsername(), existingUser.getEmail()))
                .thenReturn(Optional.of(existingUser));

        // Act & Assert
        DuplicateUserException exception = assertThrows(DuplicateUserException.class, () -> {
            registerService.registerUser(existingUser);
        });

        assertEquals("Username or email already exists.", exception.getMessage());
        verify(userRepository, never()).save(existingUser);
    }
}
