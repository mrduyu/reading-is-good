package com.getir.readingisgood.readingisgood.authenticationservice.dataaccesslayer.implementations;

import com.getir.readingisgood.readingisgood.authenticationservice.dataaccesslayer.interfaces.UserRepositoryDAL;
import com.getir.readingisgood.readingisgood.authenticationservice.models.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryDALImpl implements UserRepositoryDAL {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public UserDAO saveUser(UserDAO userData) {
        return mongoTemplate.save(userData);
    }

    @Override
    public UserDAO findUserByUsername(String username) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));
        UserDAO user = mongoTemplate.findOne(query, UserDAO.class);
        return user;
    }
}
