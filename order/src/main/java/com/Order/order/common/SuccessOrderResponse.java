package com.Order.order.common;

import com.Order.order.dto.OrderDto;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;

@Getter
public class SuccessOrderResponse implements OrderResponse{
    @JsonUnwrapped
    private final OrderDto orderDto;

    public SuccessOrderResponse(OrderDto orderDto){
        this.orderDto=orderDto;
    }
}
