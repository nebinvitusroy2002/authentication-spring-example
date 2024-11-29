package com.spring.securitysignIn.service.serviceimpl;

import com.spring.securitysignIn.model.CustomUserDetails;
import com.spring.securitysignIn.model.User;
import com.spring.securitysignIn.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        log.info("Attempting to load user by username: {}",username);
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("Username or password Not found");
        }
        log.info("User loaded successfully: {}",user.getUsername());
        return new CustomUserDetails(user.getUsername(),user.getPassword(),authorities());
    }
    public Collection<? extends GrantedAuthority> authorities(){
        return List.of(new SimpleGrantedAuthority("USER"));
    }
}
