package com.spring.securitysignIn.service;

import com.spring.securitysignIn.model.User;
import com.spring.securitysignIn.model.UserDto;

public interface UserService {
    User findByUserName(String username);
    User save(UserDto userDto);
}
