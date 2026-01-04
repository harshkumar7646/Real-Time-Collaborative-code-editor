package com.example.learningSpring.service;

import com.example.learningSpring.Repository.customUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class customUserDetailService implements UserDetailsService {
    @Autowired
    private customUserRepo uu;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return uu.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("user nhi mill rha"+username));
    }
}
