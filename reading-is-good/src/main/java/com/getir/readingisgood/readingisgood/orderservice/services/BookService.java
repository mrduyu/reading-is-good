package com.getir.readingisgood.readingisgood.orderservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.getir.readingisgood.readingisgood.bookservice.controllers.BookController;
import com.getir.readingisgood.readingisgood.bookservice.models.BookDTO;
import com.getir.readingisgood.readingisgood.bookservice.requests.UpdateBookRequest;
import com.getir.readingisgood.readingisgood.bookservice.responses.BookDetailResponse;
import com.getir.readingisgood.readingisgood.bookservice.responses.OrderBookResponse;
import com.getir.readingisgood.readingisgood.orderservice.requests.OrderBookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
public class BookService
{
    private final BookController bookController;

    public BookService(BookController bookController) {
        this.bookController = bookController;
    }

    public BigDecimal getBookPrice(String bookId) throws JsonProcessingException {
            ObjectMapper objectMapper = new ObjectMapper();
            BookDetailResponse bookDetailResponse = bookController.getBookDetail(bookId);
            return bookDetailResponse.getBookDetail().getAmountPerItem();
    }

    public OrderBookResponse updateBookStock(String bookId, int quantity, boolean isRemovedFromBasket) {
        OrderBookRequest orderBookRequest = new OrderBookRequest(bookId,quantity,isRemovedFromBasket);
        return bookController.updateBookStock(orderBookRequest);
    }
}
