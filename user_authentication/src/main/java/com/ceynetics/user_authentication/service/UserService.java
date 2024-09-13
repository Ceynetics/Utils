package com.ceynetics.user_authentication.service;

import com.ceynetics.user_authentication.dto.LoginRequest;
import com.ceynetics.user_authentication.dto.UserRequest;
import com.ceynetics.user_authentication.dto.UserResponse;
import com.ceynetics.user_authentication.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.ceynetics.user_authentication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ceynetics.user_authentication.repository.UserRepository;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public void registerUser(UserRequest userRequest) {
        // Register a new user
        User user = User.builder()
                .username(userRequest.getUsername())
                .password(userRequest.getPassword())
                .email(userRequest.getEmail())
                .build();

        // Save the user to the database
        userRepository.save(user);
        log.info("User:{} registered successfully", user.getUsername());
    }

    public UserResponse loginUser(LoginRequest loginRequest) {
        // Login a user
        User user = userRepository.findByEmail(loginRequest.getEmail());
        if (user == null) {
            log.error("User:{} not found", loginRequest.getEmail());
            return null;
        }

        if (!user.getPassword().equals(loginRequest.getPassword())) {
            log.error("Invalid credentials for user:{}", user.getUsername());
            return null;
        }

        String token = jwtUtil.generateToken(user.getUsername());

        log.info("User:{} logged in successfully", user.getUsername());
        return UserResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .token(token)
                .build();
    }
}
