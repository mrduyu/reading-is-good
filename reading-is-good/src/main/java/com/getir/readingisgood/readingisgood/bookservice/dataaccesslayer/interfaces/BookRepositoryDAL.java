package com.getir.readingisgood.readingisgood.bookservice.dataaccesslayer.interfaces;

import com.getir.readingisgood.readingisgood.authenticationservice.responses.BaseResponse;
import com.getir.readingisgood.readingisgood.bookservice.models.BookDTO;
import com.getir.readingisgood.readingisgood.bookservice.requests.InsertBookRequest;
import com.getir.readingisgood.readingisgood.bookservice.requests.UpdateBookRequest;
import com.getir.readingisgood.readingisgood.bookservice.responses.*;
import com.getir.readingisgood.readingisgood.orderservice.requests.OrderBookRequest;

import java.util.List;

public interface BookRepositoryDAL {
    InsertBookResponse insertBook(InsertBookRequest bookData);
    UpdateBookResponse updateBook(UpdateBookRequest bookData);
    OrderBookResponse orderBook(OrderBookRequest updateBookRequest);
    ListBooksResponse getAllBooks();
    BookDetailResponse getBookDetail(String bookId);
}
