package com.srevice;

import com.model.Order;
import com.model.OrderItem;

import java.util.List;

public interface OrderService {
    public void addOrder(Order order);
    public List<Order> selectAllOrder(int userId);
}
