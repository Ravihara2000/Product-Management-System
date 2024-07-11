package com.Order.order.service;

import com.Order.order.dto.OrderDto;
import com.Order.order.entity.Order;
import com.Order.order.repository.OrderRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<OrderDto> getAllOrders() {
        List<Order>orderList = orderRepo.findAll();
        return modelMapper.map(orderList, new TypeToken<List<OrderDto>>(){}.getType());
    }

    public OrderDto saveOrder(OrderDto OrderDTO) {
        orderRepo.save(modelMapper.map(OrderDTO, Order.class));
        return OrderDTO;
    }

    public OrderDto updateOrder(OrderDto OrderDTO) {
        orderRepo.save(modelMapper.map(OrderDTO, Order.class));
        return OrderDTO;
    }

    public String deleteOrder(Integer orderId) {
        orderRepo.deleteById(orderId);
        return "Order deleted";
    }

    public OrderDto getOrderById(Integer orderId) {
        Order order = orderRepo.getOrderById(orderId);
        return modelMapper.map(order, OrderDto.class);
    }
}
