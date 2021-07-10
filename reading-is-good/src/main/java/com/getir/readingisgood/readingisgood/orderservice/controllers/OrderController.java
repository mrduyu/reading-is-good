package com.getir.readingisgood.readingisgood.orderservice.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.getir.readingisgood.readingisgood.authenticationservice.responses.BaseResponse;
import com.getir.readingisgood.readingisgood.bookservice.responses.OrderBookResponse;
import com.getir.readingisgood.readingisgood.errormessages.ErrorCodes;
import com.getir.readingisgood.readingisgood.orderservice.dataaccesslayer.interfaces.OrderRepositoryDAL;
import com.getir.readingisgood.readingisgood.orderservice.dataaccesslayer.repositories.OrderRepository;
import com.getir.readingisgood.readingisgood.orderservice.helpers.Util;
import com.getir.readingisgood.readingisgood.orderservice.requests.OrderBookRequest;
import com.getir.readingisgood.readingisgood.orderservice.responses.OrderDetailResponse;
import com.getir.readingisgood.readingisgood.orderservice.responses.OrderListResponse;
import com.getir.readingisgood.readingisgood.orderservice.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
public class OrderController {
    private final OrderRepositoryDAL orderRepositoyDAL;
    private final OrderRepository orderRepository;
    @Autowired
    private BookService bookService;

    public OrderController(OrderRepositoryDAL orderRepositoyDAL, OrderRepository orderRepository) {
        this.orderRepositoyDAL = orderRepositoyDAL;
        this.orderRepository = orderRepository;
    }

    @RequestMapping(value = "/orders/{startDate}/{endDate}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderListResponse getOrderBetweenDates(@PathVariable String startDate, @PathVariable String endDate) throws JsonProcessingException, ParseException {
        return orderRepositoyDAL.getOrdersBetweenDates(startDate,endDate);
    }

    @RequestMapping(value = "/orders/{orderId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderDetailResponse getOrderById(@PathVariable String orderId) throws JsonProcessingException {
        return orderRepositoyDAL.getOrderById(orderId);
    }

    @RequestMapping(value = "/orders", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderBookResponse createOrder(@RequestBody OrderBookRequest orderBookRequest) throws JsonProcessingException {
        if (Util.validateOrderRequest(orderBookRequest) != null)
            return new OrderBookResponse(ErrorCodes.ERR0011.name(),ErrorCodes.ERR0011.getDescription(), null);
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BaseResponse orderCreatedResponse = orderRepositoyDAL.createOrder(orderBookRequest,userDetails.getUsername());
        if (orderCreatedResponse.getReturnCode().equals("SUCCESSFUL")){
            return bookService.updateBookStock(orderBookRequest.getBookId(),orderBookRequest.getQuantity(),orderBookRequest.isRemovedFromBasket());
        }
        return  new OrderBookResponse(ErrorCodes.ERR0004.name(),ErrorCodes.ERR0004.getDescription(), null);
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderListResponse listAllOrders() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return orderRepositoyDAL.getOrdersByUsername(userDetails.getUsername());
    }
}