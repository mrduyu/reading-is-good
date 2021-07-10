package com.getir.readingisgood.readingisgood.bookservice.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.getir.readingisgood.readingisgood.authenticationservice.responses.BaseResponse;
import com.getir.readingisgood.readingisgood.bookservice.models.BookDTO;

import java.util.List;

public class ListBooksResponse extends BaseResponse {
    @JsonProperty(value = "bookList", required = true)
    private List<BookDTO> bookList;

    public ListBooksResponse(String returnCode, String errorMessage, List<BookDTO> bookList) {
        super(returnCode, errorMessage);
        this.bookList = bookList;
    }
    public List<BookDTO> getBookList() {
        return bookList;
    }
}
