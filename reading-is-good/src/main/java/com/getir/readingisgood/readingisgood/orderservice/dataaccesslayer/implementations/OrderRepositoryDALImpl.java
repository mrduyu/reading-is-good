package com.getir.readingisgood.readingisgood.orderservice.dataaccesslayer.implementations;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.getir.readingisgood.readingisgood.authenticationservice.responses.BaseResponse;
import com.getir.readingisgood.readingisgood.errormessages.ErrorCodes;
import com.getir.readingisgood.readingisgood.orderservice.dataaccesslayer.interfaces.OrderRepositoryDAL;
import com.getir.readingisgood.readingisgood.orderservice.models.OrderDAO;
import com.getir.readingisgood.readingisgood.orderservice.models.OrderDTO;
import com.getir.readingisgood.readingisgood.orderservice.requests.OrderBookRequest;
import com.getir.readingisgood.readingisgood.orderservice.responses.CreateOrderResponse;
import com.getir.readingisgood.readingisgood.orderservice.responses.OrderDetailResponse;
import com.getir.readingisgood.readingisgood.orderservice.responses.OrderListResponse;
import com.getir.readingisgood.readingisgood.orderservice.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class OrderRepositoryDALImpl  implements OrderRepositoryDAL {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private BookService bookService;

    private final SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

    @Override
    public CreateOrderResponse createOrder(OrderBookRequest orderBookRequest, String username) throws JsonProcessingException {
        OrderDAO orderData = new OrderDAO();
        orderData.setQuantity(orderBookRequest.getQuantity());
        orderData.setBookId(orderBookRequest.getBookId());
        orderData.setUsername(username);
        orderData.setTotalAmount(bookService.getBookPrice(orderBookRequest.getBookId()).multiply(BigDecimal.valueOf(orderBookRequest.getQuantity())));
        String strDate = formatter.format(new Date());
        orderData.setOrderDate(strDate);
        OrderDAO createdOrder = mongoTemplate.save(orderData);
        return new CreateOrderResponse(ErrorCodes.SUCCESSFUL.name(), null,createdOrder.getId());
    }

    @Override
    public OrderListResponse getOrdersByUsername(String username) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));
        List<OrderDAO> orderList = mongoTemplate.find(query, OrderDAO.class);
        if (orderList != null && orderList.size()>0){
            List<OrderDTO> orderDTOList = new ArrayList<>();
            for (OrderDAO item: orderList) {
                OrderDTO orderDTO = new OrderDTO(item.getId(),item.getBookId(),item.getQuantity(),item.getTotalAmount(),item.getOrderDate());
                orderDTOList.add(orderDTO);
            }
            return new OrderListResponse(ErrorCodes.SUCCESSFUL.name(), null,orderDTOList);
        }
        return new OrderListResponse(ErrorCodes.ERR0006.name(),ErrorCodes.ERR0006.getDescription(),null);
    }

    @Override
    public OrderDetailResponse getOrderById(String orderId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(orderId));
        OrderDAO order = mongoTemplate.findOne(query, OrderDAO.class);
        if (order != null){
            OrderDTO orderDTO = new OrderDTO(order.getId(),order.getBookId(),order.getQuantity(),order.getTotalAmount(),order.getOrderDate());
            return new OrderDetailResponse(ErrorCodes.SUCCESSFUL.name(), null, orderDTO);
        }
        return new OrderDetailResponse(ErrorCodes.ERR0006.name(), ErrorCodes.ERR0006.getDescription(), null);
    }

    @Override
    public OrderListResponse getOrdersBetweenDates(String startDate, String endDate) throws ParseException {
        List<OrderDTO> orderListBetweenDates = new ArrayList<>();
        Query query = new Query();
        List<OrderDAO> orderList = mongoTemplate.find(query, OrderDAO.class);
        Date requestStartDate = new SimpleDateFormat("dd.MM.yyyy").parse(startDate);
        Date requestEndDate = new SimpleDateFormat("dd.MM.yyyy").parse(endDate);
        for (OrderDAO item : orderList) {
            Date sDate = new SimpleDateFormat("dd.MM.yyyy").parse(item.getOrderDate());
            Date eDate = new SimpleDateFormat("dd.MM.yyyy").parse(item.getOrderDate());
            if ((sDate.after(requestStartDate) || sDate.equals(requestStartDate)) && (eDate.before(requestEndDate) || eDate.equals(requestEndDate))) {
                OrderDTO orderDTO = new OrderDTO(item.getId(),item.getBookId(),item.getQuantity(),item.getTotalAmount(),item.getOrderDate());
                orderListBetweenDates.add(orderDTO);
            }
        }
        return new OrderListResponse(ErrorCodes.SUCCESSFUL.name(), null,orderListBetweenDates);
    }
}