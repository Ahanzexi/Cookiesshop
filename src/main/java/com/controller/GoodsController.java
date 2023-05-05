package com.controller;

import com.github.pagehelper.PageInfo;
import com.model.Goods;
import com.srevice.GoodsService;
import com.srevice.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private TypeService typeService;

    @RequestMapping("/toAddGoods")
    public String toAdd(Model model){
        model.addAttribute("typeList",typeService.typeList());
        return "/admin/goods_add";
    }

    @RequestMapping("/goods_add")
    public String addGoods(Goods goods, @RequestParam("images")List<MultipartFile> images, HttpServletRequest request){
        List<String> imagesNames = new ArrayList<String>();
        if(!images.isEmpty() && images.size()>0){
            for(MultipartFile image:images){
                String imageName = image.getOriginalFilename();
                String dirPath = request.getServletContext().getRealPath("/picture/");
                File filePath = new File(dirPath);
                if(filePath.exists()){
                    filePath.mkdirs();
                }
//                给JavaBean对象赋值图片路径
                String newFileName = "/picture/" + imageName;
                imagesNames.add(newFileName);
                try {
                    image.transferTo(new File(dirPath + imageName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        goods.setCover(imagesNames.get(0));
        goods.setImage1(imagesNames.get(1));
        goods.setImage2(imagesNames.get(2));
        goodsService.addGoods(goods);
        return "forward:/admin/goods_list";
    }

    @RequestMapping("/goods_list")
    public String goodsList(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,
                            @RequestParam(defaultValue = "0",required = false) int recommend,
                            Model model){
        PageInfo<Goods> pageInfo = goodsService.getGoodsByPage(pageNum,recommend);
        model.addAttribute("page",pageInfo);
        model.addAttribute("recommend",recommend);
        return "/admin/goods_list";
    }

    @RequestMapping("/goods_delete")
    public String goodsDelete(int id,int pageNum,int recommend){
        goodsService.deleteGoods(id);
        return "forward:/admin/goods_list";
    }

    @RequestMapping("/goods_recommend")
    public String goodsRecommend(int id,String method,int typeTarget,int pageNum,int recommend){
        if(method.equals("remove")){
            goodsService.removeRecommend(typeTarget,id);
        }else if(method.equals("add")){
            goodsService.addRecommend(typeTarget,id);
        }
        return "forward:/admin/goods_list";
    }
}
