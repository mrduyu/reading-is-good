package com.getir.readingisgood.readingisgood.bookservice.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.getir.readingisgood.readingisgood.authenticationservice.responses.BaseResponse;

public class OrderBookResponse extends BaseResponse {
    @JsonProperty(value = "orderId", required = true)
    private String orderId;

    public OrderBookResponse(String returnCode, String errorMessage, String orderId) {
        super(returnCode, errorMessage);
        this.orderId = orderId;
    }

}
