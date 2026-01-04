package com.example.learningSpring.service;


import com.example.learningSpring.DTO.userregister;
import com.example.learningSpring.Repository.customUserRepo;
import com.example.learningSpring.entity.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userRegistration {
    @Autowired
    private customUserRepo cc;
    @Autowired
    private  PasswordEncoder passwordEncoder;


    public void makeRegistration(userregister request){
        user uu=new user();
        uu.setUsername(request.getUsername());
        uu.setUserpass(passwordEncoder.encode(request.getPassword()));
        if(request.getRoles()==null || request.getRoles().isEmpty()){
            uu.setRoles(List.of("ROLE_USER"));
        }
        else{
            uu.setRoles(request.getRoles());
        }
        cc.save(uu);

    }
}
