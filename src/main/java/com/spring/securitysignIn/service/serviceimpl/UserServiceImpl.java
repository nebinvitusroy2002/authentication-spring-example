package com.spring.securitysignIn.service.serviceimpl;

import com.spring.securitysignIn.model.User;
import com.spring.securitysignIn.model.UserDto;
import com.spring.securitysignIn.repository.UserRepository;
import com.spring.securitysignIn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
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
        return userRepository.findByUsername(username);
    }

    @Override
    public User save(UserDto userDto){
        User user = new User(userDto.getUsername(),passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(user);
    }
}
