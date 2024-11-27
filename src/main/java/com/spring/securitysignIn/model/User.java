package com.spring.securitysignIn.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String username;
    private String password;

    public User(){

    }

    public User(String username,String password){
        this.username=username;
        this.password=password;
    }

    @Override
    public String toString(){
        return "User [id=" + id + ", username=" + username + ", password=" + password +"]";
    }
}
