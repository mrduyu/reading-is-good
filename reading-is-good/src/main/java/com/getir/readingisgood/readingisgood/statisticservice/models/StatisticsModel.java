package com.getir.readingisgood.readingisgood.statisticservice.models;

import java.math.BigDecimal;

public class StatisticsModel {
    private int totalOrderCount;
    private int totalOrderedBookCount;
    private BigDecimal totalPurchasedAmount;

    public StatisticsModel(int totalOrderCount, int totalOrderedBookCount, BigDecimal totalPurchasedAmount) {
        this.totalOrderCount = totalOrderCount;
        this.totalOrderedBookCount = totalOrderedBookCount;
        this.totalPurchasedAmount = totalPurchasedAmount;
    }

    public int getTotalOrderCount() {
        return totalOrderCount;
    }

    public void setTotalOrderCount(int totalOrderCount) {
        this.totalOrderCount = totalOrderCount;
    }

    public int getTotalOrderedBookCount() {
        return totalOrderedBookCount;
    }

    public void setTotalOrderedBookCount(int totalOrderedBookCount) {
        this.totalOrderedBookCount = totalOrderedBookCount;
    }

    public BigDecimal getTotalPurchasedAmount() {
        return totalPurchasedAmount;
    }

    public void setTotalPurchasedAmount(BigDecimal totalPurchasedAmount) {
        this.totalPurchasedAmount = totalPurchasedAmount;
    }
}
