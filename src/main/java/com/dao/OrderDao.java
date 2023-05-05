package com.dao;

import com.model.Order;
import com.model.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrderDao {
    public int addOrder(Order order);
    public int insertOrderItem(OrderItem orderItem);
    public List<Order> selectAllOrder(int userId);
    public List<OrderItem> selectAllItem(int orderId);
}
