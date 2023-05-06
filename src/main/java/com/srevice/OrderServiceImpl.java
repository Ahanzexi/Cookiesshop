package com.srevice;

import com.dao.OrderDao;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    @Override
    public PageInfo<Order> selectOrderByStatus(int user_id, int status, Integer pageNum) {
        PageHelper.startPage(pageNum,10);
        List<Order> orderList;
        if (status==0){
            orderList = orderDao.selectAllOrder(user_id);
        } else{
            orderList = orderDao.selectOrderByStatus(user_id,status);
        }
        for (Order order:orderList) {
            order.setItemList(orderDao.selectAllItem(order.getId()));
        }
        return new PageInfo<>(orderList);
    }
}
