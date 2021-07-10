package com.getir.readingisgood.readingisgood.errormessages;

public enum ErrorCodes {
    ERR0001("User already exists. Please choose different username."),
    ERR0002("You cannot buy more than stock count."),
    ERR0003("The book you would like to change the stock data does not exist. Please check your request."),
    ERR0004("Order could not be created. Please try again later."),
    ERR0005("The book you would like to buy is in another customer basket."),
    ERR0006("Order not found."),
    ERR0007("Book does not exist. Please ensure book data you enter."),
    ERR0008("Book already exists. Please insert different book."),
    ERR0009("There is no book exists in database."),
    ERR0010("There is no statistic data found."),
    ERR0011("Purchase count cannot be negative or zero."),
    SUCCESSFUL("SUCCESS");

    private String description;

    public String getDescription() {
        return description;
    }

    ErrorCodes(String description) {
        this.description = description;
    }
}