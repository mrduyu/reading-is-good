package com.getir.readingisgood.readingisgood.orderservice.models;


import java.io.Serializable;
import java.math.BigDecimal;

public class OrderDTO implements Serializable {
    private String orderId;
    private String orderedBookId;
    private int quantity;
    private BigDecimal totalAmount;
    private String orderDate;

    public OrderDTO() {
    }

    public OrderDTO(String orderId, String orderedBookId, int quantity, BigDecimal totalAmount, String orderDate) {
        this.orderId = orderId;
        this.orderedBookId = orderedBookId;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderedBookId() {
        return orderedBookId;
    }

    public void setOrderedBookId(String orderedBookId) {
        this.orderedBookId = orderedBookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
}

