package com.getir.readingisgood.readingisgood.statisticservice.helpers;

public class DateUtil {
    public static String theMonth(int month){
        String[] monthNames = {"","January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        return monthNames[month];
    }
}
