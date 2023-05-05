package com.srevice;

import com.dao.OrderDao;
import com.model.Order;
import com.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderDao orderDao;

    @Override
    public void addOrder(Order order) {
        orderDao.addOrder(order);
        System.out.println(order.getId());
        order.setId(order.getId());
        for(OrderItem item:order.getItemMap().values()){
//            System.out.println(item);
            orderDao.insertOrderItem(item);
        }
    }

    @Override
    public List<Order> selectAllOrder(int userId) {
        List<Order> list = orderDao.selectAllOrder(userId);
        for (Order order : list) {
            // 详单集合
            List<OrderItem> itemList = orderDao.selectAllItem(order.getId());
            order.setItemList(itemList);
        }
        return list;
    }
}
