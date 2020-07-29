package com.eurodesign09.windowproject.config;

import com.eurodesign09.windowproject.dao.UserRepository;
import com.eurodesign09.windowproject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.getUserByName(username);

        if(user == null)
            throw new UsernameNotFoundException("Пользователь с данным именем не существует!");
        return new MyUserDetails(user);
    }
}
