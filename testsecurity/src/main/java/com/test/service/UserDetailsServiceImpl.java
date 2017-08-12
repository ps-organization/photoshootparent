package com.test.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//@Service("userDetailsServiceImpl")
@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private MessageBoardService messageBoardService;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        long l = 300000;
        System.out.println("it---------------------------------success!");
        return (UserDetails) messageBoardService.findMessageById(l);
    }

}
