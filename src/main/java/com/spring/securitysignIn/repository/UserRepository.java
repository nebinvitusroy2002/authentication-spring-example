package com.spring.securitysignIn.repository;

import com.spring.securitysignIn.model.User;
import com.spring.securitysignIn.model.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
    User save(UserDto userDto);
}
