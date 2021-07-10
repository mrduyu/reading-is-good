package com.getir.readingisgood.readingisgood.statisticservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.getir.readingisgood.readingisgood.statisticservice.responses.StatisticsResponse;
import com.getir.readingisgood.readingisgood.statisticservice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticController {
    @Autowired
    private OrderService orderService;
    @RequestMapping(value = "/orderstatistics", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public StatisticsResponse getCustomerOrderStatistics() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return orderService.getOrderStatistics();
    }
}