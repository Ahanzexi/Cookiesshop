package com.dao;

import com.model.Order;
import com.model.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrderDao {
     int addOrder(Order order);
     int insertOrderItem(OrderItem orderItem);
     List<Order> selectAllOrder(int userId);
     List<OrderItem> selectAllItem(int orderId);

     List<Order> selectOrderByStatus(@Param("userid") int userid,@Param("status") int status);

//     List<OrderItem> selectOrderItemByStatus(@Param("user_id") int user_id,@Param("status") int status);
}
