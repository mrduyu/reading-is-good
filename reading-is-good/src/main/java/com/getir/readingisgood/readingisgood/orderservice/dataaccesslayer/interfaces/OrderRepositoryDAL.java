package com.getir.readingisgood.readingisgood.orderservice.dataaccesslayer.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.getir.readingisgood.readingisgood.authenticationservice.responses.BaseResponse;
import com.getir.readingisgood.readingisgood.orderservice.models.OrderDAO;
import com.getir.readingisgood.readingisgood.orderservice.models.OrderDTO;
import com.getir.readingisgood.readingisgood.orderservice.requests.OrderBookRequest;
import com.getir.readingisgood.readingisgood.orderservice.responses.CreateOrderResponse;
import com.getir.readingisgood.readingisgood.orderservice.responses.OrderDetailResponse;
import com.getir.readingisgood.readingisgood.orderservice.responses.OrderListResponse;

import java.text.ParseException;
import java.util.List;

public interface OrderRepositoryDAL {
    CreateOrderResponse createOrder(OrderBookRequest orderData, String username) throws JsonProcessingException;
    OrderListResponse getOrdersByUsername(String username);
    OrderDetailResponse getOrderById(String orderId);
    OrderListResponse getOrdersBetweenDates(String startDate, String endDate) throws ParseException;
}
