package com.getir.readingisgood.readingisgood.bookservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.getir.readingisgood.readingisgood.bookservice.dataaccesslayer.interfaces.BookRepositoryDAL;
import com.getir.readingisgood.readingisgood.bookservice.dataaccesslayer.repositories.BookRepository;
import com.getir.readingisgood.readingisgood.bookservice.models.BookDTO;
import com.getir.readingisgood.readingisgood.bookservice.requests.InsertBookRequest;
import com.getir.readingisgood.readingisgood.bookservice.requests.UpdateBookRequest;
import com.getir.readingisgood.readingisgood.bookservice.responses.*;
import com.getir.readingisgood.readingisgood.orderservice.requests.OrderBookRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class BookController {
    private final BookRepositoryDAL bookRepositoryDAL;
    private final BookRepository bookRepository;

    public BookController(BookRepositoryDAL bookRepositoryDAL, BookRepository bookRepository) {
        this.bookRepositoryDAL = bookRepositoryDAL;
        this.bookRepository = bookRepository;
    }

    @RequestMapping(value = "/books/insert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public InsertBookResponse insertNewBook(@RequestBody InsertBookRequest insertBookRequest) throws JsonProcessingException {
        return bookRepositoryDAL.insertBook(insertBookRequest);
    }

    @RequestMapping(value = "/book/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public UpdateBookResponse updateBook(@RequestBody UpdateBookRequest updateBookRequest) throws JsonProcessingException {
        return bookRepositoryDAL.updateBook(updateBookRequest);
    }

    @RequestMapping(value = "/books/order", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderBookResponse updateBookStock(@RequestBody OrderBookRequest orderBookRequest) {
        return bookRepositoryDAL.orderBook(orderBookRequest);
    }

    @RequestMapping(value = "/books/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ListBooksResponse getAllBooks() throws JsonProcessingException {
        return bookRepositoryDAL.getAllBooks();
    }

    @RequestMapping(value = "/books/{bookId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BookDetailResponse getBookDetail(@PathVariable String bookId) throws JsonProcessingException {
        return bookRepositoryDAL.getBookDetail(bookId);
    }
}
