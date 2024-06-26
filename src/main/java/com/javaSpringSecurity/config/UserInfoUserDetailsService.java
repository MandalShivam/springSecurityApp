package com.javaSpringSecurity.config;

import com.javaSpringSecurity.entity.UserInfo;
import com.javaSpringSecurity.repository.UserInfoRepository;
import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;
/*
here we are using own UserDetailsService class(UserInfoUserDetailsService) by implementing it for
custom logic for authentication
this class will take the user credentials from the database and calls UserInfoUserDetails class to convert
the user credentials to UserDetails form so that spring authenticator can understand that
* */
@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserInfoRepository  repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<UserInfo> userInfo = repository.findByName(username);
       return userInfo.map(UserInfoUserDetails::new)
               .orElseThrow(()->new UsernameNotFoundException("user not found "+username));
    }
}
