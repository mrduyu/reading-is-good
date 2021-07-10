package com.getir.readingisgood.readingisgood.statisticservice.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.getir.readingisgood.readingisgood.bookservice.controllers.BookController;
import com.getir.readingisgood.readingisgood.errormessages.ErrorCodes;
import com.getir.readingisgood.readingisgood.orderservice.controllers.OrderController;
import com.getir.readingisgood.readingisgood.orderservice.models.OrderDTO;
import com.getir.readingisgood.readingisgood.orderservice.responses.OrderListResponse;
import com.getir.readingisgood.readingisgood.statisticservice.helpers.DateUtil;
import com.getir.readingisgood.readingisgood.statisticservice.models.StatisticsModel;
import com.getir.readingisgood.readingisgood.statisticservice.responses.StatisticsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@Service
public class OrderService {
    private OrderController orderController;

    public OrderService(OrderController orderController) {
        this.orderController = orderController;
    }

    public StatisticsResponse getOrderStatistics() {
        OrderListResponse orderList = orderController.listAllOrders();
        if (orderList != null && orderList.getOrderList() != null && orderList.getOrderList().size()>0){
            HashMap<String, StatisticsModel> orders = new HashMap<>();
            for (OrderDTO item: orderList.getOrderList() ) {
                String monthName = DateUtil.theMonth(Integer.parseInt(item.getOrderDate().split("\\.")[1])); //dd.MM.yyyy
                if (orders.containsKey(monthName)){
                    StatisticsModel current = orders.get(monthName);
                    int totalOrderCount = current.getTotalOrderCount();
                    int totalOrderBookCount = current.getTotalOrderedBookCount();
                    BigDecimal totalPurchasedAmount = current.getTotalPurchasedAmount();
                    int bookCount = current.getTotalOrderedBookCount();
                    StatisticsModel newStatistic = new StatisticsModel(totalOrderCount+1,totalOrderBookCount+item.getQuantity(),totalPurchasedAmount.add(item.getTotalAmount()));
                    orders.put(monthName,newStatistic);
                }
                else
                {
                    StatisticsModel newStatistic = new StatisticsModel(1,item.getQuantity(),item.getTotalAmount());
                    orders.put(monthName,newStatistic);
                }
            }
            return new StatisticsResponse(ErrorCodes.SUCCESSFUL.name(), null,orders);
        }
        return new StatisticsResponse(ErrorCodes.ERR0010.name(),ErrorCodes.ERR0010.getDescription(), null);
    }
}
