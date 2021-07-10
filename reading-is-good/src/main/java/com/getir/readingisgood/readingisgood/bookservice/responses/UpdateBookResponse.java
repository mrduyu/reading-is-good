package com.getir.readingisgood.readingisgood.bookservice.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.getir.readingisgood.readingisgood.authenticationservice.responses.BaseResponse;

public class UpdateBookResponse extends BaseResponse {
    @JsonProperty(value = "bookId", required = true)
    private String bookId;

    public UpdateBookResponse(String returnCode, String errorMessage, String bookId) {
        super(returnCode, errorMessage);
        this.bookId = bookId;
    }

}
