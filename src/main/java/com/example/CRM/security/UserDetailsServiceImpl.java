package com.example.CRM.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        if (userName.contains("admin")) {
            return new User("admin", "admin", new ArrayList<>());
        }

        if (userName.contains("trainer")) {
            return new User("trainer", "trainer", new ArrayList<>());
        }

        else
            return new User("client", "client", new ArrayList<>());
    }
}
