package com.getir.readingisgood.readingisgood.orderservice.responses;

import com.getir.readingisgood.readingisgood.authenticationservice.responses.BaseResponse;

import java.math.BigDecimal;

public class CreateOrderResponse extends BaseResponse {
    private String orderId;

    public CreateOrderResponse(String returnCode, String errorMessage, String orderId) {
        super(returnCode, errorMessage);
        this.orderId = orderId;
    }
}
