package com.jaggaer.moviedb.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jaggaer.moviedb.model.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    User user = new User(username, username, "{noop}password");

    return UserDetailsImpl.build(user);
  }
}