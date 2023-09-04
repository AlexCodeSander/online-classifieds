package com.geekbrains.onlineclassifieds.services;

import com.geekbrains.onlineclassifieds.dto.RegistrationUserDto;
import com.geekbrains.onlineclassifieds.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);
    Optional<User> findUserByEmail(String email);
    void createUser(RegistrationUserDto registrationUserDto);
}
