package com.getir.readingisgood.readingisgood.orderservice.requests;
import javax.validation.constraints.Min;

public class OrderBookRequest {
    private String bookId;
    @Min(value = 1, message = "Purchase count cannot be negative or zero.")
    private int quantity;
    private boolean isRemovedFromBasket;// kullanici sepetten kitabi cikardiysa

    public OrderBookRequest(String bookId, int quantity, boolean isRemovedFromBasket) {
        this.bookId = bookId;
        this.quantity = quantity;
        this.isRemovedFromBasket = isRemovedFromBasket;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isRemovedFromBasket() {
        return isRemovedFromBasket;
    }

    public void setRemovedFromBasket(boolean removedFromBasket) {
        isRemovedFromBasket = removedFromBasket;
    }
}
