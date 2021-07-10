package com.getir.readingisgood.readingisgood.bookservice.dataaccesslayer.repositories;

import com.getir.readingisgood.readingisgood.bookservice.models.BookDAO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<BookDAO,String> {
}