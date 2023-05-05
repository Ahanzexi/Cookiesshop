package com.controller;

import com.github.pagehelper.PageInfo;
import com.model.Goods;
import com.model.Order;
import com.srevice.GoodsService;
import com.srevice.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private TypeService typeService;

    @RequestMapping("/index")
    public String index(Model model, HttpSession session){
        model.addAttribute("scroll",goodsService.getAllGoodsByRecommend(1));
        model.addAttribute("hotList",goodsService.getAllGoodsByRecommend(2));
        model.addAttribute("newList",goodsService.getAllGoodsByRecommend(3));
//        model.addAttribute("typeList",typeService.typeList());
        session.setAttribute("typeList",typeService.typeList());
        return "index";
    }

    @RequestMapping("/goodsrecommend_list")
    public String goodsRecommendList(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,@RequestParam int type,Model model){
        PageInfo<Goods> pageInfo = goodsService.getGoodsByPage(pageNum,type);
        model.addAttribute("page",pageInfo);
        model.addAttribute("t",type);
        return "goodsrecommend_list";
    }

    @RequestMapping("/goods_detail")
    public String goodsDetail(@RequestParam int id,Model model){
        model.addAttribute("g",goodsService.goodsDetail(id));
        return "goods_detail";
    }

    @RequestMapping("/goods_list")
    public String goodsList(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,
                            @RequestParam(defaultValue = "0",value = "typeid")Integer typeid,Model model){
        PageInfo<Goods> pageInfo = goodsService.getGoodsByPageAndTypeId(pageNum,typeid);
        System.out.println(pageInfo.getPageNum());
        model.addAttribute("page",pageInfo);
        model.addAttribute("t",typeService.selectTypeById(typeid));
        return "goods_list";
    }

    @RequestMapping("/showCart")
    public String showCart(){
        return "goods_cart";
    }

    @RequestMapping("/goods_buy")
    @ResponseBody
    public String goodsBuy(int goodsid, HttpServletRequest request,HttpSession session){
        Order order;
        if(request.getSession().getAttribute("order") != null){
            order = (Order) request.getSession().getAttribute("order");
        }else {
            order = new Order();
        }
        Goods goods = goodsService.goodsDetail(goodsid);
        if(goods.getStock() > 0){
            order.addGoods(goods);
            session.setAttribute("order",order);
            return "ok";
        }else {
           session.setAttribute("order",order);
            return "fail";
        }
    }

    @RequestMapping("/goods_lessen")
    @ResponseBody
    public String goodsLessen(int goodsid,HttpServletRequest request){
        Order order = (Order) request.getSession().getAttribute("order");
        order.lessen(goodsid);
        request.getSession().setAttribute("order",order);
        return "ok";
    }

    @RequestMapping("/goods_delete")
    @ResponseBody
    public String goodsDelete(int goodsid,HttpServletRequest request){
        Order order = (Order) request.getSession().getAttribute("order");
        order.delete(goodsid);
        request.getSession().setAttribute("order",order);
        return "ok";
    }
}
