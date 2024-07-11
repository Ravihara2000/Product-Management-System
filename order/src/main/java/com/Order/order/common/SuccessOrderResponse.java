package com.Order.order.common;

import com.Order.order.dto.OrderDto;
import lombok.Getter;

@Getter
public class SuccessOrderResponse implements OrderResponse{
    private final OrderDto orderDto;

    public SuccessOrderResponse(OrderDto orderDto){
        this.orderDto=orderDto;
    }
}
