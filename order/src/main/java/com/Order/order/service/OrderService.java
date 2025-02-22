package com.Order.order.service;

import com.Inventory.inventory.dto.InventoryDto;
import com.Order.order.common.ErrorOrderResponse;
import com.Order.order.common.OrderResponse;
import com.Order.order.common.SuccessOrderResponse;
import com.Order.order.dto.OrderDto;
import com.Order.order.entity.Order;
import com.Order.order.repository.OrderRepo;
import com.Product.product.dto.ProductDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
@Service
public class OrderService {
    private final WebClient inventoryWebClient;
    private final WebClient productWebClient;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper modelMapper;

    public OrderService(WebClient inventoryWebClient,WebClient productWebClient,OrderRepo orderRepo,ModelMapper modelMapper) {
        this.inventoryWebClient=inventoryWebClient;
        this.productWebClient=productWebClient;
        this.orderRepo=orderRepo;
        this.modelMapper=modelMapper;
    }

    public List<OrderDto> getAllOrders() {
        List<Order>orderList = orderRepo.findAll();
        return modelMapper.map(orderList, new TypeToken<List<OrderDto>>(){}.getType());
    }

    public OrderResponse saveOrder(OrderDto orderDto) {
        Integer itemId =orderDto.getItemId();

        try{
            InventoryDto inventoryResponse=inventoryWebClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/inventory/item/{itemId}").build(itemId))
                    .retrieve()
                    .bodyToMono(InventoryDto.class)
                    .block();

            assert inventoryResponse != null;

            Integer productId= inventoryResponse.getProductId();

            ProductDto productResponse=productWebClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/product/product/{productId}").build(productId))
                    .retrieve()
                    .bodyToMono(ProductDto.class)
                    .block();

            assert productResponse != null;

            if(inventoryResponse.getQuantity()>0){
                if(productResponse.getForSale()==1) {
                    orderRepo.save(modelMapper.map(orderDto, Order.class));
                    return new SuccessOrderResponse(orderDto);
                }else {
                    return new ErrorOrderResponse("Item is not for sale");
                }
            }else {
                return new ErrorOrderResponse("Item  not available");
            }
        }catch (WebClientResponseException e){
            if(e.getStatusCode().is5xxServerError()){
                return new ErrorOrderResponse("Item  not available");
            }
        }
        return null;
    }

    public OrderDto updateOrder(OrderDto orderDto) {
        orderRepo.save(modelMapper.map(orderDto, Order.class));
        return orderDto;
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
