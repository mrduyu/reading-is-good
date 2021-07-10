package com.getir.readingisgood.readingisgood.orderservice.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.getir.readingisgood.readingisgood.authenticationservice.responses.BaseResponse;
import com.getir.readingisgood.readingisgood.orderservice.models.OrderDTO;

import java.util.List;

public class OrderListResponse extends BaseResponse {
    @JsonProperty(value = "orderList", required = true)
    private List<OrderDTO> orderList;

    public OrderListResponse(String returnCode, String errorMessage, List<OrderDTO> orderList) {
        super(returnCode, errorMessage);
        this.orderList = orderList;
    }

    public List<OrderDTO> getOrderList() {
        return orderList;
    }
}
