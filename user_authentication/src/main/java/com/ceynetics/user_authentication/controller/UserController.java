package com.ceynetics.user_authentication.controller;

import com.ceynetics.user_authentication.dto.LoginRequest;
import com.ceynetics.user_authentication.dto.UserRequest;
import com.ceynetics.user_authentication.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ceynetics.user_authentication.service.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> register(@RequestBody UserRequest userRequest) {
        // Register a new user
        userService.registerUser(userRequest);

        return ResponseEntity.ok("User Registered Successfully!");
    }

    @PostMapping("/login")
    public UserResponse login(@RequestBody LoginRequest loginRequest) {
        // Login a user
        return userService.loginUser(loginRequest);
    }


}


