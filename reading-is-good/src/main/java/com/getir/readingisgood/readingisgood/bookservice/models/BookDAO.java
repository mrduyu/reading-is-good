package com.getir.readingisgood.readingisgood.bookservice.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
public class BookDAO {
    @Id
    private String id;
    private String name;
    private String author;
    private String status;
    private int quantity;
    private BigDecimal amountPerItem;

    public BookDAO(String id, String name, String author, String status, int quantity) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.status = status;
        this.quantity = quantity;
    }

    public BookDAO() {
    }

    public BookDAO(String name, String author, String status, int quantity, BigDecimal amountPerItem) {
        this.name = name;
        this.author = author;
        this.status = status;
        this.quantity = quantity;
        this.amountPerItem = amountPerItem;
    }

    public BookDAO(String id, String name, String author, String status, int quantity, BigDecimal amountPerItem) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.status = status;
        this.quantity = quantity;
        this.amountPerItem = amountPerItem;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmountPerItem() {
        return amountPerItem;
    }

    public void setAmountPerItem(BigDecimal amountPerItem) {
        this.amountPerItem = amountPerItem;
    }
}
