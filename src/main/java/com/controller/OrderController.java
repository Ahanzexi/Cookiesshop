package com.controller;

import com.model.Order;
import com.model.User;
import com.srevice.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/order_submit")
    public String orderSubmit(HttpServletRequest request, Model model){
        if(request.getSession().getAttribute("user") == null){
            model.addAttribute("msg","提交订单，请先登录！");
            return "user_login";
        }
        return "order_submit";
    }

    @RequestMapping("/order_confirm")
    public String orderConfirm(int paytype,HttpServletRequest request,Model model){
        Order order = (Order) request.getSession().getAttribute("order");
        //1微信/2支付宝/3货到付款
        order.setPaytype(paytype);
        if(paytype == 3){
            order.setStatus(1); // 未付款
        }else {
            order.setStatus(2); // 已付款
        }
        User user = (User) request.getSession().getAttribute("user");
        order.setUser(user);
        order.setName(user.getName());
        order.setPhone(user.getPhone());
        order.setAddress(user.getAddress());
        order.setDatetime(new Date());
//        添加订单
        orderService.addOrder(order);
//        清空购物车
        request.getSession().removeAttribute("order");
        model.addAttribute("msg", "订单支付成功，待收货！");
        return "order_success";
    }

    @RequestMapping("/order_list")
    public String orderList(HttpServletRequest request,HttpSession session,Model model){
        User user = (User) request.getSession().getAttribute("user");
        if( user == null){
            model.addAttribute("msg","提交订单，请先登录！");
            return "user_login";
        }
        List<Order> orderList = orderService.selectAllOrder(user.getId());
        session.setAttribute("orderList",orderList);
        return "order_list";
    }
}
