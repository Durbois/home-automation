package com.teamtreehouse.homeautomation.service;

import com.teamtreehouse.homeautomation.dao.UserRepository;
import com.teamtreehouse.homeautomation.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class DetailsService implements UserDetailsService {
  @Autowired
  private UserRepository users;

  public User findByUsername(String username) {
    return users.findByName(username);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // load user from database (throw exception if not found)
    User user = users.findByName(username);

    if(user == null){
      throw new UsernameNotFoundException("User not found");
    }

    // return user object
    return new org.springframework.security.core.userdetails.User(
        user.getName(),
        user.getPassword(),
        AuthorityUtils.createAuthorityList(user.getRoles())
    );
  }
}
