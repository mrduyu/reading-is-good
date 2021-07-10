package com.getir.readingisgood.readingisgood.authenticationservice.dataaccesslayer.interfaces;

import com.getir.readingisgood.readingisgood.authenticationservice.models.UserDAO;

public interface UserRepositoryDAL {
    UserDAO saveUser(UserDAO userData);
    UserDAO findUserByUsername(String username);
}