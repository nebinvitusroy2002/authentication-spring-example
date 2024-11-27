package com.spring.securitysignIn.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {

    private String username;
    private String password;

    public UserDto(String username,String password){
        this.username=username;
        this.password=password;
    }

    @Override
    public String toString(){
        return "UserDto [username=" + username + ", password=" + password +"]";
    }
}
