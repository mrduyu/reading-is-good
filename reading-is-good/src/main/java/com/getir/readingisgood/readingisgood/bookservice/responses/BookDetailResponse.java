package com.getir.readingisgood.readingisgood.bookservice.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.getir.readingisgood.readingisgood.authenticationservice.responses.BaseResponse;
import com.getir.readingisgood.readingisgood.bookservice.models.BookDTO;

public class BookDetailResponse extends BaseResponse {
    @JsonProperty(value = "bookDetail", required = true)
    private BookDTO bookDetail;

    public BookDetailResponse(String returnCode, String errorMessage, BookDTO bookDetail) {
        super(returnCode, errorMessage);
        this.bookDetail = bookDetail;
    }
    public BookDTO getBookDetail() {
        return bookDetail;
    }
}
