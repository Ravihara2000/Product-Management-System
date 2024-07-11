package com.Order.order.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Order_table")
public class Order {
    @Id
    private int id;
    private int itemId;
    private String orderDate;
    private int amount;
}
