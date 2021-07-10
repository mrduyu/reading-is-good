package com.getir.readingisgood.readingisgood.authenticationservice.services;


import com.getir.readingisgood.readingisgood.authenticationservice.dataaccesslayer.interfaces.UserRepositoryDAL;
import com.getir.readingisgood.readingisgood.authenticationservice.models.UserDAO;
import com.getir.readingisgood.readingisgood.authenticationservice.models.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepositoryDAL userRepositoryDAL;
    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDAO user = userRepositoryDAL.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        } else {
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                    new ArrayList<>());
        }
    }

    public boolean isUserAlreadyExists(String username){
        UserDAO user = userRepositoryDAL.findUserByUsername(username);
        if(user != null)
            return true;
        return false;
    }

    public UserDAO save(UserDTO user) {
        UserDAO newUser = new UserDAO();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        UserDAO userDAO = userRepositoryDAL.saveUser(newUser);
        return userDAO;
    }
}
