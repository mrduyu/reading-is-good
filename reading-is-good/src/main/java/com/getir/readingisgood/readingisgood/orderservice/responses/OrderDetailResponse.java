package com.getir.readingisgood.readingisgood.orderservice.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.getir.readingisgood.readingisgood.authenticationservice.responses.BaseResponse;
import com.getir.readingisgood.readingisgood.orderservice.models.OrderDTO;


public class OrderDetailResponse extends BaseResponse {
    @JsonProperty(value = "orderDetail", required = true)
    private OrderDTO orderDetail;

    public OrderDetailResponse(String returnCode, String errorMessage, OrderDTO orderDetail) {
        super(returnCode, errorMessage);
        this.orderDetail = orderDetail;
    }
}
