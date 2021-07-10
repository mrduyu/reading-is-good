package com.getir.readingisgood.readingisgood.orderservice.helpers;

import com.getir.readingisgood.readingisgood.errormessages.ErrorCodes;
import com.getir.readingisgood.readingisgood.orderservice.requests.OrderBookRequest;

public class Util {
    public static ErrorCodes validateOrderRequest(OrderBookRequest orderBookRequest){
        if (orderBookRequest.getQuantity() < 1){
            return ErrorCodes.ERR0011;
        }else{
            return null;
        }
    }
}
