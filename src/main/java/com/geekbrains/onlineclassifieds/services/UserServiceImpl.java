package com.geekbrains.onlineclassifieds.services;

import com.geekbrains.onlineclassifieds.dto.RegistrationUserDto;
import com.geekbrains.onlineclassifieds.dto.RoleConstants;
import com.geekbrains.onlineclassifieds.entities.User;
import com.geekbrains.onlineclassifieds.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void createUser(RegistrationUserDto registrationUserDto) {
        User user = new User();
        user.setUsername(registrationUserDto.username());
        user.setDisplayName(registrationUserDto.displayName());
        user.setTelephone(registrationUserDto.telephone());
        user.setEmail(registrationUserDto.email());
        user.setPassword(passwordEncoder.encode(registrationUserDto.password()));
        user.setRoles(List.of(roleService.findByName(RoleConstants.ROLE_USER).orElseThrow(() -> new EntityNotFoundException(String.format("Role '%s' not found", RoleConstants.ROLE_USER)))));
        userRepository.save(user);
    }
}