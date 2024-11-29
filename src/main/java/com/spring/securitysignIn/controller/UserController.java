package com.spring.securitysignIn.controller;

import com.spring.securitysignIn.model.User;
import com.spring.securitysignIn.model.UserDto;
import com.spring.securitysignIn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

    @Autowired
    private UserDetailsService userDetailsService;
    private final UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }

    @GetMapping("/home")
    public UserDetails home(Principal principal){
        return userDetailsService.loadUserByUsername(principal.getName());
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserDto userDto) {
        User user = userService.save(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
