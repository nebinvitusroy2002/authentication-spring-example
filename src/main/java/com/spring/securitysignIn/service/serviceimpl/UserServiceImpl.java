package com.spring.securitysignIn.service.serviceimpl;

import com.spring.securitysignIn.model.User;
import com.spring.securitysignIn.model.UserDto;
import com.spring.securitysignIn.repository.UserRepository;
import com.spring.securitysignIn.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public User findByUserName(String username){
        log.info("Searching for user with username: {}",username);
        try {
            User user = userRepository.findByUsername(username);
            if (user == null) {
                log.warn("User not found with username: {}", username);
                throw new IllegalArgumentException("User not found: {}" + username);
            }
            log.info("Login Successful: {}", username);
            return user;
        }catch (Exception e){
            log.error("An error occurred while retrieving user: {}",username,e);
            throw new RuntimeException("Database error: unable to fetch user information",e);
        }
    }

    @Override
    public void save(UserDto userDto){
        log.info("Register user: {}",userDto.getUsername());
        if(userRepository.findByUsername(userDto.getUsername()) != null) {
            log.warn("User already exists: {}",userDto.getUsername());
            throw new IllegalArgumentException("User already exists with username: " + userDto.getUsername());
        }
        User user = new User(userDto.getUsername(),passwordEncoder.encode(userDto.getPassword()));
        User savedUser = userRepository.save(user);

        log.info("User saved successfully: {}",savedUser);
    }
}
