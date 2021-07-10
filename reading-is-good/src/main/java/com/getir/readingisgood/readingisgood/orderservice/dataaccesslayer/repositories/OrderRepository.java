package com.getir.readingisgood.readingisgood.orderservice.dataaccesslayer.repositories;

import com.getir.readingisgood.readingisgood.orderservice.models.OrderDAO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<OrderDAO,String> {
}