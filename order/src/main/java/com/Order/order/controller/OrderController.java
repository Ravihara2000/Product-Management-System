package com.Order.order.controller;

import com.Order.order.common.OrderResponse;
import com.Order.order.dto.OrderDto;
import com.Order.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/getorders")
    public List<OrderDto> getOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/order/{orderId}")
    public OrderDto getOrderById(@PathVariable Integer orderId) {
        return orderService.getOrderById(orderId);
    }

    @PostMapping("/addorder")
    public OrderResponse saveOrder(@RequestBody OrderDto orderDto) {
        return orderService.saveOrder(orderDto);
    }

    @PutMapping("/updateorder")
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) {
        return orderService.updateOrder(orderDto);
    }

    @DeleteMapping("/deleteorder/{orderId}")
    public String deleteOrder(@PathVariable Integer orderId) {
        return orderService.deleteOrder(orderId);
    }
}
