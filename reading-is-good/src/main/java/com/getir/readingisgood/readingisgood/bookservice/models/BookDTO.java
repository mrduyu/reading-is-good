package com.getir.readingisgood.readingisgood.bookservice.models;


import java.io.Serializable;
import java.math.BigDecimal;

public class BookDTO implements Serializable {
    private String id;
    private String name;
    private String author;
    private Integer quantity;
    private BigDecimal amountPerItem;

    public BookDTO() {}

    public BookDTO(String id, String name, String author, Integer quantity) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.quantity = quantity;
    }

    public BookDTO(String id, String name, String author, Integer quantity, BigDecimal amountPerItem) {
        this.id = id;
        this.name = name;
        this.author = author;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmountPerItem() {
        return amountPerItem;
    }

    public void setAmountPerItem(BigDecimal amountPerItem) {
        this.amountPerItem = amountPerItem;
    }
}
