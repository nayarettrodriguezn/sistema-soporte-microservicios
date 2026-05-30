package com.support.userservice.service;

import com.support.userservice.dto.UserDTO;
import com.support.userservice.entity.User;
import com.support.userservice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    public User saveUser(UserDTO userDTO) {
        log.info("Starting database persistence flow for user: {}", userDTO.getName());
        try {
            User user = new User();
            user.setName(userDTO.getName());
            user.setEmail(userDTO.getEmail());
            user.setPassword("password123"); 
            user.setRole("ROLE_USER");
            
            User savedUser = userRepository.save(user);
            log.info("User successfully registered with generated ID: {}", savedUser.getId());
            return savedUser;
        } catch (Exception e) {
            log.error("Critical error while saving user: {}", e.getMessage());
            throw new RuntimeException("No se pudo completar el registro del usuario en la base de datos.");
        }
    }

    public List<User> getAllUsers() {
        log.info("Fetching all active users from the registry.");
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        log.info("Searching user with ID: {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("User lookup failed. ID {} not found.", id);
                    return new RuntimeException("El usuario consultado no existe.");
                });
    }
}