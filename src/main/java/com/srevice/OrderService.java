package com.srevice;

import com.github.pagehelper.PageInfo;
import com.model.Order;

import java.util.List;

public interface OrderService {
     void addOrder(Order order);
     List<Order> selectAllOrder(int userId);

    PageInfo<Order> selectOrderByStatus(int user_id, int status, Integer pageNum);
}
