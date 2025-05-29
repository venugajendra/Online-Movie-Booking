package com.mycode.user_service.service;

import com.mycode.user_service.model.User;
import com.mycode.user_service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Inject PasswordEncoder

    public User createUser(User user) {
        log.info("Inside the createUser method of UserService.");
        // Hash the password before saving
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }



    public Optional<User> getUserByEmail(String email) {
        log.info("Inside the getUserByEmail method of UserService.");
        return userRepository.findByEmail(email);
    }


    public User getUserById(Long id) {
        log.info("Inside the getUserById method of UserService.");
        return userRepository.findById(id).orElse(null);
    }

    public User updateUser(Long id, User user) {
        log.info("Inside the updateUser method of UserService.");
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            user.setId(id); // Ensure the ID is set
            return userRepository.save(user);
        }
        return null; // Or throw an exception
    }




}
