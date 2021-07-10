package com.getir.readingisgood.readingisgood.authenticationservice.dataaccesslayer.repositories;

import com.getir.readingisgood.readingisgood.authenticationservice.models.UserDAO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserDAO,String> {
}